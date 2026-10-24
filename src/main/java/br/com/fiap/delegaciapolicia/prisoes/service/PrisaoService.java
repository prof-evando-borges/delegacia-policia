package br.com.fiap.delegaciapolicia.prisoes.service;

import br.com.fiap.delegaciapolicia.prisoes.entity.Cela;
import br.com.fiap.delegaciapolicia.prisoes.entity.Prisao;
import br.com.fiap.delegaciapolicia.prisoes.entity.enums.PrisaoStatus;
import br.com.fiap.delegaciapolicia.prisoes.repository.PrisaoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PrisaoService {

    private final PrisaoRepository prisaoRepository;

    @Transactional
    private void registrar(Prisao prisao) {
        prisaoRepository.save(prisao);
    }

    public Prisao buscarPorId(UUID id) {
        return prisaoRepository.findById(id).orElse(null);
    }

    public Page<Prisao> listar(Pageable pageable) {
        return prisaoRepository.findAll(pageable);
    }

    public Page<Prisao> listarPorPreso(Pageable pageable, UUID id) {
        return prisaoRepository.findAllByPresoId(pageable, id);
    }

    public Prisao atualizar(PrisaoStatus status, UUID id) {
        Prisao prisao = buscarPorId(id);
        prisao.setStatus(status);
        return prisao;
    }

    public Prisao atualizar(Cela cela, UUID id) {
        Prisao prisao = buscarPorId(id);
        return prisao;
    }

    public void deletar(UUID id) {
        prisaoRepository.deleteById(id);
    }
}
