package br.com.fiap.delegaciapolicia.compartilhado.excecao;

/**
 * Lançada quando uma regra de negócio é violada.
 * O {@link TratadorGlobalDeExcecoes} converte em HTTP 422.
 */
public class RegraDeNegocioException extends RuntimeException {

    public RegraDeNegocioException(String mensagem) {
        super(mensagem);
    }
}
