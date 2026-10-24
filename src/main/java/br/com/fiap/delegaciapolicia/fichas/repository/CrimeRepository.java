package br.com.fiap.delegaciapolicia.fichas.repository;

import br.com.fiap.delegaciapolicia.fichas.entity.Crime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrimeRepository extends JpaRepository<Crime, String> {
}
