import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

import static java.lang.Double.parseDouble;

public class App {

    public static void main(String[] args) throws IOException, InterruptedException {

        // Fazer uma conexão HTTP e buscar os top 250 filmes
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://raw.githubusercontent.com/alura-cursos/imersao-java/api/TopMovies.json"))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        // Pegar só os dados que interessam (titulo, poster, nota)
        JsonParser jsonParser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = jsonParser.parse(response.body());

        // Exibir os dados
        for (Map<String, String> filme : listaDeFilmes) {
            System.out.println("Title: " + filme.get("title"));
            System.out.println("Poster: " + filme.get("image"));
            System.out.println("Rating: " + filme.get("imDbRating"));
            double estrelas = parseDouble(filme.get("imDbRating"));
            for (int i = 0; i < Math.round(estrelas); i++) {
                System.out.print("🌟");
            }
            System.out.println();
        }
    }
}
