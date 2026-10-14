# Delegacia de Polícia - Projeto da sala 2ESPV

Sistema de gestão de uma delegacia de polícia construído pela turma inteira: **9 grupos, 9 módulos, um único repositório**.

Java 21 · Spring Boot 4.1 · Spring Data JPA · Oracle

## Arquitetura: monólito modular

```
src/main/java/br/com/fiap/delegaciapolicia
├── DelegaciaPoliciaApplication.java
├── compartilhado/          ← professor (tratamento global de exceções)
├── policiais/              ← Grupo 5
│   ├── controller/
│   ├── service/
│   ├── repository/
│   ├── entity/
│   └── dto/
├── fichas/                 ← Grupo 9
├── folha/                  ← Grupo 7
├── estoque/                ← Grupo 6
├── chamadas/               ← Grupo 1
├── rondas/                 ← Grupo 2
├── boletins/               ← Grupo 8
├── patio/                  ← Grupo 3
└── prisoes/                ← Grupo 4

src/main/resources/banco-de-dados
├── 00-limpeza.sql          ← professor
├── 01-policiais.sql        ← um script por módulo, numerado por dependência
├── ...
├── 09-prisoes.sql
└── executar-todos.sql      ← professor
```

### Regras de ouro

1. **Cada grupo só altera o próprio pacote e o próprio script.** `pom.xml`, `application.properties`, `compartilhado/` e `.github/` são do professor; precisa mudar algo neles? Abra uma issue.
2. **Outro módulo se referencia pelo id, não pela entidade.** Use `Long idPolicial`, não `@ManyToOne Policial`. Relacionamentos JPA só entre entidades do mesmo módulo.
3. **Para usar regras de outro módulo, chame o `service` dele**, nunca o `repository`.
4. **Endpoints com o nome do módulo:** `/api/v1/<modulo>/...` (ex.: `/api/v1/boletins/{id}`).
5. **O banco é criado pelos scripts.** O Hibernate só valida (`ddl-auto=validate`).

## Módulos

| Ordem | Módulo | Grupo | Pacote | Branch | Script | Prefixo |
|---|---|---|---|---|---|---|
| 01 | Registro de Policiais | 5 | `policiais` | `feature/policiais` | `01-policiais.sql` | `TB_PLC_` |
| 02 | Ficha Criminal | 9 | `fichas` | `feature/fichas` | `02-fichas.sql` | `TB_FIC_` |
| 03 | Folha de Pagamento | 7 | `folha` | `feature/folha` | `03-folha.sql` | `TB_FOL_` |
| 04 | Estoque | 6 | `estoque` | `feature/estoque` | `04-estoque.sql` | `TB_EST_` |
| 05 | Registro de Chamadas | 1 | `chamadas` | `feature/chamadas` | `05-chamadas.sql` | `TB_CHM_` |
| 06 | Registro de Ronda | 2 | `rondas` | `feature/rondas` | `06-rondas.sql` | `TB_RND_` |
| 07 | Registro de BO | 8 | `boletins` | `feature/boletins` | `07-boletins.sql` | `TB_BOL_` |
| 08 | Pátio | 3 | `patio` | `feature/patio` | `08-patio.sql` | `TB_PAT_` |
| 09 | Registro de Prisão | 4 | `prisoes` | `feature/prisoes` | `09-prisoes.sql` | `TB_PRS_` |

Convenções de banco, tipos e um exemplo completo de script + entidade: [`src/main/resources/banco-de-dados/README.md`](src/main/resources/banco-de-dados/README.md).

## Como rodar

1. Clone e entre na branch de desenvolvimento:
   ```bash
   git clone https://github.com/prof-evando-borges/delegacia-policia.git
   cd delegacia-policia
   git checkout development
   ```
2. Copie `src/main/resources/application-local.properties.exemplo` para `application-local.properties` (mesma pasta) e coloque **o seu** usuário e senha do Oracle. Esse arquivo está no `.gitignore` e nunca vai para o GitHub.
3. No SQL Developer, rode `src/main/resources/banco-de-dados/executar-todos.sql` (F5) no seu usuário.
4. Rode `DelegaciaPoliciaApplication` pelo IntelliJ (ou `./mvnw spring-boot:run`).

## Como contribuir

Fluxo de branches, padrão de commits e regras de pull request: [CONTRIBUTING.md](CONTRIBUTING.md).
