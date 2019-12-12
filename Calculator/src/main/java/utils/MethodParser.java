package utils;

import enums.MethodType;

public class MethodParser {

    public static MethodType parse(String method) {

        switch (method){
            case "LOWER":
                return MethodType.LOWER;
            case "CAMEL":
                return MethodType.CAMEL;
            case "FROMCAMEL":
                return MethodType.FCAMEL;
            case "CAESAR":
                return MethodType.CAESAR;
            case "ERASE":
                return MethodType.ERASE;
            default:
                return MethodType.UPPER;
        }
    }


    public static String valueOf(MethodType method){

        switch (method){
            case LOWER:
                return "lower";
            case CAMEL:
                return "camel";
            case FCAMEL:
                return "fromcamel";
            case CAESAR:
                return "caesar";
            case ERASE:
                return "erase";
            default:
                return "upper";
        }
    }

}
