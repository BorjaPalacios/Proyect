package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "calculator")
@NamedQueries({
        @NamedQuery(name = CalculatorRecordsEntity.GET_ALL_RECORDS, query = "SELECT c FROM CalculatorRecordsEntity c order by c.id desc"),
        @NamedQuery(name = CalculatorRecordsEntity.GET_SINGLE_RECORD, query = "SELECT c FROM CalculatorRecordsEntity c WHERE c.id = :id"),
        @NamedQuery(name = CalculatorRecordsEntity.GET_OPERATIONTYPE_RECORDS, query = "SELECT c FROM CalculatorRecordsEntity c WHERE c.operator = :operator")
})
public class CalculatorRecordsEntity implements Serializable {

    public static final String GET_ALL_RECORDS = "getAllRecords";
    public static final String GET_SINGLE_RECORD = "getSingleRecord";
    public static final String GET_OPERATIONTYPE_RECORDS = "getOperationTypeRecords";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "input1")
    private double input1;

    @Column(name = "input2")
    private double input2;

    @Column(name = "result")
    private double result;

    @Column(name = "operator")
    private String operator;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "operatorDate")
    private Date operatorDate;


    public CalculatorRecordsEntity() {

    }

    public CalculatorRecordsEntity(double input1, double input2, double result, String operator) {
        this.input1 = input1;
        this.input2 = input2;
        this.result = result;
        this.operator = operator;
        this.operatorDate = new Date();
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
        return operatorDate;
    }

    public void setDateOperator(Date dateOperator) {
        this.operatorDate = dateOperator;
    }

}
