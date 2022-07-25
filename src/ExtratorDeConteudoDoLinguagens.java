import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorDeConteudoDoLinguagens implements ExtratorDeConteudo {

    public List<Conteudo> extraiConteudos(String json) {

        // Pegar só os dados que interessam (titulo, poster, nota)
        JsonParser jsonParser = new JsonParser();
        List<Map<String, String>> listaDeAtributos = jsonParser.parse(json);

        // Converter a lista de atributos em uma lista de conteudos
        List<Conteudo> conteudos = new ArrayList<>();
        for (Map<String, String> atributos : listaDeAtributos) {
            String ranking = atributos.get("ranking");
            Conteudo conteudo = new Conteudo(atributos.get("title"), atributos.get("image").replaceAll("(@+)(.*).jpg$", "$1.jpg"), 0.0, null, ranking);
            conteudos.add(conteudo);
        }
        return conteudos;
    }

}