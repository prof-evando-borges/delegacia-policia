package br.com.fiap.delegaciapolicia.prisoes.service;

import br.com.fiap.delegaciapolicia.prisoes.entity.Presidiario;
import br.com.fiap.delegaciapolicia.prisoes.repository.PresidiarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PresidiarioService {

    private final PresidiarioRepository presidiarioRepository;

    @Transactional
    public void registrar(Presidiario presidiario) {
        presidiarioRepository.save(presidiario);
    }

    public Presidiario buscarPorId(UUID id) {
        return presidiarioRepository.findById(id).orElseThrow();
    }

    public Presidiario buscarPorCpf(String cpf) {
        return presidiarioRepository.findByCpf(cpf).orElseThrow();
    }

    public Page<Presidiario> listarTodos(Pageable pageable) {
        return presidiarioRepository.findAll(pageable);
    }

}
