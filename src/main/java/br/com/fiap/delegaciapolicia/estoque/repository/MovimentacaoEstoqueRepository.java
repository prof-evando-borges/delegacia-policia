package br.com.fiap.delegaciapolicia.estoque.repository;

import br.com.fiap.delegaciapolicia.estoque.entity.MovimentacaoEstoque;
import br.com.fiap.delegaciapolicia.estoque.entity.TipoMovimentacao;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimentacaoEstoqueRepository extends JpaRepository<MovimentacaoEstoque, String> {

    boolean existsByItemId(String idItem);

    List<MovimentacaoEstoque> findByItemId(String idItem);

    List<MovimentacaoEstoque> findByIdPolicial(String idPolicial);

    List<MovimentacaoEstoque> findByTipo(TipoMovimentacao tipo);

    List<MovimentacaoEstoque> findByDataMovimentacaoBetween(LocalDateTime inicio, LocalDateTime fim);
}
