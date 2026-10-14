package br.com.fiap.delegaciapolicia;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * Sobe a aplicação inteira contra um Oracle limpo: executa os scripts da pasta
 * banco-de-dados em ordem e o Hibernate valida (ddl-auto=validate) se as entidades
 * batem com as tabelas.
 *
 * <p>Roda apenas no GitHub Actions (variável CI=true), onde existe o Oracle em container.
 * Na máquina do aluno o teste é ignorado.</p>
 */
@SpringBootTest
@ActiveProfiles("ci")
@EnabledIfEnvironmentVariable(named = "CI", matches = "true")
class DelegaciaPoliciaApplicationTests {

    @Test
    void scriptsEEntidadesEstaoConsistentes() {
    }

}
