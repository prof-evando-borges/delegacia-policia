package br.com.fiap.delegaciapolicia.patio.exception;

public class PatioException extends RuntimeException {

    public PatioException(String mensagem) {
        super(mensagem);
    }

    public PatioException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
