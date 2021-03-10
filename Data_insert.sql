use crud_gprint3d;

/*--- Admin
-- Usuarios
insert into usuarios (usu_email, usu_senha, usu_regra, usu_ativo)
values ('admin@gprint3d.com', '$2a$10$TMfY1IunLWQy/wfKgltNZ.jyaJObeOdAfBK2VPicJzVh10P0nnDQO', 'ROLE_ADM', '1');*/

-- Clientes
-- Usuario - Cliente 1
insert into usuarios (usu_email, usu_senha, usu_regra, usu_ativo)
values ('cliente1@gprint3d.com', '$2a$10$G6feYQ9OLuVyvic3WUJ/3ebR1jAJOuj2Qaob9elCNIGjMcoi5x8Jq', 'ROLE_CLI', '1');

-- Cliente
insert into clientes (cli_ranking, cli_nome, cli_sexo, cli_dtnasc, cli_usu_id)
values ('0', 'Sérgio Levi', 'Masculino', '1945-09-23', '2');

-- Documento
insert into documentos (doc_tipo, doc_numero, doc_cli_id)
values ('CPF', '91294168509', '1');

-- Endereço
insert into enderecos (end_entrega, end_ent_padrao, end_cobranca, end_cob_padrao, end_descricao, end_tipolog, end_logradouro, end_numero, end_cep, end_complemento, end_cidade, end_estado, end_pais, end_observacao, end_cli_id)
values ('1', '1', '0', '0', 'Casa', 'Rua', 'General Osório', '683', '79904596', null, 'Ponta Porã', 'MS', 'Brasil', null, '1');
insert into enderecos (end_entrega, end_ent_padrao, end_cobranca, end_cob_padrao, end_descricao, end_tipolog, end_logradouro, end_numero, end_cep, end_complemento, end_cidade, end_estado, end_pais, end_observacao, end_cli_id)
values ('0', '0', '1', '1', 'Mãe', 'Rua', 'General Osório', '683', '79904596', null, 'Ponta Porãs', 'MS', 'Brasil', null, '1');

-- Telefones
insert into telefones (tel_tipo, tel_ddd, tel_numero, tel_cli_id)
values ('Comercial', '(67)', '36975899', '1');

-- Cartao
insert into cartoes(crt_bandeira, crt_padrao, crt_nome, crt_numero, crt_validade, crt_cvv, crt_cli_id)
values ('Visa', '1', 'Sérgio Levi Yuri Melo', '1234 5688 8885 5221', '07/20', '333', '1');



-- Usuario - Cliente 2
insert into usuarios (usu_email, usu_senha, usu_regra, usu_ativo)
values ('cliente2@gprint3d.com', '$2a$10$G6feYQ9OLuVyvic3WUJ/3ebR1jAJOuj2Qaob9elCNIGjMcoi5x8Jq', 'ROLE_CLI', '1');

-- Cliente
insert into clientes (cli_ranking, cli_nome, cli_sexo, cli_dtnasc, cli_usu_id)
values ('0', 'Cauã Luiz Lima', 'Masculino', '1954-09-21', '3');

-- Documento
insert into documentos (doc_tipo, doc_numero, doc_cli_id)
values ('CPF', '03784356397', '2');

-- Endereço
insert into enderecos (end_entrega, end_ent_padrao, end_cobranca, end_cob_padrao, end_descricao, end_tipolog, end_logradouro, end_numero, end_cep, end_complemento, end_cidade, end_estado, end_pais, end_observacao, end_cli_id)
values ('1', '1', '0', '0', 'Casa', 'Rua', 'Manoel Miguel Alves Filho', '905', '81940500', null, 'Curitiba', 'PR', 'Brasil', null, '2');
insert into enderecos (end_entrega, end_ent_padrao, end_cobranca, end_cob_padrao, end_descricao, end_tipolog, end_logradouro, end_numero, end_cep, end_complemento, end_cidade, end_estado, end_pais, end_observacao, end_cli_id)
values ('0', '0', '1', '1', 'Mãe', 'Rua', 'Manoel Miguel Alves Filho', '905', '81940500', null, 'Curitiba', 'PR', 'Brasil', null, '2');

-- Telefones
insert into telefones (tel_tipo, tel_ddd, tel_numero, tel_cli_id)
values ('Celular', '(41)', '987211324', '2');

-- Cartao
insert into cartoes (crt_bandeira, crt_padrao, crt_nome, crt_numero, crt_validade, crt_cvv, crt_cli_id)
values ('Visa', '1', 'Cauã Luiz Lima', '1234 5688 8885 5221', '07/20', '333', '2');



-- Usuario - Cliente 3
insert into usuarios (usu_email, usu_senha, usu_regra, usu_ativo)
values ('cliente3@gprint3d.com', '$2a$10$G6feYQ9OLuVyvic3WUJ/3ebR1jAJOuj2Qaob9elCNIGjMcoi5x8Jq', 'ROLE_CLI', '1');

-- Cliente
insert into clientes (cli_ranking, cli_nome, cli_sexo, cli_dtnasc, cli_usu_id)
values ('0', 'Renato Nicolas Lorenzo Araújo', 'Masculino', '1945-03-20', '4');

-- Documento
insert into documentos (doc_tipo, doc_numero, doc_cli_id)
values ('CPF', '35308521719', '3');

