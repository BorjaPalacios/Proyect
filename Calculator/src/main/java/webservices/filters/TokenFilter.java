package webservices.filters;

import annotations.Token;
import enums.ErrorCode;
import exception.CalculatorException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;


@Provider
@Token
public class TokenFilter implements ContainerRequestFilter {


    @Override
    public void filter(ContainerRequestContext request) throws IOException {


        //String Utils
        if(request.getHeaderString("token") == null || !("hello").equals(request.getHeaderString("token"))) {
            throw new CalculatorException(ErrorCode.UNAUTHORIZED);
        }




    }

}
