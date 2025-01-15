import java.net.http.*;
import java.net.URI;

public class APIClient {

    private static final String API_URL = "https://v6.exchangerate-api.com/v6/bf424c82dcb234b97b80123d/latest/BRL";

    public static String getRates() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return response.body();
        } else {
            throw new RuntimeException("Erro ao obter dados da API: " + response.statusCode());
        }
    }
}
