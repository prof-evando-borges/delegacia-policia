package br.com.fiap.delegaciapolicia.chamadas.repository;


import br.com.fiap.delegaciapolicia.chamadas.entity.Chamada;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChamadaRepository extends JpaRepository<Chamada, String> {
}
