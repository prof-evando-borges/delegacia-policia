package br.com.fiap.delegaciapolicia.estoque.repository;

import br.com.fiap.delegaciapolicia.estoque.entity.ItemEstoque;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemEstoqueRepository extends JpaRepository<ItemEstoque, String> {

    boolean existsByCodigo(String codigo);

    boolean existsByCodigoAndIdNot(String codigo, String id);

    boolean existsByCategoriaId(String idCategoria);

    List<ItemEstoque> findByNomeContainingIgnoreCase(String nome);

    List<ItemEstoque> findByCategoriaId(String idCategoria);

    List<ItemEstoque> findByAtivoTrue();

    List<ItemEstoque> findByQuantidadeLessThanEqual(Integer quantidade);
}
