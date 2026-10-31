package br.com.fiap.delegaciapolicia.estoque.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "br.com.fiap.delegaciapolicia.estoque")
public class EstoqueExceptionHandler {

    @ExceptionHandler(EstoqueException.class)
    public ProblemDetail tratarEstoque(EstoqueException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(422), ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail tratarArgumentoInvalido(IllegalArgumentException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
}
