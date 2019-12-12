package pojos.records;

import entities.CalculatorRecordsEntity;
import utils.OperatorParser;

import java.util.Date;

public class CalculatorRecordsPojo {
    private Long id;

    private double input1;

    private double input2;

    private double result;

    private String operator;

    private Date dateOperator;

    public CalculatorRecordsPojo(){

    }

    public CalculatorRecordsPojo(CalculatorRecordsEntity entity) {
        this.id = entity.getId();
        this.input1 = entity.getInput1();
        this.input2 = entity.getInput2();
        this.result = entity.getResult();
        this.operator = OperatorParser.toWord(entity.getOperator());
        this.dateOperator = entity.getDateOperator();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getInput1() {
        return input1;
    }

    public void setInput1(double input1) {
        this.input1 = input1;
    }

    public double getInput2() {
        return input2;
    }

    public void setInput2(double input2) {
        this.input2 = input2;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getDateOperator() {
        return dateOperator;
    }

    public void setDateOperator(Date dateOperator) {
        this.dateOperator = dateOperator;
    }
}
