package br.com.fiap.delegaciapolicia.policiais;

import br.com.fiap.delegaciapolicia.policiais.entity.Delegacia;
import br.com.fiap.delegaciapolicia.policiais.entity.Endereco;
import br.com.fiap.delegaciapolicia.policiais.entity.Policial;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PoliciaisValidationTest {

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
    void aceitaEntidadesComDadosValidos() {
        Endereco endereco = new Endereco();
        endereco.setRua("Rua da Delegacia");
        endereco.setNumero(10L);
        endereco.setCep("01234567");

        Delegacia delegacia = new Delegacia();
        delegacia.setNome("Delegacia Central");
        delegacia.setEndereco(endereco);

        Policial policial = new Policial();
        policial.setNome("Ana da Silva");
        policial.setCpf("12345678901");
        policial.setMatricula("PLC-001");
        policial.setCargo("Investigadora");
        policial.setDelegacia(delegacia);

        assertTrue(validator.validate(endereco).isEmpty());
        assertTrue(validator.validate(delegacia).isEmpty());
        assertTrue(validator.validate(policial).isEmpty());
    }

    @Test
    void rejeitaCpfCepEEnderecoObrigatorioInvalidos() {
        Endereco endereco = new Endereco();
        endereco.setRua("Rua da Delegacia");
        endereco.setNumero(0L);
        endereco.setCep("123");

        Policial policial = new Policial();
        policial.setNome("Ana da Silva");
        policial.setCpf("123");
        policial.setMatricula("PLC-001");
        policial.setCargo("Investigadora");

        Set<String> enderecoComErros = validator.validate(endereco).stream()
                .map(erro -> erro.getPropertyPath().toString())
                .collect(Collectors.toSet());
        Set<String> policialComErros = validator.validate(policial).stream()
                .map(erro -> erro.getPropertyPath().toString())
                .collect(Collectors.toSet());

        assertEquals(Set.of("numero", "cep"), enderecoComErros);
        assertEquals(Set.of("cpf", "delegacia"), policialComErros);
    }
}
