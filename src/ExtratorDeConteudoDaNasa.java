import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorDeConteudoDaNasa implements ExtratorDeConteudo {

    public List<Conteudo> extraiConteudos(String json) {
        // Pegar só os dados que interessam (titulo, poster, nota)
        JsonParser jsonParser = new JsonParser();
        List<Map<String, String>> listaDeAtributos = jsonParser.parse(json);

        // Converter a lista de atributos em uma lista de conteudos
        List<Conteudo> conteudos = new ArrayList<>();
        for (Map<String, String> atributos : listaDeAtributos) {
            Double nota = 0.0;
            Conteudo conteudo = new Conteudo(atributos.get("title"), atributos.get("url"), nota, atributos.get("date"));
            conteudos.add(conteudo);
        }
        return conteudos;
    }

}
