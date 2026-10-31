package br.com.fiap.delegaciapolicia.patio.repository;

import br.com.fiap.delegaciapolicia.patio.entity.VagaPatio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VagaPatioRepository extends JpaRepository<VagaPatio, String> {

    boolean existsByCodigo(String codigo);

    boolean existsByCodigoAndIdNot(String codigo, String id);

    List<VagaPatio> findByOcupada(Boolean ocupada);
}
