package br.com.fiap.delegaciapolicia.prisoes.repository;

import br.com.fiap.delegaciapolicia.prisoes.entity.Cela;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CelaRepository extends JpaRepository<Cela, String> {
}
