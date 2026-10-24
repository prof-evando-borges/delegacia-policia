package br.com.fiap.delegaciapolicia.rondas.repository;

import br.com.fiap.delegaciapolicia.rondas.entity.RondaViatura;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RondaViaturaRepository extends JpaRepository<RondaViatura, Long> {
    List<RondaViatura> findByRonda_Id(Long rondaId);
}
