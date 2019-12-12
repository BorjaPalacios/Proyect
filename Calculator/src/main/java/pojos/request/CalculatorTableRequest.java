package pojos.request;

public class CalculatorTableRequest {

    private Integer number;
    private Integer limit;


    public CalculatorTableRequest(Integer number, Integer limit) {
        this.number = number;
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
