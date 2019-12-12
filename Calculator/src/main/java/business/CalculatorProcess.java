package business;

import database.CalculatorDbBean;
import enums.ErrorCode;
import enums.OperatorType;
import exception.CalculatorException;
import org.apache.log4j.Logger;
import pojos.records.CalculatorRecordsPojo;
import pojos.response.CalculatorTableResponse;
import singletons.CalculatorSingleton;
import utils.OperatorParser;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Stateless(name = CalculatorProcess.BEAN_NAME)
public class CalculatorProcess {

    public static final String BEAN_NAME = "calculatorProcess";

    @Inject
    private CalculatorDbBean calculatorDbBean;

    @Inject
    private CalculatorSingleton calculatorSingleton;

    @Inject
    private Logger logger;
    
    public CalculatorRecordsPojo calcule(Double input1, Double input2, OperatorType operator) throws Exception{

        Double result ;
        CalculatorRecordsPojo pojo ;

        try {
            if (operator == OperatorType.ADD) result = input1 + input2;
            else if (operator == OperatorType.SUBTRACT) result = input1 - input2;
            else if (operator == OperatorType.MULTIPLY) result = input1 * input2;
            else if (operator == OperatorType.POWER) result = Math.pow(input1, input2);
            else if (operator == OperatorType.ROOT) result = Math.pow(input1, 1/input2);
            else if (operator == OperatorType.PERCENTAGE) result = input1 * (input2/100);
            else if (operator == OperatorType.LOG) result = Math.log10(input1)/Math.log10(input2);
            else if (operator == OperatorType.SIN){
                result = Math.sin(input1);
                input1 = Math.toDegrees(input1);
            }
            else if (operator == OperatorType.COS){
                result = Math.cos(input1);
                input1 = Math.toDegrees(input1);
            }
            else if (operator == OperatorType.FACTORIAL) result = factorial(input1);
            else if (operator == OperatorType.TAN){
                result = Math.tan(input1);
                input1 = Math.toDegrees(input1);
            }
            else result = input1 / input2;

            input1 = new BigDecimal(input1).setScale(4, BigDecimal.ROUND_HALF_DOWN).doubleValue();
            input2 = new BigDecimal(input2).setScale(4, BigDecimal.ROUND_HALF_DOWN).doubleValue();
            result = new BigDecimal(result).setScale(4, BigDecimal.ROUND_HALF_DOWN).doubleValue();

            pojo = calculatorDbBean.createRecord(input1,input2,result, OperatorParser.valueOf(operator));
            calculatorSingleton.increase();

            logger.info("operations " + calculatorSingleton.getOperationsInServer());

        } catch (Exception e){
            logger.error("Error: " + e.getMessage());
            throw new CalculatorException(ErrorCode.UNKNOW_ERROR);
        }

        return pojo;
    }

    public List<CalculatorTableResponse> table(Integer number, Integer limit) throws Exception{
        try{
            List<CalculatorTableResponse> responses = new ArrayList<>();

            for(int i = 1; i <= limit; i++){
                CalculatorTableResponse response = new CalculatorTableResponse(number,i,number*i);
                responses.add(response);
            }

            return responses;
        }catch (Exception e){
            throw new CalculatorException(ErrorCode.UNKNOW_ERROR);
        }
    }

    public List<CalculatorRecordsPojo> getAllRecords(Integer limit, Integer offset) throws Exception {
        try {
            return calculatorDbBean.getAllRecords(limit, offset);
        } catch (Exception e){
            logger.error("Error getting all records: " + e.getMessage());
            throw new CalculatorException(ErrorCode.UNKNOW_ERROR);
        }
    }

    public List<CalculatorRecordsPojo> getOperationTypeRecords(OperatorType operator, Integer limit, Integer offset) throws Exception {
        try {
            return calculatorDbBean.getOperationTypeRecords(operator, limit, offset);
        } catch (Exception e){
            logger.error("Error getting all records: " + e.getMessage());
            throw new CalculatorException(ErrorCode.UNKNOW_ERROR);
        }
    }

    public CalculatorRecordsPojo getSingleRecord (Long id) throws Exception {
        try {
            CalculatorRecordsPojo record = calculatorDbBean.getSingleRecord(id);
            if (record == null) throw new NoResultException();
            return record;

        }catch (NoResultException nr){
            throw new CalculatorException(ErrorCode.NOT_FOUND);
        }catch (Exception e){
            throw e;
        }
    }

    public boolean removeRecord (Long id) throws Exception{
        try{
            return calculatorDbBean.removeRecord(id);

        } catch (NoResultException nr) {
            throw new CalculatorException(ErrorCode.NOT_FOUND);
        }catch (Exception e){
            throw new CalculatorException(ErrorCode.UNKNOW_ERROR);
        }

    }

    public Double factorial (Double input) throws Exception{
        try{
            if(input - input.intValue() != 0){
                throw new CalculatorException(ErrorCode.DOUBLE_NUMBER);
            }
            Double factorial = 1.0;
            for(double i = input; i > 0; i--){
                factorial = factorial * i;
            }

            return factorial;

        }catch (Exception e){
            throw new CalculatorException(ErrorCode.UNKNOW_ERROR);
        }
    }

}
