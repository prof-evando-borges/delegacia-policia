package br.com.fiap.delegaciapolicia.chamadas;

import br.com.fiap.delegaciapolicia.chamadas.entity.Atendente;
import br.com.fiap.delegaciapolicia.chamadas.entity.Chamada;
import br.com.fiap.delegaciapolicia.chamadas.entity.StatusChamada;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ChamadasValidationTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeAll
    static void criarValidador() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterAll
    static void fecharValidador() {
        validatorFactory.close();
    }

    @Test
    void aceitaChamadaComDadosValidos() {
        Atendente atendente = new Atendente();
        atendente.setIdPolicial(1L);
        atendente.setMatricula("ATD-001");
        atendente.setTurno("NOITE");

        Chamada chamada = new Chamada();
        chamada.setProtocolo("CHM-20261020120000-ABC123");
        chamada.setNatureza("Furto");
        chamada.setIdSolicitante(1L);
        chamada.setAtendente(atendente);
        chamada.setDescricao("Celular furtado na saída do metrô");
        chamada.setEndereco("Av. Paulista, 1000");
        chamada.setStatus(StatusChamada.ABERTA);
        chamada.setDataHora(LocalDateTime.now());

        assertTrue(validator.validate(atendente).isEmpty());
        assertTrue(validator.validate(chamada).isEmpty());
    }

    @Test
    void rejeitaChamadaSemSolicitanteESemAtendente() {
        Chamada chamada = new Chamada();
        chamada.setProtocolo("CHM-20261020120000-ABC123");
        chamada.setNatureza("Furto");
        chamada.setDescricao("Celular furtado");
        chamada.setEndereco("Av. Paulista, 1000");
        chamada.setStatus(StatusChamada.ABERTA);
        chamada.setDataHora(LocalDateTime.now());

        Set<String> camposComErro = validator.validate(chamada).stream()
                .map(erro -> erro.getPropertyPath().toString())
                .collect(Collectors.toSet());

        assertEquals(Set.of("idSolicitante", "atendente"), camposComErro);
    }
}
