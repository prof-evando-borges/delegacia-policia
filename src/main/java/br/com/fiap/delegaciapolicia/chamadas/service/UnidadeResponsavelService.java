package br.com.fiap.delegaciapolicia.chamadas.service;

import br.com.fiap.delegaciapolicia.chamadas.dto.UnidadeResponsavelRequest;
import br.com.fiap.delegaciapolicia.chamadas.dto.UnidadeResponsavelResponse;
import br.com.fiap.delegaciapolicia.chamadas.entity.Chamada;
import br.com.fiap.delegaciapolicia.chamadas.entity.Disponibilidade;
import br.com.fiap.delegaciapolicia.chamadas.entity.UnidadeResponsavel;
import br.com.fiap.delegaciapolicia.chamadas.repository.UnidadeResponsavelRepository;
import br.com.fiap.delegaciapolicia.compartilhado.excecao.RecursoNaoEncontradoException;
import br.com.fiap.delegaciapolicia.compartilhado.excecao.RegraDeNegocioException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UnidadeResponsavelService {

    private final UnidadeResponsavelRepository unidadeRepository;
    private final ChamadaService chamadaService;

    @Transactional
    public UnidadeResponsavelResponse salvarUnidadeResponsavel(UnidadeResponsavelRequest request) {
        UnidadeResponsavel unidade = new UnidadeResponsavel();
        unidade.setUnidade(request.unidade());
        unidade.setDescricao(request.descricao());
        unidade.setTelefone(request.telefone());
        unidade.setDisponibilidade(request.disponibilidade());
        return UnidadeResponsavelResponse.de(unidadeRepository.save(unidade));
    }

    @Transactional
    public UnidadeResponsavelResponse atualizarDisponibilidadeUnidade(Long id, Disponibilidade disponibilidade) {
        UnidadeResponsavel unidade = buscarEntidadePorId(id);
        unidade.setDisponibilidade(disponibilidade);
        return UnidadeResponsavelResponse.de(unidade);
    }

    @Transactional
    public UnidadeResponsavelResponse vincularChamada(Long id, String protocolo) {
        UnidadeResponsavel unidade = buscarEntidadePorId(id);
        Chamada chamada = chamadaService.buscarEntidadePorProtocolo(protocolo);

        if (unidade.getDisponibilidade() != Disponibilidade.DISPONIVEL) {
            throw new RegraDeNegocioException("A unidade " + unidade.getUnidade() + " não está disponível.");
        }

        chamada.setUnidadeResponsavel(unidade);
        unidade.getChamadasVinculadas().add(chamada);
        return UnidadeResponsavelResponse.de(unidade);
    }

    @Transactional
    public UnidadeResponsavelResponse desvincularChamada(Long id, String protocolo) {
        UnidadeResponsavel unidade = buscarEntidadePorId(id);
        Chamada chamada = chamadaService.buscarEntidadePorProtocolo(protocolo);

        if (chamada.getUnidadeResponsavel() == null || !chamada.getUnidadeResponsavel().getId().equals(id)) {
            throw new RegraDeNegocioException("A chamada " + protocolo + " não está vinculada a esta unidade.");
        }

        chamada.setUnidadeResponsavel(null);
        unidade.getChamadasVinculadas().remove(chamada);
        return UnidadeResponsavelResponse.de(unidade);
    }

    @Transactional(readOnly = true)
    public UnidadeResponsavelResponse buscarUnidadePorId(Long id) {
        return UnidadeResponsavelResponse.de(buscarEntidadePorId(id));
    }

    @Transactional(readOnly = true)
    public List<UnidadeResponsavelResponse> listarUnidadesResponsaveis() {
        return unidadeRepository.findAll().stream().map(UnidadeResponsavelResponse::de).toList();
    }

    private UnidadeResponsavel buscarEntidadePorId(Long id) {
        return unidadeRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Unidade responsável " + id + " não encontrada."));
    }
}
