package br.com.fiap.delegaciapolicia.prisoes.repository;

import br.com.fiap.delegaciapolicia.prisoes.entity.Cela;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CelaRepository extends JpaRepository<Cela, UUID> {
}
