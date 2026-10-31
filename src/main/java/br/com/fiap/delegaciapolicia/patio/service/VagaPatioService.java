package br.com.fiap.delegaciapolicia.patio.service;

import br.com.fiap.delegaciapolicia.compartilhado.excecao.RecursoNaoEncontradoException;
import br.com.fiap.delegaciapolicia.patio.entity.VagaPatio;
import br.com.fiap.delegaciapolicia.patio.exception.PatioException;
import br.com.fiap.delegaciapolicia.patio.repository.VagaPatioRepository;
import br.com.fiap.delegaciapolicia.patio.repository.VeiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VagaPatioService {

    private final VagaPatioRepository vagaPatioRepository;
    private final VeiculoRepository veiculoRepository;

    @Transactional
    public VagaPatio cadastrar(VagaPatio vaga) {
        if (vagaPatioRepository.existsByCodigo(vaga.getCodigo()))
            throw new PatioException("Já existe uma vaga com o código " + vaga.getCodigo() + ".");

        vaga.setId(null);
        vaga.setOcupada(false);
        return vagaPatioRepository.save(vaga);
    }

    public List<VagaPatio> listarTodos() {
        return vagaPatioRepository.findAll();
    }

    public List<VagaPatio> listarPorOcupacao(Boolean ocupada) {
        return vagaPatioRepository.findByOcupada(ocupada);
    }

    public VagaPatio buscarPorId(String id) {
        return vagaPatioRepository.findById(id)
            .orElseThrow(() -> new RecursoNaoEncontradoException("Vaga do pátio não encontrada com o id " + id + "."));
    }

    @Transactional
    public VagaPatio atualizar(String id, VagaPatio dados) {
        VagaPatio vaga = buscarPorId(id);

        if (vagaPatioRepository.existsByCodigoAndIdNot(dados.getCodigo(), id))
            throw new PatioException("Já existe outra vaga com o código " + dados.getCodigo() + ".");

        vaga.setCodigo(dados.getCodigo());
        vaga.setSetor(dados.getSetor());
        return vagaPatioRepository.save(vaga);
    }

    @Transactional
    public void remover(String id) {
        VagaPatio vaga = buscarPorId(id);

        if (Boolean.TRUE.equals(vaga.getOcupada()))
            throw new PatioException("A vaga " + vaga.getCodigo() + " está ocupada e não pode ser removida.");

        if (veiculoRepository.existsByVagaId(id))
            throw new PatioException("A vaga " + vaga.getCodigo() + " possui histórico de veículos e não pode ser removida.");

        vagaPatioRepository.delete(vaga);
    }

    @Transactional
    public void ocupar(VagaPatio vaga) {
        if (Boolean.TRUE.equals(vaga.getOcupada()))
            throw new PatioException("A vaga " + vaga.getCodigo() + " já está ocupada por outro veículo.");

        vaga.setOcupada(true);
        vagaPatioRepository.save(vaga);
    }

    @Transactional
    public void liberar(VagaPatio vaga) {
        vaga.setOcupada(false);
        vagaPatioRepository.save(vaga);
    }
}
