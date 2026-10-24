package br.com.fiap.delegaciapolicia.fichas.repository;

import br.com.fiap.delegaciapolicia.fichas.entity.Criminal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CriminalRepository extends JpaRepository<Criminal, String> {
}