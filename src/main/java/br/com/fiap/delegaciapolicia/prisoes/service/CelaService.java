package br.com.fiap.delegaciapolicia.prisoes.service;

import br.com.fiap.delegaciapolicia.prisoes.entity.Cela;
import br.com.fiap.delegaciapolicia.prisoes.repository.CelaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CelaService {

    private final CelaRepository celaRepository;

    @Transactional
    public void registrar(Cela cela) {
        celaRepository.save(cela);
    }

    @Transactional
    private Cela atualizar(Cela cela, UUID id) {
        Cela celaAtualizar = celaRepository.findById(id).orElseThrow();

        if (cela.getBloco() != null)
            celaAtualizar.setBloco(cela.getBloco());

        if (cela.getNumero() != null)
            celaAtualizar.setNumero(cela.getNumero());

        if (cela.getPavilhao() != null)
            celaAtualizar.setPavilhao(cela.getPavilhao());

        return celaRepository.save(celaAtualizar);
    }

}
