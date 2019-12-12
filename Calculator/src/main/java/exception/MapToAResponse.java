package exception;

import enums.ErrorCode;
import pojos.response.ErrorResponse;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.ResourceBundle;

@Provider
public class MapToAResponse implements ExceptionMapper<CalculatorException> {

    public Response toResponse(CalculatorException exception) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("errorDetails");
        ErrorCode errorCode = exception.getErrorCode();
        return Response.status(errorCode.getHttpCode())
                .entity(new ErrorResponse(exception.getErrorCode().name(), resourceBundle.getString(errorCode.name())))
                .build();
    }
}
