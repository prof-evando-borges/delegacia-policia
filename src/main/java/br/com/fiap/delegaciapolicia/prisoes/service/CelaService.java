package br.com.fiap.delegaciapolicia.prisoes.service;

import br.com.fiap.delegaciapolicia.prisoes.entity.Cela;
import br.com.fiap.delegaciapolicia.prisoes.repository.CelaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CelaService {

    private final CelaRepository celaRepository;

    @Transactional
    public Cela registrar(Cela cela) {
        return celaRepository.save(cela);
    }

    @Transactional
    public Cela atualizar(Cela cela, String id) {
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
