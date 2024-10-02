import java.rmi.Remote; // Permite chamadas remotas
import java.rmi.RemoteException; // Tratamento de exceções remotas

public interface InterfaceProc extends Remote { // Interface para o processo do cliente
    void atribuirMensagem(String mensagem) throws RemoteException; // Método para receber mensagens
}
