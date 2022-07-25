public enum ApiUrlEnum {

    API_IMDB("https://api.mocki.io/v2/549a5d8b/Top250Movies", new ExtratorDeConteudoDoIMDB()),
    API_NASA("https://api.mocki.io/v2/549a5d8b/NASA-APOD", new ExtratorDeConteudoDaNasa()),
    API_LINGUAGENS("https://linguagensapi-alura.herokuapp.com/linguagens", new ExtratorDeConteudoDoLinguagens());

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
