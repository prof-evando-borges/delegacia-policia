package br.com.fiap.delegaciapolicia.rondas.repository;

import br.com.fiap.delegaciapolicia.rondas.entity.Ronda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RondaRepository extends JpaRepository<Ronda, String> {}
