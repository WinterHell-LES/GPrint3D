use crud_gprint3d;

/*--- Admin
-- Usuarios
insert into usuarios (usu_email, usu_senha, usu_regra, usu_ativo)
values ('admin@gprint3d.com', '$2a$10$TMfY1IunLWQy/wfKgltNZ.jyaJObeOdAfBK2VPicJzVh10P0nnDQO', 'ROLE_ADM', true);*/

-- Clientes
-- Usuario - Cliente 1
insert into usuarios (usu_email, usu_senha, usu_regra, usu_ativo)
values ('cliente1@gprint3d.com', '$2a$10$TMfY1IunLWQy/wfKgltNZ.jyaJObeOdAfBK2VPicJzVh10P0nnDQO', 'ROLE_CLI', true);

-- Cliente
insert into clientes (cli_ranking, cli_nome, cli_sexo, cli_dtnasc, cli_usu_id)
values ('Bronze', 'Sérgio Levi Yuri Melo', 'Masculino', '1945-09-23', '2');

-- Documento
insert into documentos (doc_tipo, doc_numero, doc_cli_id)
values ('CPF', '91294168509', '1');

-- Endereço
insert into enderecos (end_entrega, end_cobranca, end_descricao, end_tipolog, end_logradouro, end_numero, end_cep, end_complemento, end_cidade, end_estado, end_pais, end_observacao, end_cli_id)
values (true, false, 'Casa', 'Rua', 'General Osório', '683', '79904596', null, 'Ponta Porã', 'Mato Grosso do Sul', 'Brasil', null, '1');
insert into enderecos (end_entrega, end_cobranca, end_descricao, end_tipolog, end_logradouro, end_numero, end_cep, end_complemento, end_cidade, end_estado, end_pais, end_observacao, end_cli_id)
values (false, true, 'Mãe', 'Rua', 'General Osório', '683', '79904596', null, 'Ponta Porãs', 'Mato Grosso do Sul', 'Brasil', null, '1');

-- Telefones
insert into telefones (tel_tipo, tel_ddd, tel_numero, tel_cli_id)
values ('Comercial', '67', '36975899', '1');



-- Usuario - Cliente 2
insert into usuarios (usu_email, usu_senha, usu_regra, usu_ativo)
values ('cliente2@gprint3d.com', '$2a$10$TMfY1IunLWQy/wfKgltNZ.jyaJObeOdAfBK2VPicJzVh10P0nnDQO', 'ROLE_CLI', true);

-- Cliente
insert into clientes (cli_ranking, cli_nome, cli_sexo, cli_dtnasc, cli_usu_id)
values ('Bronze', 'Cauã Luiz Lima', 'Masculino', '1954-09-21', '3');

-- Documento
insert into documentos (doc_tipo, doc_numero, doc_cli_id)
values ('CPF', '03784356397', '2');

-- Endereço
insert into enderecos (end_entrega, end_cobranca, end_descricao, end_tipolog, end_logradouro, end_numero, end_cep, end_complemento, end_cidade, end_estado, end_pais, end_observacao, end_cli_id)
values (true, false, 'Casa', 'Rua', 'Manoel Miguel Alves Filho', '905', '81940500', null, 'Curitiba', 'Paraná', 'Brasil', null, '2');
insert into enderecos (end_entrega, end_cobranca, end_descricao, end_tipolog, end_logradouro, end_numero, end_cep, end_complemento, end_cidade, end_estado, end_pais, end_observacao, end_cli_id)
values (false, true, 'Mãe', 'Rua', 'Manoel Miguel Alves Filho', '905', '81940500', null, 'Curitiba', 'Paraná', 'Brasil', null, '2');

-- Telefones
insert into telefones (tel_tipo, tel_ddd, tel_numero, tel_cli_id)
values ('Celular', '41', '987211324', '2');



-- Usuario - Cliente 3
insert into usuarios (usu_email, usu_senha, usu_regra, usu_ativo)
values ('cliente3@gprint3d.com', '$2a$10$TMfY1IunLWQy/wfKgltNZ.jyaJObeOdAfBK2VPicJzVh10P0nnDQO', 'ROLE_CLI', true);

-- Cliente
insert into clientes (cli_ranking, cli_nome, cli_sexo, cli_dtnasc, cli_usu_id)
values ('Bronze', 'Renato Nicolas Lorenzo Araújo', 'Masculino', '1945-03-20', '4');

