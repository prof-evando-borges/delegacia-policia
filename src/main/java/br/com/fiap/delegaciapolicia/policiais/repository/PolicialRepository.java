package br.com.fiap.delegaciapolicia.policiais.repository;

import br.com.fiap.delegaciapolicia.policiais.entity.Policial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PolicialRepository extends JpaRepository<Policial, Long> {
}
