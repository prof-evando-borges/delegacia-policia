package br.com.fiap.delegaciapolicia.chamadas.repository;

import br.com.fiap.delegaciapolicia.chamadas.entity.Solicitante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitanteRepository
    extends JpaRepository<Solicitante, String> {
}
