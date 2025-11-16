CREATE TABLE IF NOT EXISTS autor
(
    codau SERIAL PRIMARY KEY,
    nome  VARCHAR(40) NOT NULL
);


CREATE TABLE IF NOT EXISTS assunto
(
    codas     SERIAL PRIMARY KEY,
    descricao VARCHAR(20) NOT NULL
);


CREATE TABLE IF NOT EXISTS livro
(
    codl          SERIAL PRIMARY KEY,
    titulo        VARCHAR(40) NOT NULL,
    editora       VARCHAR(40),
    edicao        INTEGER,
    anopublicacao VARCHAR(4),
    valor         NUMERIC(10, 2)
);


CREATE TABLE IF NOT EXISTS livro_autor
(
    livro_codl  INTEGER NOT NULL,
    autor_codau INTEGER NOT NULL,

    PRIMARY KEY (livro_codl, autor_codau),

    FOREIGN KEY (livro_codl) REFERENCES livro (codl) ON DELETE CASCADE,
    FOREIGN KEY (autor_codau) REFERENCES autor (codau) ON DELETE CASCADE
);


CREATE INDEX IF NOT EXISTS livro_autor_fkindex1 ON livro_autor (livro_codl);
CREATE INDEX IF NOT EXISTS livro_autor_fkindex2 ON livro_autor (autor_codau);


CREATE TABLE IF NOT EXISTS livro_assunto
(
    livro_codl    INTEGER NOT NULL,
    assunto_codas INTEGER NOT NULL,

    PRIMARY KEY (livro_codl, assunto_codas),

    FOREIGN KEY (livro_codl) REFERENCES livro (codl) ON DELETE CASCADE,
    FOREIGN KEY (assunto_codas) REFERENCES assunto (codas) ON DELETE CASCADE
);


CREATE INDEX IF NOT EXISTS livro_assunto_fkindex1 ON livro_assunto (livro_codl);
CREATE INDEX IF NOT EXISTS livro_assunto_fkindex2 ON livro_assunto (assunto_codas);

CREATE OR REPLACE VIEW vw_relatorio_autor_livro AS
SELECT L.CODL                                                 AS CODLIVRO,
       AT.CODAU                                               AS CODAUTOR,
       AT.NOME                                                AS AUTOR,
       L.TITULO                                               AS LIVRO,
       STRING_AGG(ASS.DESCRICAO, ', ' ORDER BY ASS.DESCRICAO) AS ASSUNTOS,
       L.EDITORA,
       L.EDICAO,
       L.ANOPUBLICACAO
FROM AUTOR AT
         JOIN LIVRO_AUTOR LT ON AT.CODAU = LT.AUTOR_CODAU
         JOIN LIVRO L ON LT.LIVRO_CODL = L.CODL
         LEFT JOIN LIVRO_ASSUNTO LA ON L.CODL = LA.LIVRO_CODL
         LEFT JOIN ASSUNTO ASS ON LA.ASSUNTO_CODAS = ASS.CODAS
GROUP BY L.CODL, AT.CODAU, L.CODL, AT.NOME, L.TITULO, L.EDITORA, L.EDICAO, L.ANOPUBLICACAO
ORDER BY AT.NOME, L.TITULO;

