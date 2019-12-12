package enums;

public enum ErrorCode {
    BAD_BODY(400),
    MISSING_PARAM(400),
    ERROR_PARAM( 400),
    DIVIDE_ZERO(400),
    DOUBLE_NUMBER(400),
    INDICE_ZERO(400),
    UNDERTERMINED(400),
    LOG_NUMBER(400),
    NEGATIVE_NUMBER(400),
    BAD_OPERATOR(400),
    ERROR_SERVICE(400),
    UNKNOW_ERROR(500),
    NOT_FOUND(404),
    UNAUTHORIZED(401),
    FORBIDDEN_ERROR(403);

    int httpCode;

    ErrorCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public int getHttpCode() {
        return httpCode;
    }
}