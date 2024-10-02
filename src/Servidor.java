import java.rmi.RemoteException; // Tratamento de exceções remotas
import java.rmi.server.UnicastRemoteObject; // Permite a implementação de objetos remotos
import java.util.ArrayList; // Lista para armazenar clientes conectados
import java.util.HashMap; // Mapa para associar clientes a nomes
import java.util.Iterator; // Para iterar sobre a lista de clientes
import java.util.Map; // Para manipulação de pares chave-valor

public class Servidor extends UnicastRemoteObject implements InterfaceServidor { // Implementa a interface do servidor

    private final ArrayList<InterfaceProc> clientesConectados = new ArrayList<>(); // Lista de clientes conectados
    private final Map<InterfaceProc, String> clienteNomes = new HashMap<>(); // Mapa cliente-nome

    public Servidor() throws RemoteException { // Construtor do servidor
        super();
    }

    @Override
    public void estabeleceConexao(InterfaceProc cliente, String nome) throws RemoteException { // Conecta um cliente
        // Verifica se o nome já está em uso
        for (String existingName : clienteNomes.values()) {
            if (existingName.equalsIgnoreCase(nome)) {
                throw new RemoteException("\n [ Nome já está em uso. Escolha outro nome. ]\n"); // Lança erro se nome duplicado
            }
        }

        clientesConectados.add(cliente); // Adiciona cliente à lista
        clienteNomes.put(cliente, nome); // Armazena nome associado ao cliente
        System.out.println("\n[ Cliente conectado ] -> " + nome); // Confirma a conexão
    }

    @Override
    public void liberaConexao(InterfaceProc cliente) throws RemoteException { // Desconecta um cliente
        String clientName = clienteNomes.get(cliente); // Obtém nome do cliente
        clienteNomes.remove(cliente); // Remove associação
        clientesConectados.remove(cliente); // Remove cliente da lista
        System.out.println("\n[ Cliente desconectado: ] -> " + clientName); // Confirma a desconexão
        ChatColors.removeColor(clientName); // Remove a cor associada
    }

    @Override
    public void sendToAll(String msgm, int idCliente, String nome) throws RemoteException { // Envia mensagens a todos os clientes
        Iterator<InterfaceProc> iterator = clientesConectados.iterator(); // Iterador para evitar ConcurrentModificationException

        while (iterator.hasNext()) {
            InterfaceProc cliente = iterator.next();
            try {
                //Thread.sleep(1000); // Atraso para simular envio
                cliente.atribuirMensagem(nome + ": " + msgm); // Envia a mensagem
            } catch (RemoteException e) {
                // Se o cliente não está acessível, removê-lo da lista
                System.out.println("\n[ Cliente desconectado: removendo da lista. ]"); // Confirma remoção
                iterator.remove(); // Remove cliente da lista
            }
        }
    }

    public static void main(String[] args) { // Método principal do servidor
        System.setProperty("java.security.policy", "policy.all"); // Define política de segurança
        try {
            java.rmi.registry.LocateRegistry.createRegistry(1099); // Cria o registro RMI

            String ipServidor = "10.1.45.167"; // IP do servidor

            Servidor servidor = new Servidor(); // Instancia o servidor

            java.rmi.Naming.rebind("rmi://" + ipServidor + "/ChatServidor", servidor); // Registra o servidor

            System.out.println("\n[ Servidor pronto no IP: ] -> " + ipServidor); // Confirma que o servidor está pronto
        } catch (Exception e) {
            System.out.println("\n[ Erro no servidor: ] -> " + e.getMessage()); // Trata erros
        }
    }
}
