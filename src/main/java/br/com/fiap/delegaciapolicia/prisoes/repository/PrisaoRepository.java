package br.com.fiap.delegaciapolicia.prisoes.repository;

import br.com.fiap.delegaciapolicia.prisoes.entity.Presidiario;
import br.com.fiap.delegaciapolicia.prisoes.entity.Prisao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.UUID;

public interface PrisaoRepository extends JpaRepository<Prisao, UUID> {
    Page<Prisao> findAllByPresoId(Pageable pageable, UUID presidiarioId);
}
