-- REGISTRO DE PRISAO

-- Criação de tabelas
-- TABELA PRESIDIARIO
CREATE TABLE TB_PRS_PRESIDIARIO (
    id VARCHAR2(36)CONSTRAINT pk_tb_prs_presidiario PRIMARY KEY,
    nome VARCHAR2(100) NOT NULL,
    cpf VARCHAR2(11) NOT NULL CONSTRAINT uk_tb_prs_presidiario_cpf UNIQUE,
    genero CHAR(1) NOT NULL CONSTRAINT ck_tb_prs_prisao_genero CHECK(genero IN('M', 'F')) ,
    idade NUMBER(3) NOT NULL,
    data_nascimento DATE NOT NULL,
    peso NUMBER(5,2) NOT NULL,
    altura NUMBER(3,2) NOT NULL,
    endereco VARCHAR2(100) NOT NULL
);

-- TABELA CELA
CREATE TABLE TB_PRS_CELA (
    id VARCHAR2(36)CONSTRAINT pk_tb_prs_cela PRIMARY KEY,
    pavilhao VARCHAR2(10) NOT NULL,
    bloco VARCHAR2(10) NOT NULL,
    numero VARCHAR2(10) NOT NULL,
    id_presidiario VARCHAR2(36) NOT NULL,
    
    -- Chaves estrangeiras
    CONSTRAINT fk_tb_prs_cela_presidiario FOREIGN KEY(id_presidiario) REFERENCES TB_PRS_PRESIDIARIO(id),
    -- Unique composta
    CONSTRAINT uk_tb_prs_cela_pavilhao_bloco_numero UNIQUE (pavilhao,bloco,numero)
);

-- TABELA PRISAO
CREATE TABLE TB_PRS_PRISAO (
    id VARCHAR2(36) CONSTRAINT pk_tb_prs_prisao PRIMARY KEY,
    status VARCHAR2(50) NOT NULL CONSTRAINT ck_tb_prs_prisao_status CHECK(status IN('EM_ANDAMENTO', 'CONCLUIDA', 'REVOGADA')),
    id_presidiario VARCHAR2(36) NOT NULL,
    data DATE NOT NULL,
    tipo VARCHAR2(16) NOT NULL CONSTRAINT ck_tb_prs_prisao_tipo CHECK(tipo IN('FLAGRANTE', 'PREVENTIVA', 'TEMPORARIA', 'MANDADO_JUDICIAL')),
    motivo VARCHAR2(200) NOT NULL,
    id_policial VARCHAR2(36) NOT NULL,
    id_cela VARCHAR2(36) NOT NULL,
    reincidente NUMBER(1) NOT NULL CONSTRAINT ck_tb_prs_prisao_reincidente CHECK(reincidente IN(0,1)),
    
    -- Chaves Estrangeiras
    CONSTRAINT fk_tb_prs_prisao_presidiario FOREIGN KEY(id_presidiario) REFERENCES TB_PRS_PRESIDIARIO(id),
    --CONSTRAINT fk_tb_prs_prisao_policial FOREIGN KEY(id_policial) REFERENCES TB_PLC_POLICIAL(id),
    CONSTRAINT fk_tb_prs_prisao_cela FOREIGN KEY(id_cela) REFERENCES TB_PRS_CELA(id)
);
-- Inserção de dados

-- PRESIDIARIO
INSERT INTO tb_prs_presidiario(id,nome,cpf,genero,idade,data_nascimento,peso,altura,endereco)
VALUES(1,'Gabriel Amara','12345678901','M',28,TO_DATE('06/10/1998','dd/mm/yyyy'),73.52,1.73,'Rua Aleatoria,123');

INSERT INTO tb_prs_presidiario(id,nome,cpf,genero,idade,data_nascimento,peso,altura,endereco)
VALUES(2,'Camila Martins','12345678999','F',24,TO_DATE('17/10/2002','dd/mm/yyyy'),50,1.60,'Rua da Fiap, 1234');

INSERT INTO tb_prs_presidiario(id,nome,cpf,genero,idade,data_nascimento,peso,altura,endereco)
VALUES(3,'Pedro Lucas Almeida','12345878999','M',20,TO_DATE('01/07/2006','dd/mm/yyyy'),60,1.70,'Rua da Aleatoria, 12');

INSERT INTO tb_prs_presidiario(id,nome,cpf,genero,idade,data_nascimento,peso,altura,endereco)
VALUES(4,'Guilherme Godoy','12245878999','M',23,TO_DATE('23/04/2003','dd/mm/yyyy'),65,1.68,'Avenida Paulista, 1222');

INSERT INTO tb_prs_presidiario(id,nome,cpf,genero,idade,data_nascimento,peso,altura,endereco)
VALUES(5,'Luis Felipe Scacchetti','32245878999','M',21,TO_DATE('23/04/2005','dd/mm/yyyy'),90,1.80,'Avenida Paulista, 2500');

-- CELA
INSERT INTO tb_prs_cela(id,pavilhao,bloco,numero,id_presidiario)
VALUES(1,'AB','C','1001',1);

INSERT INTO tb_prs_cela(id,pavilhao,bloco,numero,id_presidiario)
VALUES(2,'CB','A','1034',2);

INSERT INTO tb_prs_cela(id,pavilhao,bloco,numero,id_presidiario)
VALUES(3,'AB','C','1002',3);

