package br.com.fiap.delegaciapolicia.prisoes.service;

import br.com.fiap.delegaciapolicia.prisoes.entity.Cela;
import br.com.fiap.delegaciapolicia.prisoes.entity.Prisao;
import br.com.fiap.delegaciapolicia.prisoes.entity.enums.PrisaoStatus;
import br.com.fiap.delegaciapolicia.prisoes.repository.PrisaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PrisaoService {

    private final PrisaoRepository prisaoRepository;

    @Transactional
    public Prisao registrar(Prisao prisao) {
        return prisaoRepository.save(prisao);
    }

    public Prisao buscarPorId(String id) {
        return prisaoRepository.findById(id).orElse(null);
    }

    public Page<Prisao> listar(Pageable pageable) {
        return prisaoRepository.findAll(pageable);
    }

    /** Prisoes de uma pessoa (id de TB_FIC_CRIMINAL, modulo 02). */
    public Page<Prisao> listarPorPreso(Pageable pageable, String idCriminal) {
        return prisaoRepository.findAllByIdCriminal(idCriminal, pageable);
    }

    @Transactional
    public Prisao atualizar(PrisaoStatus status, String id) {
        Prisao prisao = prisaoRepository.findById(id).orElseThrow();
        prisao.setStatus(status);
        return prisaoRepository.save(prisao);
    }

    @Transactional
    public Prisao atualizar(Cela cela, String id) {
        Prisao prisao = prisaoRepository.findById(id).orElseThrow();
        prisao.setCela(cela);
        return prisaoRepository.save(prisao);
    }

    @Transactional
    public void deletar(String id) {
        prisaoRepository.deleteById(id);
    }
}
