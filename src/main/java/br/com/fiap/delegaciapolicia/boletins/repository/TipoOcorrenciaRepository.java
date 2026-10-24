package br.com.fiap.delegaciapolicia.boletins.repository;

import br.com.fiap.delegaciapolicia.boletins.entity.TipoOcorrencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoOcorrenciaRepository extends JpaRepository<TipoOcorrencia, String> {
}
