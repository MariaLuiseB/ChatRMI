import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceServidor extends Remote {
    /**
     * Responsável por estabelecer a conexão entre o cliente e o servidor,
     * Recebe o objeto InterfaceProc do cliente e o nome do cliente.
     * O servidor verifica se o nome está  em uso e, se n o estiver,
     * adiciona o cliente na lista de clientes conectados.
     */
    void estabeleceConexao(InterfaceProc cliente, String nome) throws RemoteException;
    /**
     * Responsável por liberar a conexão entre o cliente e o servidor,
     * Recebe o objeto InterfaceProc do cliente e remove o cliente
     * da lista de clientes conectados.
     */

    void liberaConexao(InterfaceProc cliente) throws RemoteException;
    /**
     * Responsável por enviar mensagens para todos os clientes conectados,
     * Recebe a mensagem, o id do cliente que enviou a mensagem e o nome do cliente que enviou a mensagem.
     */
    void sendToAll(String msgm, int idCliente, String nome) throws RemoteException;
}
