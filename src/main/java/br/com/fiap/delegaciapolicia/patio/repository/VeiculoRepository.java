package br.com.fiap.delegaciapolicia.patio.repository;

import br.com.fiap.delegaciapolicia.patio.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculoRepository extends JpaRepository<Veiculo, String> {
}
