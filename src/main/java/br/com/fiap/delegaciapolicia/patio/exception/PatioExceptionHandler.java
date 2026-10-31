package br.com.fiap.delegaciapolicia.patio.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "br.com.fiap.delegaciapolicia.patio")
public class PatioExceptionHandler {

    @ExceptionHandler(PatioException.class)
    public ProblemDetail tratarPatioException(PatioException ex) {
        // 422 Unprocessable Content
        return ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(422), ex.getMessage());
    }
}
