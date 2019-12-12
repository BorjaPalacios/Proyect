package pojos.response;

public class WordResponse {

    private Long id;
    private String result;

    public WordResponse(Long id, String result) {
        this.id = id;
        this.result = result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
