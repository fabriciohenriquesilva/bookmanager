-- ============================================
-- AUTORES (15 registros)
-- ============================================
INSERT INTO autor (codau, nome)
VALUES (1, 'Machado de Assis'),
       (2, 'George Orwell'),
       (3, 'J. K. Rowling'),
       (4, 'Agatha Christie'),
       (5, 'Stephen King'),
       (6, 'J. R. R. Tolkien'),
       (7, 'Isaac Asimov'),
       (8, 'Neil Gaiman'),
       (9, 'Dan Brown'),
       (10, 'Jane Austen'),
       (11, 'Victor Hugo'),
       (12, 'Gabriel García Márquez'),
       (13, 'Franz Kafka'),
       (14, 'Mary Shelley'),
       (15, 'Haruki Murakami')
    ON CONFLICT (codau) DO NOTHING;

-- ============================================
-- ASSUNTOS (15 registros)
-- ============================================
INSERT INTO assunto (codas, descricao)
VALUES (1, 'Romance'),
       (2, 'Fantasia'),
       (3, 'Suspense'),
       (4, 'Terror'),
       (5, 'Ficção Científica'),
       (6, 'Drama'),
       (7, 'Mistério'),
       (8, 'Aventura'),
       (9, 'Política'),
       (10, 'Thriller'),
       (11, 'Mitologia'),
       (12, 'Psicológico'),
       (13, 'Sobrenatural'),
       (14, 'Crítica Social'),
       (15, 'Clássico')
    ON CONFLICT (codas) DO NOTHING;

-- ============================================
-- LIVROS (15 registros)
-- ============================================
INSERT INTO livro (codl, titulo, anopublicacao, editora, edicao, valor)
VALUES (1, 'Dom Casmurro', '1899', 'Editora A', 1, 39.90),
       (2, '1984', '1949', 'Companhia das Letras', 1, 34.50),
       (3, 'Harry Potter e a Pedra Filosofal', '1997', 'Rocco', 1, 49.90),
       (4, 'Assassinato no Expresso Oriente', '1934', 'Record', 1, 29.90),
       (5, 'O Iluminado', '1977', 'Suma', 1, 44.90),
       (6, 'O Senhor dos Anéis: A Sociedade do Anel', '1954', 'HarperCollins', 1, 59.90),
       (7, 'Fundação', '1951', 'Aleph', 1, 39.50),
       (8, 'Coraline', '2002', 'Rocco', 1, 32.90),
       (9, 'O Código Da Vinci', '2003', 'Arqueiro', 1, 45.00),
       (10, 'Orgulho e Preconceito', '1813', 'Penguin', 1, 29.50),
       (11, 'Os Miseráveis', '1862', 'Martin Claret', 1, 79.90),
       (12, 'Cem Anos de Solidão', '1967', 'Record', 1, 38.90),
       (13, 'A Metamorfose', '1915', 'Companhia das Letras', 1, 22.90),
       (14, 'Frankenstein', '1818', 'Penguin', 1, 27.50),
       (15, 'Kafka à Beira-Mar', '2002', 'Objetiva', 1, 49.00)
    ON CONFLICT (codl) DO NOTHING;

-- ============================================
-- RELAÇÃO LIVRO_AUTOR
-- (ajustada para livro_codl e autor_codau)
-- ============================================

INSERT INTO livro_autor (livro_codl, autor_codau)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 4),
       (5, 5),
       (6, 6),
       (7, 7),
       (8, 8),
       (9, 9),
       (10, 10),
       (11, 11),
       (12, 12),
       (13, 13),
       (14, 14),
       (15, 15),

-- Relacionamentos adicionais (livros com múltiplos autores)
       (6, 8),
       (7, 2),
       (9, 3),
       (12, 6),
       (15, 12)
    ON CONFLICT (livro_codl, autor_codau) DO NOTHING;

-- ============================================
-- RELAÇÃO LIVRO_ASSUNTO
-- (ajustada para livro_codl e assunto_codas)
-- ============================================

INSERT INTO livro_assunto (livro_codl, assunto_codas)
VALUES (1, 1),
       (2, 9),
       (3, 2),
       (4, 7),
       (5, 4),
       (6, 8),
       (7, 5),
       (8, 13),
       (9, 10),
       (10, 1),
       (11, 6),
       (12, 14),
       (13, 12),
       (14, 13),
       (15, 12),

-- múltiplos assuntos
       (3, 11),
       (6, 2),
       (6, 11),
       (7, 14),
       (9, 7),
       (9, 10),
       (5, 13),
       (12, 6),
       (12, 13),
       (15, 14)
    ON CONFLICT (livro_codl, assunto_codas) DO NOTHING;
