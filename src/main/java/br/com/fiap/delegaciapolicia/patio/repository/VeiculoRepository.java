package br.com.fiap.delegaciapolicia.patio.repository;

import br.com.fiap.delegaciapolicia.patio.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VeiculoRepository extends JpaRepository<Veiculo, String> {

    boolean existsByPlacaAndDataSaidaIsNull(String placa);

    boolean existsByPlacaAndDataSaidaIsNullAndIdNot(String placa, String id);

    boolean existsByVagaId(String idVaga);

    List<Veiculo> findByPlaca(String placa);

    List<Veiculo> findByDataSaidaIsNull();
}
