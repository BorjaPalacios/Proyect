package pojos.request;

public class CalculatorRequest {

    private Double input1;
    private Double input2;

    public CalculatorRequest(Double input1, Double input2) {
        this.input1 = input1;
        this.input2 = input2;
    }

    public Double getInput1() {
        return input1;
    }

    public Double getInput2() {
        return input2;
    }


}