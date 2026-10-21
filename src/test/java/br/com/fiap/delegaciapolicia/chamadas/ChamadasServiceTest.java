package br.com.fiap.delegaciapolicia.chamadas;

import br.com.fiap.delegaciapolicia.chamadas.dto.AtendenteRequest;
import br.com.fiap.delegaciapolicia.chamadas.entity.Atendente;
import br.com.fiap.delegaciapolicia.chamadas.entity.Chamada;
import br.com.fiap.delegaciapolicia.chamadas.entity.Disponibilidade;
import br.com.fiap.delegaciapolicia.chamadas.entity.StatusChamada;
import br.com.fiap.delegaciapolicia.chamadas.entity.UnidadeResponsavel;
import br.com.fiap.delegaciapolicia.chamadas.repository.AtendenteRepository;
import br.com.fiap.delegaciapolicia.chamadas.repository.ChamadaRepository;
import br.com.fiap.delegaciapolicia.chamadas.repository.UnidadeResponsavelRepository;
import br.com.fiap.delegaciapolicia.chamadas.service.AtendenteService;
import br.com.fiap.delegaciapolicia.chamadas.service.ChamadaService;
import br.com.fiap.delegaciapolicia.chamadas.service.UnidadeResponsavelService;
import br.com.fiap.delegaciapolicia.compartilhado.excecao.RecursoNaoEncontradoException;
import br.com.fiap.delegaciapolicia.compartilhado.excecao.RegraDeNegocioException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ChamadasServiceTest {

    private AtendenteRepository atendenteRepository;
    private ChamadaRepository chamadaRepository;
    private UnidadeResponsavelRepository unidadeRepository;
    private AtendenteService atendenteService;
    private ChamadaService chamadaService;
    private UnidadeResponsavelService unidadeService;

    @BeforeEach
    void criarServices() {
        atendenteRepository = mock(AtendenteRepository.class);
        chamadaRepository = mock(ChamadaRepository.class);
        unidadeRepository = mock(UnidadeResponsavelRepository.class);
        atendenteService = new AtendenteService(atendenteRepository);
        chamadaService = new ChamadaService(chamadaRepository, atendenteService);
        unidadeService = new UnidadeResponsavelService(unidadeRepository, chamadaService);
    }

    @Test
    void naoSalvaAtendenteComMatriculaDuplicada() {
        when(atendenteRepository.existsByMatricula("ATD-001")).thenReturn(true);

        assertThrows(RegraDeNegocioException.class,
                () -> atendenteService.salvarAtendente(new AtendenteRequest(1L, "ATD-001", "NOITE")));
    }

    @Test
    void buscarChamadaInexistenteLancaNaoEncontrado() {
        when(chamadaRepository.findByProtocolo("X")).thenReturn(Optional.empty());

        assertThrows(RecursoNaoEncontradoException.class, () -> chamadaService.buscarChamadaPorProtocolo("X"));
    }

    @Test
    void naoEncerraChamadaJaEncerrada() {
        Chamada chamada = chamada("CHM-1", StatusChamada.ENCERRADA);
        when(chamadaRepository.findByProtocolo("CHM-1")).thenReturn(Optional.of(chamada));

        assertThrows(RegraDeNegocioException.class, () -> chamadaService.encerrarChamada("CHM-1", "Resolvido"));
    }

    @Test
    void naoVinculaChamadaEmUnidadeOcupada() {
        UnidadeResponsavel unidade = unidade(Disponibilidade.OCUPADA);
        when(unidadeRepository.findById(10L)).thenReturn(Optional.of(unidade));
        when(chamadaRepository.findByProtocolo("CHM-1")).thenReturn(Optional.of(chamada("CHM-1", StatusChamada.ABERTA)));

        assertThrows(RegraDeNegocioException.class, () -> unidadeService.vincularChamada(10L, "CHM-1"));
    }

    @Test
    void vincularGuardaUnidadeNaChamada() {
        UnidadeResponsavel unidade = unidade(Disponibilidade.DISPONIVEL);
        Chamada chamada = chamada("CHM-1", StatusChamada.ABERTA);
        when(unidadeRepository.findById(10L)).thenReturn(Optional.of(unidade));
        when(chamadaRepository.findByProtocolo("CHM-1")).thenReturn(Optional.of(chamada));

        unidadeService.vincularChamada(10L, "CHM-1");

        assertEquals(unidade, chamada.getUnidadeResponsavel());
        assertEquals(1, unidade.getChamadasVinculadas().size());
    }

    private static Chamada chamada(String protocolo, StatusChamada status) {
        Atendente atendente = new Atendente();
        atendente.setMatricula("ATD-001");

        Chamada chamada = new Chamada();
        chamada.setProtocolo(protocolo);
        chamada.setStatus(status);
        chamada.setAtendente(atendente);
        chamada.setIdSolicitante(1L);
        return chamada;
    }

    private static UnidadeResponsavel unidade(Disponibilidade disponibilidade) {
        UnidadeResponsavel unidade = new UnidadeResponsavel();
        unidade.setId(10L);
        unidade.setUnidade("Viatura 01");
        unidade.setTelefone("190");
        unidade.setDisponibilidade(disponibilidade);
        return unidade;
    }
}
