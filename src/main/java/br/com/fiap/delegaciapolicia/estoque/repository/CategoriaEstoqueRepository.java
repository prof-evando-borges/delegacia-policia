package br.com.fiap.delegaciapolicia.estoque.repository;

import br.com.fiap.delegaciapolicia.estoque.entity.CategoriaEstoque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaEstoqueRepository extends JpaRepository<CategoriaEstoque, String> {

    boolean existsByNomeIgnoreCase(String nome);

    boolean existsByNomeIgnoreCaseAndIdNot(String nome, String id);
}
