-- Fornecedores (sem alteração)
INSERT INTO FORNECEDOR (nome, contato) VALUES ('Fornecedor de Papelaria', 'contato@papel.com');
INSERT INTO FORNECEDOR (nome, contato) VALUES ('Fornecedor de Eletrônicos', 'contato@eletro.com');

-- Produtos (com os novos campos e colunas)
INSERT INTO PRODUTO (nome, valor, descontos, observacoes, data_de_entrega, taxas, fornecedor_id, garantia, data_pagamento) VALUES ('Caneta Azul', 1.50, 0, 'Descrição: Caneta esferográfica de ponta fina.', '2025-10-20', 0, 1, '3 meses', '2025-11-20');
INSERT INTO PRODUTO (nome, valor, descontos, observacoes, data_de_entrega, taxas, fornecedor_id, garantia, data_pagamento) VALUES ('Mouse Sem Fio', 89.90, 10, 'Descrição: Mouse óptico com 3 botões.', '2025-10-15', 5, 2, '12 meses', '2025-11-15');
INSERT INTO PRODUTO (nome, valor, descontos, observacoes, data_de_entrega, taxas, fornecedor_id, garantia, data_pagamento) VALUES ('Caderno 96 Folhas', 12.00, 0, 'Descrição: Caderno universitário com capa dura.', '2025-10-20', 0, 1, 'N/A', '2025-11-20');