-- Documento
insert into documentos (doc_tipo, doc_numero, doc_cli_id)
values ('CPF', '35308521719', '3');

-- Endereço
insert into enderecos (end_entrega, end_cobranca, end_descricao, end_tipolog, end_logradouro, end_numero, end_cep, end_complemento, end_cidade, end_estado, end_pais, end_observacao, end_cli_id)
values (true, false, 'Casa', 'Rua', 'Treze', '906', '78053733', null, 'Cuiabá', 'Mato Grosso', 'Brasil', null, '3');
insert into enderecos (end_entrega, end_cobranca, end_descricao, end_tipolog, end_logradouro, end_numero, end_cep, end_complemento, end_cidade, end_estado, end_pais, end_observacao, end_cli_id)
values (false, true, 'Mãe', 'Rua', 'Treze', '906', '78053733', null, 'Cuiabá', 'Mato Grosso', 'Brasil', null, '3');

-- Telefones
insert into telefones (tel_tipo, tel_ddd, tel_numero, tel_cli_id)
values ('Comercial', '65', '36706873', '3');



-- Usuario - Cliente 4
insert into usuarios (usu_email, usu_senha, usu_regra, usu_ativo)
values ('cliente4@gprint3d.com', '$2a$10$TMfY1IunLWQy/wfKgltNZ.jyaJObeOdAfBK2VPicJzVh10P0nnDQO', 'ROLE_CLI', true);

-- Cliente
insert into clientes (cli_ranking, cli_nome, cli_sexo, cli_dtnasc, cli_usu_id)
values ('Bronze', 'Theo Heitor Henrique da Paz', 'Masculino', '1975-06-14', '5');

-- Documento
insert into documentos (doc_tipo, doc_numero, doc_cli_id)
values ('CPF', '58748412708', '4');

-- Endereço
insert into enderecos (end_entrega, end_cobranca, end_descricao, end_tipolog, end_logradouro, end_numero, end_cep, end_complemento, end_cidade, end_estado, end_pais, end_observacao, end_cli_id)
values (true, false, 'Casa', 'Rua', 'Guaçuí', '810', '29177280', null, 'Serra', 'Espiríto Santo', 'Brasil', null, '4');
insert into enderecos (end_entrega, end_cobranca, end_descricao, end_tipolog, end_logradouro, end_numero, end_cep, end_complemento, end_cidade, end_estado, end_pais, end_observacao, end_cli_id)
values (false, true, 'Mãe', 'Rua', 'Guaçuí', '810', '29177280', null, 'Serra', 'Espiríto Santo', 'Brasil', null, '4');

-- Telefones
insert into telefones (tel_tipo, tel_ddd, tel_numero, tel_cli_id)
values ('Celular', '27', '995943161', '4');



-- Usuario - Cliente 5
insert into usuarios (usu_email, usu_senha, usu_regra, usu_ativo)
values ('cliente5@gprint3d.com', '$2a$10$TMfY1IunLWQy/wfKgltNZ.jyaJObeOdAfBK2VPicJzVh10P0nnDQO', 'ROLE_CLI', true);

-- Cliente
insert into clientes (cli_ranking, cli_nome, cli_sexo, cli_dtnasc, cli_usu_id)
values ('Bronze', 'Calebe Mateus Daniel Barbosa', 'Feminino', '1955-03-10', '6');

-- Documento
insert into documentos (doc_tipo, doc_numero, doc_cli_id)
values ('CPF', '47196638344', '5');

-- Endereço
insert into enderecos (end_entrega, end_cobranca, end_descricao, end_tipolog, end_logradouro, end_numero, end_cep, end_complemento, end_cidade, end_estado, end_pais, end_observacao, end_cli_id)
values (true, false, 'Casa', 'Rua', 'Antônio Lucas de Carvalho', '884', '58419373', null, 'Campina Grande', 'Paraíba', 'Brasil', null, '5');
insert into enderecos (end_entrega, end_cobranca, end_descricao, end_tipolog, end_logradouro, end_numero, end_cep, end_complemento, end_cidade, end_estado, end_pais, end_observacao, end_cli_id)
values (true, false, 'Mãe', 'Rua', 'Antônio Lucas de Carvalho', '884', '58419373', null, 'Campina Grande', 'Paraíba', 'Brasil', null, '5');

-- Telefones
insert into telefones (tel_tipo, tel_ddd, tel_numero, tel_cli_id)
values ('Celular', '83', '983300010', '5');