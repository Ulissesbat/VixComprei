
INSERT INTO tb_categoria (nome, tipo, descricao) VALUES ( 'Eletrônicos', 'PRODUTO', 'Produtos de tecnologia');
INSERT INTO tb_categoria (nome, tipo, descricao) VALUES ( 'Informática', 'SERVICO', 'Serviços técnicos de informática');
INSERT INTO tb_categoria (nome, tipo, descricao) VALUES ( 'Alimentação', 'RESTAURANTE', 'Restaurantes e lanchonetes');

INSERT INTO tb_usuario (nome, email, senha, telefone, endereco, data_cadastro, tipo_usuario) VALUES ('João Silva', 'joao@gmail.com', '123456', '11999990001', 'Rua A, 123', '2025-01-10T10:00:00', 'CLIENTE');
INSERT INTO tb_usuario (nome, email, senha, telefone, endereco, data_cadastro, tipo_usuario) VALUES ( 'Maria Oliveira', 'maria@gmail.com', '123456', '11999990002', 'Rua B, 456', '2025-01-11T11:00:00', 'VENDEDOR');
INSERT INTO tb_usuario (nome, email, senha, telefone, endereco, data_cadastro, tipo_usuario) VALUES ( 'Carlos Sousa', 'carlos@gmail.com', '123456', '11999990003', 'Rua C, 789', '2025-01-12T12:00:00', 'PRESTADOR_SERVICO');
INSERT INTO tb_usuario (nome, email, senha, telefone, endereco, data_cadastro, tipo_usuario) VALUES ( 'Rest. Dona Ana', 'ana@restaurante.com', '123456', '11999990004', 'Rua D, 321', '2025-01-13T13:00:00', 'RESTAURANTE');

INSERT INTO tb_servico (titulo, descricao, preco_base, categoria_id, usuario_id, disponivel) VALUES ('Manutenção de Computador', 'Limpeza e otimização completa', 120.00, 1, 3, true);
INSERT INTO tb_servico (titulo, descricao, preco_base, categoria_id, usuario_id, disponivel) VALUES ('Instalação de Rede WiFi', 'Montagem, testes e configuração de rede residencial', 90.00, 1, 3, true);
INSERT INTO tb_servico (titulo, descricao, preco_base, categoria_id, usuario_id, disponivel) VALUES ( 'Design Gráfico', 'Criação de artes digitais, banners e flyers', 75.00, 2, 4, true);


INSERT INTO tb_restaurante (nome, descricao, categoria_id, endereco, telefone, tempo_medio_entrega, usuario_id) VALUES ( 'Dona Ana Lanches', 'Lanches artesanais', 3, 'Rua D, 321', '11999990004', 40, 4);

INSERT INTO tb_produto (nome, descricao, preco, condicao_produto, categoria_id, usuario_id, data_publicacao, ativo) VALUES ('Notebook Dell', 'Notebook Dell Inspiron 14', 3500.00, 'USADO', 1, 2, '2025-01-15T10:30:00', true);
INSERT INTO tb_produto (nome, descricao, preco, condicao_produto, categoria_id, usuario_id, data_publicacao, ativo) VALUES ('Mouse Gamer', 'Mouse RGB 7200DPI', 150.00, 'NOVO', 1, 2, '2025-01-15T11:00:00', true);
INSERT INTO tb_produto (nome, descricao, preco, condicao_produto, categoria_id, usuario_id, data_publicacao, ativo) VALUES ('X-Burger', 'Hambúrguer artesanal', 28.00, 'NOVO', 3, 1, '2025-01-15T11:15:00', true);

INSERT INTO tb_pedido (data_pedido, status, total, tipo_origem, usuario_id) VALUES ( '2025-01-20T14:30:00', 'CRIADO', 3650.00, 'DESAPEGA', 1);
INSERT INTO tb_pedido (data_pedido, status, total, tipo_origem, usuario_id) VALUES ( '2025-01-21T12:00:00', 'CONFIRMADO', 28.00, 'RESTAURANTE', 1);

INSERT INTO tb_item_pedido (pedido_id, produto_id, quantidade, preco_unitario, sub_total) VALUES (1, 1, 1, 3500.00, 3500.00);
INSERT INTO tb_item_pedido (pedido_id, produto_id, quantidade, preco_unitario, sub_total) VALUES (1, 2, 1, 150.00, 150.00);
INSERT INTO tb_item_pedido (pedido_id, produto_id, quantidade, preco_unitario, sub_total) VALUES (2, 3, 1, 28.00, 28.00);

INSERT INTO tb_pagamento (tipo_pagamento, status, valor, momento, pedido_id) VALUES ('PIX', 'CRIADO', 3650.00, '2025-01-20T14:35:00Z', 1);
INSERT INTO tb_pagamento (tipo_pagamento, status, valor, momento, pedido_id) VALUES ('DINHEIRO', 'CONFIRMADO', 28.00, '2025-01-21T12:05:00Z', 2);
