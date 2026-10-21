package br.com.fiap.delegaciapolicia.chamadas.service;

import br.com.fiap.delegaciapolicia.chamadas.dto.AtendenteRequest;
import br.com.fiap.delegaciapolicia.chamadas.dto.AtendenteResponse;
import br.com.fiap.delegaciapolicia.chamadas.entity.Atendente;
import br.com.fiap.delegaciapolicia.chamadas.repository.AtendenteRepository;
import br.com.fiap.delegaciapolicia.compartilhado.excecao.RecursoNaoEncontradoException;
import br.com.fiap.delegaciapolicia.compartilhado.excecao.RegraDeNegocioException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AtendenteService {

    private final AtendenteRepository atendenteRepository;

    @Transactional
    public AtendenteResponse salvarAtendente(AtendenteRequest request) {
        if (atendenteRepository.existsByMatricula(request.matricula())) {
            throw new RegraDeNegocioException("Já existe atendente com a matrícula " + request.matricula() + ".");
        }
        // TODO: validar o policial com o service do módulo policiais (Grupo 5) quando ele existir.
        // Até lá, a FK FK_CHM_ATENDENTE_POLICIAL garante que o id existe em TB_PLC_POLICIAL.

        Atendente atendente = new Atendente();
        atendente.setIdPolicial(request.idPolicial());
        atendente.setMatricula(request.matricula());
        atendente.setTurno(request.turno());
        return AtendenteResponse.de(atendenteRepository.save(atendente));
    }

    @Transactional(readOnly = true)
    public AtendenteResponse buscarAtendentePorMatricula(String matricula) {
        return AtendenteResponse.de(buscarEntidadePorMatricula(matricula));
    }

    @Transactional(readOnly = true)
    public List<AtendenteResponse> listarAtendentes() {
        return atendenteRepository.findAll().stream().map(AtendenteResponse::de).toList();
    }

    /** Uso interno do módulo (ex.: {@link ChamadaService}). */
    @Transactional(readOnly = true)
    public Atendente buscarEntidadePorMatricula(String matricula) {
        return atendenteRepository.findByMatricula(matricula)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Atendente com matrícula " + matricula + " não encontrado."));
    }
}
