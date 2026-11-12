-- =====================================================================
-- Novas colunas da tabela PRODUTO (sintaxe H2).
-- O Hibernate (ddl-auto=update) já cria as colunas a partir da entidade;
-- os ALTER abaixo garantem o mesmo esquema em bancos que já existiam e
-- não fazem nada quando a coluna já está lá (IF NOT EXISTS).
-- =====================================================================
ALTER TABLE produto ADD COLUMN IF NOT EXISTS status BOOLEAN DEFAULT TRUE NOT NULL;
ALTER TABLE produto ADD COLUMN IF NOT EXISTS cor VARCHAR(50);

-- =====================================================================
-- Carga inicial. MERGE ... KEY (nome): insere o produto novo ou atualiza
-- status e cor se o nome já existir (o nome é único), então pode rodar
-- mais de uma vez sem erro. status alterna TRUE/FALSE na ordem da lista.
-- =====================================================================
MERGE INTO produto (nome, tamanho, preco, tipo, imagem, status, cor) KEY (nome)
VALUES
    ('Camisa Militar', 'GG', 24.90, 'Algodão', 'camisa-militar.svg', TRUE, 'Verde Militar'),
    ('Camisa Polo Listrada', 'M', 39.90, 'Algodão', 'camisa-polo-listrada.svg', FALSE, 'Azul Marinho'),
    ('Camiseta Básica Branca', 'G', 19.90, 'Algodão', 'camiseta-basica-branca.svg', TRUE, 'Branco'),
    ('Camiseta Estampa Floral', 'P', 29.90, 'Malha', 'camiseta-estampa-floral.svg', FALSE, 'Rosa Claro'),
    ('Camiseta Oversized Preta', 'GG', 44.90, 'Algodão', 'camiseta-oversized-preta.svg', TRUE, 'Preto'),
    ('Regata Fitness', 'M', 22.90, 'Poliéster', 'regata-fitness.svg', FALSE, 'Grafite'),
    ('Blusa de Moletom Cinza', 'G', 69.90, 'Moletom', 'blusa-de-moletom-cinza.svg', TRUE, 'Cinza'),
    ('Moletom com Capuz Rosa', 'M', 84.90, 'Moletom', 'moletom-com-capuz-rosa.svg', FALSE, 'Rosa'),
    ('Jaqueta Jeans', 'G', 119.90, 'Jeans', 'jaqueta-jeans.svg', TRUE, 'Azul Jeans'),
    ('Jaqueta Corta-Vento', 'M', 89.90, 'Poliéster', 'jaqueta-corta-vento.svg', FALSE, 'Verde Petróleo'),
    ('Calça Jeans Skinny', '38', 99.90, 'Jeans', 'calca-jeans-skinny.svg', TRUE, 'Azul Escuro'),
    ('Calça Jeans Reta', '42', 109.90, 'Jeans', 'calca-jeans-reta.svg', FALSE, 'Azul Jeans Escuro'),
    ('Calça Jogger', 'M', 59.90, 'Moletom', 'calca-jogger.svg', TRUE, 'Cinza Chumbo'),
    ('Calça Alfaiataria Preta', '40', 94.90, 'Viscose', 'calca-alfaiataria-preta.svg', FALSE, 'Preto'),
    ('Bermuda Cargo', '42', 54.90, 'Sarja', 'bermuda-cargo.svg', TRUE, 'Caqui'),
    ('Bermuda Jeans', '40', 49.90, 'Jeans', 'bermuda-jeans.svg', FALSE, 'Azul Jeans'),
    ('Short Saia Plissado', 'P', 34.90, 'Poliéster', 'short-saia-plissado.svg', TRUE, 'Lilás'),
    ('Saia Midi Floral', 'M', 57.90, 'Viscose', 'saia-midi-floral.svg', FALSE, 'Pêssego'),
    ('Vestido Midi Rosa', 'M', 79.90, 'Viscose', 'vestido-midi-rosa.svg', TRUE, 'Rosa'),
    ('Vestido Curto Listrado', 'P', 64.90, 'Malha', 'vestido-curto-listrado.svg', FALSE, 'Rosa e Branco'),
    ('Cropped Canelado', 'P', 27.90, 'Canelado', 'cropped-canelado.svg', TRUE, 'Bege'),
    ('Cardigan de Tricô', 'G', 74.90, 'Tricô', 'cardigan-de-trico.svg', FALSE, 'Caramelo'),
    ('Camisa Social Branca', 'M', 64.90, 'Algodão', 'camisa-social-branca.svg', TRUE, 'Branco'),
    ('Camisa Xadrez Flanela', 'G', 52.90, 'Flanela', 'camisa-xadrez-flanela.svg', FALSE, 'Vermelho');
