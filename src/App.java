import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {

    public static void main(String[] args) throws Exception {

        // Fazer uma conexão HTTP e buscar os top 250 filmes

//        List<Conteudo> conteudos = ApiUrlEnum.API_NASA.getExtrator().extraiConteudos(ClienteHttp.buscaDados(ApiUrlEnum.API_NASA.getUrl()));
        List<Conteudo> conteudos = ApiUrlEnum.API_IMDB.getExtrator().extraiConteudos(ClienteHttp.buscaDados(ApiUrlEnum.API_IMDB.getUrl()));

        for (Conteudo conteudo : conteudos) {

            String frase = conteudo.titulo();
            String nomeArquivo = conteudo.titulo().replace(":", "-") + ".png";
            InputStream inputStream = new URL(conteudo.urlImagem()).openStream();
            StickerGenerator stickerGenerator = new StickerGenerator();
            stickerGenerator.cria(inputStream, nomeArquivo, frase);
            System.out.println(conteudo.titulo());

        }
    }
}