package br.com.fiap.delegaciapolicia.chamadas.repository;

import br.com.fiap.delegaciapolicia.chamadas.entity.Atendente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtendenteRepository extends JpaRepository<Atendente, String> {
}
