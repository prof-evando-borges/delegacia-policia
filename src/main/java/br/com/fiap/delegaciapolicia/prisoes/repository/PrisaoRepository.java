package br.com.fiap.delegaciapolicia.prisoes.repository;

import br.com.fiap.delegaciapolicia.prisoes.entity.Prisao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrisaoRepository extends JpaRepository<Prisao, String> {
    Page<Prisao> findAllByIdCriminal(String idCriminal, Pageable pageable);
}
