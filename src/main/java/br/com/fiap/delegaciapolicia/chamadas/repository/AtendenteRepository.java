package br.com.fiap.delegaciapolicia.chamadas.repository;

import br.com.fiap.delegaciapolicia.chamadas.entity.Atendente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AtendenteRepository extends JpaRepository<Atendente, String> {
    Optional<Atendente> findByIdPolicial(String idPolicial);
}
