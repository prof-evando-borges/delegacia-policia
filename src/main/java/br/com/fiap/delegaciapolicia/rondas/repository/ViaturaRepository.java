package br.com.fiap.delegaciapolicia.rondas.repository;

import br.com.fiap.delegaciapolicia.rondas.entity.Viatura;
import br.com.fiap.delegaciapolicia.rondas.entity.ViaturaStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ViaturaRepository extends JpaRepository<Viatura, String> {
    Optional<Viatura> findByPrefixo(String prefixo);
    Optional<Viatura> findByPlaca(String placa);
    List<Viatura> findByStatus(ViaturaStatus status);
}
