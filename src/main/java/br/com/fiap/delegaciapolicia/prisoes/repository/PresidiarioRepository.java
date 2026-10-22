package br.com.fiap.delegaciapolicia.prisoes.repository;

import br.com.fiap.delegaciapolicia.prisoes.domain.entity.Presidiario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PresidiarioRepository extends JpaRepository<Presidiario, UUID> {
    Optional<Presidiario> findByCpf(String cpf);
    boolean existsByCpf(String cpf);
}
