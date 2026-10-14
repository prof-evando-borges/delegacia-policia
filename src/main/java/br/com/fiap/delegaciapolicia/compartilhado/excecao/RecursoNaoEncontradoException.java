package br.com.fiap.delegaciapolicia.compartilhado.excecao;

/**
 * Lançada quando um registro procurado não existe.
 * O {@link TratadorGlobalDeExcecoes} converte em HTTP 404.
 */
public class RecursoNaoEncontradoException extends RuntimeException {

    public RecursoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
