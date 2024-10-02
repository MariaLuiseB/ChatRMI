import java.rmi.RemoteException; // Tratamento de exceções remotas
import java.rmi.server.UnicastRemoteObject; // Permite a implementação de objetos remotos
import java.util.Scanner; // Para entrada do usuário

public class Proc extends UnicastRemoteObject implements InterfaceProc { // Implementa a interface do cliente
    private static final String EMOJI = "📩 "; // Emoji de cartinha

    protected Proc() throws RemoteException { // Construtor do cliente
        super();
    }

    @Override
    public void atribuirMensagem(String mensagem) throws RemoteException {
        // Limpa a linha anterior
        System.out.print("\033[F"); // Retorna ao início da linha
        // Exibe a nova mensagem
        String clientName = mensagem.split(":")[0]; // Extrai o nome do cliente
        String cor = ChatColors.getColor(clientName); // Obtém cor do cliente
        System.out.print(cor + mensagem + ChatColors.reset()); // Imprime a mensagem
        System.out.println("\nDIGITE A MENSAGEM:");
    }


    public static void main(String[] args) {
        try {
            String ipServidor = "192.168.1.19"; // IP do servidor
            InterfaceServidor servidor = (InterfaceServidor) java.rmi.Naming.lookup("rmi://" + ipServidor + "/ChatServidor"); // Conecta ao servidor

            Proc cliente = new Proc(); // Instancia o cliente
            Scanner scanner = new Scanner(System.in); // Cria scanner para entrada
            String nome;

            // Loop até obter um nome válido
            while (true) {
                System.out.println("\nDIGITE SEU NOME: ");
                nome = scanner.nextLine(); // Lê o nome

                // Adiciona o emoji ao nome do cliente
                nome = EMOJI + nome;
                try {
                    servidor.estabeleceConexao(cliente, nome); // Tenta conectar o cliente
                    break; // Sai do loop se a conexão for bem-sucedida
                } catch (RemoteException e) {
                    System.out.println(e.getMessage()); // Mostra mensagem de erro
                }
            }

            System.out.println("\nDIGITE A MENSAGEM:");
            while (true) {
                String mensagem = scanner.nextLine(); // Lê a mensagem

                if (mensagem.equalsIgnoreCase("sair")) { // Verifica se o cliente quer sair
                    servidor.liberaConexao(cliente); // Desconecta o cliente
                    break;
                }
                servidor.sendToAll(mensagem, 0, nome); // Envia mensagem para todos
            }
        } catch (Exception e) {
            System.out.println("\n[ Erro no cliente: ] " + e.getMessage()); // Trata erros
        }
    }
}
