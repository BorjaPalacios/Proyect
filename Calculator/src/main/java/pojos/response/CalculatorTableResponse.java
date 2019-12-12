package pojos.response;

public class CalculatorTableResponse {

    private Integer number;
    private Integer multiplier;
    private Integer result;

    public CalculatorTableResponse(Integer number, Integer multiplier, Integer result) {
        this.number = number;
        this.multiplier = multiplier;
        this.result = result;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Integer getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(Integer multiplier) {
        this.multiplier = multiplier;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
