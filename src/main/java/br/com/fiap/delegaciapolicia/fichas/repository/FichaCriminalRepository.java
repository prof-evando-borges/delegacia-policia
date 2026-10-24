package br.com.fiap.delegaciapolicia.fichas.repository;

import br.com.fiap.delegaciapolicia.fichas.entity.FichaCriminal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FichaCriminalRepository extends JpaRepository<FichaCriminal, String> {
    List<FichaCriminal> findByCriminal_Id(String idCriminal);
}
