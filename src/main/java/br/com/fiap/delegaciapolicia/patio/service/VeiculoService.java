package br.com.fiap.delegaciapolicia.patio.service;

import br.com.fiap.delegaciapolicia.compartilhado.excecao.RecursoNaoEncontradoException;
import br.com.fiap.delegaciapolicia.patio.entity.VagaPatio;
import br.com.fiap.delegaciapolicia.patio.entity.Veiculo;
import br.com.fiap.delegaciapolicia.patio.exception.PatioException;
import br.com.fiap.delegaciapolicia.patio.repository.VeiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;
    private final VagaPatioService vagaPatioService;

    @Transactional
    public Veiculo cadastrar(Veiculo veiculo) {
        if (veiculo.getDataSaida() != null)
            throw new PatioException("O veículo deve ser cadastrado sem data de saída; registre a saída pela atualização.");

        if (veiculoRepository.existsByPlacaAndDataSaidaIsNull(veiculo.getPlaca()))
            throw new PatioException("Já existe um veículo com a placa " + veiculo.getPlaca() + " no pátio.");

        VagaPatio vaga = buscarVaga(veiculo.getVaga());
        if (vaga != null)
            vagaPatioService.ocupar(vaga);

        veiculo.setId(null);
        veiculo.setVaga(vaga);
        return veiculoRepository.save(veiculo);
    }

    public List<Veiculo> listarTodos() {
        return veiculoRepository.findAll();
    }

    public List<Veiculo> buscarPorPlaca(String placa) {
        return veiculoRepository.findByPlaca(placa);
    }

    public List<Veiculo> listarNoPatio() {
        return veiculoRepository.findByDataSaidaIsNull();
    }

    public Veiculo buscarPorId(String id) {
        return veiculoRepository.findById(id)
            .orElseThrow(() -> new RecursoNaoEncontradoException("Veículo não encontrado com o id " + id + "."));
    }

    @Transactional
    public Veiculo atualizar(String id, Veiculo dados) {
        Veiculo veiculo = buscarPorId(id);

        if (veiculo.getDataSaida() != null)
            throw new PatioException("O veículo já saiu do pátio e não pode mais ser alterado.");

        if (veiculoRepository.existsByPlacaAndDataSaidaIsNullAndIdNot(dados.getPlaca(), id))
            throw new PatioException("Já existe outro veículo com a placa " + dados.getPlaca() + " no pátio.");

        if (dados.getDataSaida() != null && dados.getDataSaida().isBefore(dados.getDataEntrada()))
            throw new PatioException("A data de saída não pode ser anterior à data de entrada.");

        VagaPatio vagaAtual = veiculo.getVaga();
        VagaPatio novaVaga = buscarVaga(dados.getVaga());
        if (!mesmaVaga(vagaAtual, novaVaga)) {
            if (novaVaga != null)
                vagaPatioService.ocupar(novaVaga);
            if (vagaAtual != null)
                vagaPatioService.liberar(vagaAtual);
        }

        // Saida registrada: o veiculo deixa o patio e a vaga fica livre
        if (dados.getDataSaida() != null && novaVaga != null)
            vagaPatioService.liberar(novaVaga);

        veiculo.setPlaca(dados.getPlaca());
        veiculo.setMarca(dados.getMarca());
        veiculo.setModelo(dados.getModelo());
        veiculo.setCor(dados.getCor());
        veiculo.setTipo(dados.getTipo());
        veiculo.setDataEntrada(dados.getDataEntrada());
        veiculo.setDataSaida(dados.getDataSaida());
        veiculo.setVaga(novaVaga);
        veiculo.setIdRegistro(dados.getIdRegistro());
        return veiculoRepository.save(veiculo);
    }

    @Transactional
    public void remover(String id) {
        Veiculo veiculo = buscarPorId(id);

        if (veiculo.getDataSaida() == null && veiculo.getVaga() != null)
            vagaPatioService.liberar(veiculo.getVaga());

        veiculoRepository.delete(veiculo);
    }

    private VagaPatio buscarVaga(VagaPatio vaga) {
        if (vaga == null || vaga.getId() == null)
            return null;
        return vagaPatioService.buscarPorId(vaga.getId());
    }

    private boolean mesmaVaga(VagaPatio a, VagaPatio b) {
        String idA = a == null ? null : a.getId();
        String idB = b == null ? null : b.getId();
        return Objects.equals(idA, idB);
    }
}
