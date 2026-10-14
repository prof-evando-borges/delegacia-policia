# Configuração do repositório (professor)

Passo a passo para deixar o repositório pronto antes da Entrega 2.

## 1. Criar o repositório e publicar a base

1. No GitHub, crie o repositório `delegacia-policia` **vazio** (sem README, sem .gitignore).
   - Deixe-o **público**, ou crie dentro de uma organização com GitHub Education. Em repositório privado de conta gratuita, as regras de proteção de branch não funcionam.
2. Na pasta do projeto:
   ```bash
   git init -b main
   git add .
   git commit -m "chore: aplicacao base do projeto da sala"
   git remote add origin https://github.com/prof-evando-borges/delegacia-policia.git
   git push -u origin main

   git checkout -b development
   git push -u origin development
   ```
3. Confira se `@prof-evando-borges`, em `.github/CODEOWNERS`, é o seu usuário do GitHub. Se não for, troque e faça commit na `development`.

## 2. Branch padrão

**Settings → General → Default branch → `development`.** Assim o clone dos alunos já abre na branch certa e os PRs apontam para ela por padrão.

## 3. Proteger `main` e `development`

**Settings → Branches → Add branch ruleset** (ou "Add classic branch protection rule"), uma regra para cada branch:

- [x] Require a pull request before merging
  - [x] Require approvals: **1**
  - [x] Require review from Code Owners
  - [x] Dismiss stale pull request approvals when new commits are pushed
- [x] Require status checks to pass → adicione **`Build + scripts + entidades`** (aparece depois que o workflow rodar a primeira vez)
  - [x] Require branches to be up to date before merging
- [x] Block force pushes
- [x] Restrict deletions

Em `main`, marque também **Restrict who can push** (só você).

## 4. Convidar os alunos (Entrega 2)

**Settings → Collaborators → Add people**, com permissão **Write**. Os alunos aceitam o convite pelo e-mail ou em `https://github.com/prof-evando-borges/delegacia-policia/invitations`.

## 5. Ordem de merge da Entrega 3

Os scripts dependem uns dos outros, então aprove os PRs **na ordem dos números** (01 → 09). Depois de cada merge, os grupos seguintes atualizam a branch (`git pull origin development`) e o check roda de novo contra os scripts já integrados.

## 6. Arquivos de teste da Entrega 2

Depois de avaliar a Entrega 2, apague a pasta `src/main/resources/banco-de-dados/teste/` da `development` com um commit seu.
