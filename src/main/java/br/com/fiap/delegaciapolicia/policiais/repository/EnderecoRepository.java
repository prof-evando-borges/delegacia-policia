package br.com.fiap.delegaciapolicia.policiais.repository;

import br.com.fiap.delegaciapolicia.policiais.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, String> {
}
