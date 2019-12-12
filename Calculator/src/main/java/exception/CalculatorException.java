package exception;

import enums.ErrorCode;

import java.io.IOException;

public class CalculatorException extends IOException {


    private ErrorCode errorCode;

    public CalculatorException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
