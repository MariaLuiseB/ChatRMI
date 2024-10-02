import java.util.ArrayList; // Lista de cores disponíveis
import java.util.HashMap; // Mapa para associar nomes a cores
import java.util.List; // Lista para manipulação de cores
import java.util.Map; // Para manipulação de pares chave-valor

public class ChatColors { // Classe para gerenciar cores de mensagens
    private static final String RESET = "\033[0m"; // Cor padrão
    private static final String[] COLORS = { // Cores disponíveis
            "\033[31m", // Vermelho
            "\033[32m", // Verde
            "\033[33m", // Amarelo
            "\033[34m", // Azul
            "\033[35m", // Magenta
            "\033[36m", // Ciano
            "\033[37m"  // Branco
    };

    private static final List<String> availableColors = new ArrayList<>(List.of(COLORS)); // Lista de cores disponíveis
    private static final Map<String, String> clientColors = new HashMap<>(); // Mapa de cores por cliente

    public static String getColor(String clientName) { // Atribui cor ao cliente
        if (!clientColors.containsKey(clientName)) { // Se não tiver cor atribuída
            if (!availableColors.isEmpty()) {
                String color = availableColors.remove(0); // Remove uma cor da lista
                clientColors.put(clientName, color); // Atribui a cor ao cliente
            } else {
                return RESET; // Retorna cor padrão se sem cores
            }
        }
        return clientColors.get(clientName); // Retorna cor do cliente
    }

    public static String reset() { // Retorna cor padrão
        return RESET;
    }

    public static void removeColor(String clientName) { // Remove cor de um cliente
        clientColors.remove(clientName); // Remove do mapa
        // Adiciona a cor de volta à lista de cores disponíveis
        String color = clientColors.get(clientName);
        if (color != null) {
            availableColors.add(color); // Reinsere a cor na lista
        }
    }
}
