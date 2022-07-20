import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws Exception {

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

            String url = filme.get("image");
            String titulo = filme.get("title").replace(":", "-") + ".png";
            Double notaFilme = Double.parseDouble(filme.get("imDbRating"));
            String frase = "TEXTO";
            InputStream inputStream = new URL(url).openStream();
            StickerGenerator stickerGenerator = new StickerGenerator();
            stickerGenerator.cria(inputStream, titulo, notaFilme, frase);

            System.out.println("Title: " + filme.get("title"));
        }
    }
}
