package br.com.fiap.delegaciapolicia.chamadas.repository;

import br.com.fiap.delegaciapolicia.chamadas.entity.Chamada;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChamadaRepository extends JpaRepository<Chamada, Long> {

    Optional<Chamada> findByProtocolo(String protocolo);
}
