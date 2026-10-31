package br.com.fiap.delegaciapolicia.estoque.exception;

public class EstoqueException extends RuntimeException {

    public EstoqueException(String mensagem) {
        super(mensagem);
    }

    public EstoqueException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