INSERT INTO tb_prs_cela(id,pavilhao,bloco,numero,id_presidiario)
VALUES(4,'DB','F','10',4);

INSERT INTO tb_prs_cela(id,pavilhao,bloco,numero,id_presidiario)
VALUES(5,'CB','G','1022',5);

-- PRISAO
INSERT INTO tb_prs_prisao(id,status,id_presidiario,data,tipo,motivo,id_policial,id_cela,reincidente)
VALUES(1,'EM_ANDAMENTO',1,TO_DATE('21/10/2026','dd/mm/yyyy'), 'FLAGRANTE', 'Foi pego roubando no Shopping',1,1,0);

INSERT INTO tb_prs_prisao(id,status,id_presidiario,data,tipo,motivo,id_policial,id_cela,reincidente)
VALUES(2,'EM_ANDAMENTO',2,TO_DATE('21/10/2026','dd/mm/yyyy'), 'FLAGRANTE', 'Foi pego destruindo PCs',1,2,1);

INSERT INTO tb_prs_prisao(id,status,id_presidiario,data,tipo,motivo,id_policial,id_cela,reincidente)
VALUES(3,'EM_ANDAMENTO',3,TO_DATE('21/10/2026','dd/mm/yyyy'), 'PREVENTIVA', 'Não pagou a pensão',1,3,1);

INSERT INTO tb_prs_prisao(id,status,id_presidiario,data,tipo,motivo,id_policial,id_cela,reincidente)
VALUES(4,'EM_ANDAMENTO',4,TO_DATE('21/10/2026','dd/mm/yyyy'), 'TEMPORARIA', 'Investigado pela PF',1,4,0);

INSERT INTO tb_prs_prisao(id,status,id_presidiario,data,tipo,motivo,id_policial,id_cela,reincidente)
VALUES(5,'EM_ANDAMENTO',5,TO_DATE('21/10/2026','dd/mm/yyyy'), 'FLAGRANTE', 'Colocou a chave da API no README',1,5,1);


-- Comentarios
-- Prisao
COMMENT ON TABLE TB_PRS_PRISAO IS 'Tabela responsavel por armazenar dados referente ao registro do presidiario na prisão';
-- Colunas
COMMENT ON COLUMN TB_PRS_PRISAO.id IS  'Identificador do registro de prisão';
COMMENT ON COLUMN TB_PRS_PRISAO.status IS  'Situação do preso atualmente: Em andamento, Concluido, Revogada';
COMMENT ON COLUMN TB_PRS_PRISAO.id_presidiario IS  'Identificador de dados quem esta preso';
COMMENT ON COLUMN TB_PRS_PRISAO.data IS  'Data que o preso entrou na prisão';
COMMENT ON COLUMN TB_PRS_PRISAO.tipo IS  'Os tipos aceitos são : Flagrante, Preventiva, Temporaria, Mandado Judicial';
COMMENT ON COLUMN TB_PRS_PRISAO.motivo IS  'Descrição breve do motivo da prisão';
COMMENT ON COLUMN TB_PRS_PRISAO.id_policial IS  'Identificador de qual policial realizou a prisão';
COMMENT ON COLUMN TB_PRS_PRISAO.id_cela IS  'Identificador de qual cela o preso esta';
COMMENT ON COLUMN TB_PRS_PRISAO.reincidente IS  'Se já existe alguma passagem anterior em qualquer prisao, 1 = SIM e 0 = NÃO';
-- Presidiario
COMMENT ON TABLE TB_PRS_PRESIDIARIO IS 'Tabela responsavel por armazenar informações cadastrais referente ao presidiario';
-- Colunas
COMMENT ON COLUMN TB_PRS_PRESIDIARIO.id IS  'Identificador do presidiario';
COMMENT ON COLUMN TB_PRS_PRESIDIARIO.nome IS  'Nome completo de quem esta preso';
COMMENT ON COLUMN TB_PRS_PRESIDIARIO.cpf IS  'Documento unico do Presidiario, neste caso CPF';
COMMENT ON COLUMN TB_PRS_PRESIDIARIO.genero IS  'Sexo biologico do preso, M = masculino e F = feminino';
COMMENT ON COLUMN TB_PRS_PRESIDIARIO.idade IS  'Idade do preso';
COMMENT ON COLUMN TB_PRS_PRESIDIARIO.data_nascimento IS  'Data de nascimento do preso';
COMMENT ON COLUMN TB_PRS_PRESIDIARIO.peso IS  'Peso quando ingressou na prisão';
COMMENT ON COLUMN TB_PRS_PRESIDIARIO.altura IS  'Altura do preso';
COMMENT ON COLUMN TB_PRS_PRESIDIARIO.endereco IS  'Endereço residencial do preso';
-- Cela
COMMENT ON TABLE TB_PRS_CELA IS 'Tabela responsavel por armazenar informações detalhadas das celas dentro da prisão';
--Colunas
COMMENT ON COLUMN TB_PRS_CELA.id IS  'Identificador da cela';
COMMENT ON COLUMN TB_PRS_CELA.pavilhao IS  'Numero do pavilhao';
COMMENT ON COLUMN TB_PRS_CELA.bloco IS  'Numero do bloco';
COMMENT ON COLUMN TB_PRS_CELA.numero IS  'Numero da cela';
COMMENT ON COLUMN TB_PRS_CELA.id_presidiario IS  'Qual prisioneiro esta na cela';