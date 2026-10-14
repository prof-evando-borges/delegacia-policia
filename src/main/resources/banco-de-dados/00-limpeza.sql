-- =====================================================================
-- 00-limpeza.sql  (dono: professor - grupos NAO alteram)
-- Apaga SOMENTE as tabelas deste projeto (prefixos TB_PLC_ ... TB_PRS_)
-- do seu schema. Tabelas de outras disciplinas nao sao tocadas.
-- Rode antes de recriar o banco com executar-todos.sql.
-- =====================================================================
BEGIN
    FOR t IN (
        SELECT table_name
          FROM user_tables
         WHERE REGEXP_LIKE(table_name, '^TB_(PLC|FIC|FOL|EST|CHM|RND|BOL|PAT|PRS)_')
    ) LOOP
        EXECUTE IMMEDIATE 'DROP TABLE "' || t.table_name || '" CASCADE CONSTRAINTS PURGE';
    END LOOP;
END;
/
