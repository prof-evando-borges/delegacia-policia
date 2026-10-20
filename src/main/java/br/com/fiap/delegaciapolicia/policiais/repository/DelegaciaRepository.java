package br.com.fiap.delegaciapolicia.policiais.repository;

import br.com.fiap.delegaciapolicia.policiais.entity.Delegacia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DelegaciaRepository extends JpaRepository<Delegacia, Long> {
}
