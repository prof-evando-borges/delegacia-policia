package br.com.fiap.delegaciapolicia.rondas.repository;

import br.com.fiap.delegaciapolicia.rondas.entity.RondaPolicial;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RondaPolicialRepository extends JpaRepository<RondaPolicial, Long> {
    List<RondaPolicial> findByRonda_Id(Long rondaId);
    List<RondaPolicial> findByIdPolicial(Long idPolicial);
}
