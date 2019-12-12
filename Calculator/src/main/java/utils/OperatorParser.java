package utils;

import enums.OperatorType;


public class OperatorParser {


    public static OperatorType parse(String operator) {

        OperatorType operatorType;

        switch (operator) {
            case "+":
            case "ADD":
                operatorType = OperatorType.ADD;
                break;
            case "-":
            case "SUBTRACT":
                operatorType = OperatorType.SUBTRACT;
                break;
            case "*":
            case "MULTIPLY":
                operatorType = OperatorType.MULTIPLY;
                break;
            case "^":
            case "POWER":
                operatorType =OperatorType.POWER;
                break;
            case "√":
            case "ROOT":
                operatorType = OperatorType.ROOT;
                break;
            case "%":
            case "PERCENTAGE":
                operatorType = OperatorType.PERCENTAGE;
                break;
            case "log":
            case "LOG":
                operatorType = OperatorType.LOG;
                break;
            case "sin":
            case "SIN":
                operatorType = OperatorType.SIN;
                break;
            case "cos":
            case "COS":
                operatorType = OperatorType.COS;
                break;
            case "tan":
            case "TAN":
                operatorType = OperatorType.TAN;
                break;
            case "!":
            case "FACTORIAL":
                operatorType = OperatorType.FACTORIAL;
            default:
                operatorType = OperatorType.DIVIDE;
                break;
        }

        return operatorType;
    }

    public static String valueOf(OperatorType operator) {

        switch (operator) {
            case ADD:
                return  "+";
            case SUBTRACT:
                return "-";
            case MULTIPLY:
                return  "*";
            case POWER:
                return "^";
            case ROOT:
                return "root";
            case PERCENTAGE:
                return "%";
            case LOG:
                return "log";
            case SIN:
                return "sin";
            case COS:
                return "cos";
            case TAN:
                return "tan";
            case FACTORIAL:
                return "!";
            default:
                return "/";
        }
    }

    public static String toWord(String operator){

        switch (operator){
            case "+":
                return "ADD";
            case "-":
                return "SUBTRACT";
            case "*":
                return "MULTIPLY";
            case "^":
                return "POWER";
            case "root":
                return "ROOT";
            case "%":
                return "PERCENTAGE";
            case  "log":
                return "LOG";
            case "sin":
                return "SIN";
            case "cos":
                return "COS";
            case "tan":
                return "TAN";
            case "!":
                return "FACTORIAL";
            default:
                return "DIVIDE";
        }
    }
}
