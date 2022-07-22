public enum ApiUrlEnum {

    API_IMDB("https://raw.githubusercontent.com/alura-cursos/imersao-java/api/TopMovies.json", new ExtratorDeConteudoDoIMDB()),
    API_NASA("https://api.mocki.io/v2/549a5d8b/NASA-APOD", new ExtratorDeConteudoDaNasa());

    private String url;
    private ExtratorDeConteudo extrator;

    ApiUrlEnum(String url, ExtratorDeConteudo extrator) {
        this.url = url;
        this.extrator = extrator;
    }

    public String getUrl() {
        return url;
    }

    public ExtratorDeConteudo getExtrator() {
        return extrator;
    }

}
