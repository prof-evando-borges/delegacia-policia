package br.com.fiap.delegaciapolicia.boletins.repository;

import br.com.fiap.delegaciapolicia.boletins.entity.RegistroBO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistroBORepository extends JpaRepository<RegistroBO, String> {
}
