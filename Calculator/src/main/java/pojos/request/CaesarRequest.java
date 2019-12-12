package pojos.request;

public class CaesarRequest {

    private String phrase;
    private Integer convert;

    public CaesarRequest(String phrase,  Integer convert) {
        this.phrase = phrase;
        this.convert = convert;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public Integer getConvert() {
        return convert;
    }

    public void setConvert(Integer convert) {
        this.convert = convert;
    }
}
