package pojos.response;

public class CalculatorResponse {

    private Long id;
    private double result;

    public CalculatorResponse(Long id, double result) {

        this.id = id;
        this.result = result;
    }

    public Long getId(){ return id; }

    public double getResult() {
        return result;
    }
}
