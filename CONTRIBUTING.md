# Como contribuir

## Branches (Git Flow)

```
main ─────────────●──────────────────────────●──────────  versões entregues (só o professor)
                  │                          ▲
development ──────●────●─────●─────●─────────●──────────  integração da turma (protegida)
                       ▲     ▲     ▲
feature/policiais ─────┘     │     │                      um grupo = uma feature branch
feature/fichas ──────────────┘     │
feature/boletins ──────────────────┘
```

| Branch | Para quê | Quem cria | Recebe código de |
|---|---|---|---|
| `main` | Versão entregue/estável | professor | `release/*` e `hotfix/*` |
| `development` | Integração de todos os módulos | professor | PR das `feature/*` |
| `feature/<modulo>` | Trabalho do grupo | o grupo, **a partir de `development`** | commits dos integrantes |
| `release/*`, `hotfix/*` | Fechamento de versão e correção urgente | professor | - |

Ninguém faz push direto em `main` ou `development`: tudo entra por **pull request**.

## Passo a passo do grupo

```bash
# 0. Uma vez por computador: identidade e comportamento do pull
git config --global user.name "Seu Nome"
git config --global user.email "email-da-sua-conta@github.com"
git config --global pull.rebase false

# 1. Atualize a development
git checkout development
git pull origin development

# 2. Crie a branch do grupo (UMA pessoa cria e publica)
git checkout -b feature/boletins
git push -u origin feature/boletins

# 3. Os demais integrantes baixam a branch
git fetch origin
git checkout feature/boletins

# 4. Trabalhe, faça commits pequenos e envie
git add <arquivos>
git commit -m "feat(boletins): cria script da tabela TB_BOL_OCORRENCIA"
git pull origin feature/boletins      # traga o trabalho dos colegas antes de enviar
git push

# 5. Antes de abrir o PR, traga as novidades da development
git pull origin development
git push
```

Depois, no GitHub: **Pull requests → New pull request**, com `base: development` e `compare: feature/<modulo>`.

## Padrão de commit

`<tipo>(<modulo>): <o que foi feito>`, no imperativo.

| Tipo | Quando usar |
|---|---|
| `feat` | funcionalidade nova (tabela, entidade, endpoint) |
| `fix` | correção |
| `docs` | documentação |
| `test` | testes |
| `refactor` | mudança de código sem mudar comportamento |
| `chore` | configuração e arquivos de apoio |

Exemplos: `feat(policiais): mapeia entidade Policial`, `fix(prisoes): corrige FK de TB_PRS_PRISAO`.

## Regras do pull request

- Base sempre `development` (nunca `main`).
- Só arquivos do seu módulo: o seu pacote em `src/main/java/br/com/fiap/delegaciapolicia/<modulo>/` e o seu script em `banco-de-dados/`.
- Título no padrão de commit; descrição preenchida (o template aparece sozinho).
- Pelo menos **1 aprovação** de um colega do grupo e o check do GitHub Actions **verde**.
- Quem faz o merge é o professor.

## Conflito de merge?

Aconteceu quando duas pessoas mudaram o mesmo trecho. Resolva na sua branch:

```bash
git pull origin development   # o Git marca os arquivos em conflito
# edite os arquivos, escolha o trecho certo e apague as marcas <<<<<<< ======= >>>>>>>
git add <arquivo>
git commit
git push
```

Se o conflito for em um arquivo que não é do seu módulo, **pare e chame o professor**: provavelmente o arquivo não deveria estar no seu PR.