-- Endereço
insert into enderecos (end_entrega, end_ent_padrao, end_cobranca, end_cob_padrao, end_descricao, end_tipolog, end_logradouro, end_numero, end_cep, end_complemento, end_cidade, end_estado, end_pais, end_observacao, end_cli_id)
values ('1', '1', '0', '0', 'Casa', 'Rua', 'Treze', '906', '78053733', null, 'Cuiabá', 'MG', 'Brasil', null, '3');
insert into enderecos (end_entrega, end_ent_padrao, end_cobranca, end_cob_padrao, end_descricao, end_tipolog, end_logradouro, end_numero, end_cep, end_complemento, end_cidade, end_estado, end_pais, end_observacao, end_cli_id)
values ('0', '0', '1', '1', 'Mãe', 'Rua', 'Treze', '906', '78053733', null, 'Cuiabá', 'MG', 'Brasil', null, '3');

-- Telefones
insert into telefones (tel_tipo, tel_ddd, tel_numero, tel_cli_id)
values ('Comercial', '(65)', '36706873', '3');

-- Cartao
insert into cartoes (crt_bandeira, crt_padrao, crt_nome, crt_numero, crt_validade, crt_cvv, crt_cli_id)
values ('Visa', '1', 'Renato Nicolas Lorenzo Araújo', '1234 5688 8885 5221', '07/20', '333', '3');



-- Usuario - Cliente 4
insert into usuarios (usu_email, usu_senha, usu_regra, usu_ativo)
values ('cliente4@gprint3d.com', '$2a$10$G6feYQ9OLuVyvic3WUJ/3ebR1jAJOuj2Qaob9elCNIGjMcoi5x8Jq', 'ROLE_CLI', '1');

-- Cliente
insert into clientes (cli_ranking, cli_nome, cli_sexo, cli_dtnasc, cli_usu_id)
values ('0', 'Theo Heitor Henrique da Paz', 'Masculino', '1975-06-14', '5');

-- Documento
insert into documentos (doc_tipo, doc_numero, doc_cli_id)
values ('CPF', '58748412708', '4');

-- Endereço
insert into enderecos (end_entrega, end_ent_padrao, end_cobranca, end_cob_padrao, end_descricao, end_tipolog, end_logradouro, end_numero, end_cep, end_complemento, end_cidade, end_estado, end_pais, end_observacao, end_cli_id)
values ('1', '1', '0', '0', 'Casa', 'Rua', 'Guaçuí', '810', '29177280', null, 'Serra', 'ES', 'Brasil', null, '4');
insert into enderecos (end_entrega, end_ent_padrao, end_cobranca, end_cob_padrao, end_descricao, end_tipolog, end_logradouro, end_numero, end_cep, end_complemento, end_cidade, end_estado, end_pais, end_observacao, end_cli_id)
values ('0', '0', '1', '1', 'Mãe', 'Rua', 'Guaçuí', '810', '29177280', null, 'Serra', 'ES', 'Brasil', null, '4');

-- Telefones
insert into telefones (tel_tipo, tel_ddd, tel_numero, tel_cli_id)
values ('Celular', '(27)', '995943161', '4');

-- Cartao
insert into cartoes (crt_bandeira, crt_padrao, crt_nome, crt_numero, crt_validade, crt_cvv, crt_cli_id)
values ('Visa', '1', 'Theo Heitor Henrique da Paz', '1234 5688 8885 5221', '07/20', '333', '4');



-- Usuario - Cliente 5
insert into usuarios (usu_email, usu_senha, usu_regra, usu_ativo)
values ('cliente5@gprint3d.com', '$2a$10$G6feYQ9OLuVyvic3WUJ/3ebR1jAJOuj2Qaob9elCNIGjMcoi5x8Jq', 'ROLE_CLI', '1');

-- Cliente
insert into clientes (cli_ranking, cli_nome, cli_sexo, cli_dtnasc, cli_usu_id)
values ('0', 'Calebe Mateus Daniel Barbosa', 'Feminino', '1955-03-10', '6');

-- Documento
insert into documentos (doc_tipo, doc_numero, doc_cli_id)
values ('CPF', '47196638344', '5');

-- Endereço
insert into enderecos (end_entrega, end_ent_padrao, end_cobranca, end_cob_padrao, end_descricao, end_tipolog, end_logradouro, end_numero, end_cep, end_complemento, end_cidade, end_estado, end_pais, end_observacao, end_cli_id)
values ('1', '1', '0', '0', 'Casa', 'Rua', 'Antônio Lucas de Carvalho', '884', '58419373', null, 'Campina Grande', 'PB', 'Brasil', null, '5');
insert into enderecos (end_entrega, end_ent_padrao, end_cobranca, end_cob_padrao, end_descricao, end_tipolog, end_logradouro, end_numero, end_cep, end_complemento, end_cidade, end_estado, end_pais, end_observacao, end_cli_id)
values ('0', '0', '1', '1', 'Mãe', 'Rua', 'Antônio Lucas de Carvalho', '884', '58419373', null, 'Campina Grande', 'PB', 'Brasil', null, '5');

-- Telefones
insert into telefones (tel_tipo, tel_ddd, tel_numero, tel_cli_id)
values ('Celular', '(83)', '983300010', '5');

-- Cartao
insert into cartoes (crt_bandeira, crt_padrao, crt_nome, crt_numero, crt_validade, crt_cvv, crt_cli_id)
values ('Visa', '1', 'Calebe Mateus Daniel Barbosa', '1234 5688 8885 5221', '07/20', '333', '5');