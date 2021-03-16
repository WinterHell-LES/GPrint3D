use crud_gprint3d;

--- Admin
-- Usuarios
insert into usuarios (usu_email, usu_senha, usu_regra, usu_ativo)
values ('admin@gprint3d.com', '$2a$10$TMfY1IunLWQy/wfKgltNZ.jyaJObeOdAfBK2VPicJzVh10P0nnDQO', 'ROLE_ADM', '1');

-- Bandeiras
insert into bandeiras (ban_nome)
values ('Mastercard');
insert into bandeiras (ban_nome)
values ('Visa');
insert into bandeiras (ban_nome)
values ('Elo');

-- Categorias
insert into categorias (ctg_nome, ctg_descricao)
values ('Impressoras 3D', '');
insert into categorias (ctg_nome, ctg_descricao)
values ('Filamentos ABS', '');
insert into categorias (ctg_nome, ctg_descricao)
values ('Filamentos PLA', '');
insert into categorias (ctg_nome, ctg_descricao)
values ('Filamentos PETG', '');
insert into categorias (ctg_nome, ctg_descricao)
values ('Resinas', '');
insert into categorias (ctg_nome, ctg_descricao)
values ('Partes', '');

-- Clientes
-- Usuario - Cliente 1
insert into usuarios (usu_email, usu_senha, usu_regra, usu_ativo)
values ('cliente1@gprint3d.com', '$2a$10$G6feYQ9OLuVyvic3WUJ/3ebR1jAJOuj2Qaob9elCNIGjMcoi5x8Jq', 'ROLE_CLI', '1');

-- Cliente
insert into clientes (cli_ranking, cli_nome, cli_sexo, cli_dtnasc, cli_usu_id)
values ('0', 'Sérgio Levi', 'Masculino', '1945-09-23', '2');

-- Documento
insert into documentos (doc_tipo, doc_numero, doc_cli_id)
values ('CPF', '912.941.685-09', '1');

-- Endereço
insert into enderecos (end_entrega, end_cobranca, end_descricao, end_tipolog, end_logradouro, end_numero, end_cep, end_complemento, end_cidade, end_estado, end_pais, end_observacao, end_cli_id)
values ('1', '0', 'Casa', 'Rua', 'General Osório', '683', '79904-596', null, 'Ponta Porã', 'MS', 'Brasil', null, '1');
insert into enderecos (end_entrega, end_cobranca, end_descricao, end_tipolog, end_logradouro, end_numero, end_cep, end_complemento, end_cidade, end_estado, end_pais, end_observacao, end_cli_id)
values ('0', '1', 'Mãe', 'Rua', 'General Osório', '683', '79904-596', null, 'Ponta Porãs', 'MS', 'Brasil', null, '1');

insert into enderecos_cobrancas_padroes (ecp_end_id, ecp_cli_id)
values ('1', '1');
insert into enderecos_entregas_padroes (eep_end_id, eep_cli_id)
values ('2', '1');

-- Telefones
insert into telefones (tel_tipo, tel_ddd, tel_numero, tel_cli_id)
values ('Comercial', '(67)', '3697-5899', '1');

-- Cartao
insert into cartoes(crt_nome, crt_numero, crt_validade, crt_cvv, crt_ban_id, crt_cli_id)
values ('Sérgio Levi Yuri Melo', '1234 5688 8885 5221', '07/20', '333', '1', '1');

-- Cartao Padrao
insert into cartoes_padroes (ctp_crt_id, ctp_cli_id)
values ('1', '1');



-- Usuario - Cliente 2
insert into usuarios (usu_email, usu_senha, usu_regra, usu_ativo)
values ('cliente2@gprint3d.com', '$2a$10$G6feYQ9OLuVyvic3WUJ/3ebR1jAJOuj2Qaob9elCNIGjMcoi5x8Jq', 'ROLE_CLI', '1');

-- Cliente
insert into clientes (cli_ranking, cli_nome, cli_sexo, cli_dtnasc, cli_usu_id)
values ('0', 'Cauã Luiz Lima', 'Masculino', '1954-09-21', '3');

-- Documento
insert into documentos (doc_tipo, doc_numero, doc_cli_id)
values ('CPF', '037.843.563-97', '2');

-- Endereço
insert into enderecos (end_entrega, end_cobranca, end_descricao, end_tipolog, end_logradouro, end_numero, end_cep, end_complemento, end_cidade, end_estado, end_pais, end_observacao, end_cli_id)
values ('1', '0', 'Casa', 'Rua', 'Manoel Miguel Alves Filho', '905', '81940-500', null, 'Curitiba', 'PR', 'Brasil', null, '2');
insert into enderecos (end_entrega, end_cobranca, end_descricao, end_tipolog, end_logradouro, end_numero, end_cep, end_complemento, end_cidade, end_estado, end_pais, end_observacao, end_cli_id)
values ('0', '1', 'Mãe', 'Rua', 'Manoel Miguel Alves Filho', '905', '81940-500', null, 'Curitiba', 'PR', 'Brasil', null, '2');

insert into enderecos_cobrancas_padroes (ecp_end_id, ecp_cli_id)
values ('3', '2');
insert into enderecos_entregas_padroes (eep_end_id, eep_cli_id)
values ('4', '2');

-- Telefones
insert into telefones (tel_tipo, tel_ddd, tel_numero, tel_cli_id)
values ('Celular', '(41)', '98721-1324', '2');

-- Cartao
insert into cartoes (crt_nome, crt_numero, crt_validade, crt_cvv, crt_ban_id, crt_cli_id)
values ('Cauã Luiz Lima', '1234 5688 8885 5221', '07/20', '333', '1', '2');

-- Cartao Padrao
insert into cartoes_padroes (ctp_crt_id, ctp_cli_id)
values ('2', '2');


-- Usuario - Cliente 3
insert into usuarios (usu_email, usu_senha, usu_regra, usu_ativo)
values ('cliente3@gprint3d.com', '$2a$10$G6feYQ9OLuVyvic3WUJ/3ebR1jAJOuj2Qaob9elCNIGjMcoi5x8Jq', 'ROLE_CLI', '1');

-- Cliente
insert into clientes (cli_ranking, cli_nome, cli_sexo, cli_dtnasc, cli_usu_id)
values ('0', 'Renato Nicolas Lorenzo Araújo', 'Masculino', '1945-03-20', '4');

-- Documento
insert into documentos (doc_tipo, doc_numero, doc_cli_id)
values ('CPF', '353.085.217-19', '3');

-- Endereço
insert into enderecos (end_entrega, end_cobranca, end_descricao, end_tipolog, end_logradouro, end_numero, end_cep, end_complemento, end_cidade, end_estado, end_pais, end_observacao, end_cli_id)
values ('1', '0', 'Casa', 'Rua', 'Treze', '906', '78053-733', null, 'Cuiabá', 'MG', 'Brasil', null, '3');
insert into enderecos (end_entrega, end_cobranca, end_descricao, end_tipolog, end_logradouro, end_numero, end_cep, end_complemento, end_cidade, end_estado, end_pais, end_observacao, end_cli_id)
values ('0', '1', 'Mãe', 'Rua', 'Treze', '906', '78053-733', null, 'Cuiabá', 'MG', 'Brasil', null, '3');

insert into enderecos_cobrancas_padroes (ecp_end_id, ecp_cli_id)
values ('5', '3');
insert into enderecos_entregas_padroes (eep_end_id, eep_cli_id)
values ('6', '3');

-- Telefones
insert into telefones (tel_tipo, tel_ddd, tel_numero, tel_cli_id)
values ('Comercial', '(65)', '36706873', '3');

-- Cartao
insert into cartoes (crt_nome, crt_numero, crt_validade, crt_cvv, crt_ban_id, crt_cli_id)
values ('Renato Nicolas Lorenzo Araújo', '1234 5688 8885 5221', '07/20', '333', '1', '3');

-- Cartao Padrao
insert into cartoes_padroes (ctp_crt_id, ctp_cli_id)
values ('3', '3');



-- Usuario - Cliente 4
insert into usuarios (usu_email, usu_senha, usu_regra, usu_ativo)
values ('cliente4@gprint3d.com', '$2a$10$G6feYQ9OLuVyvic3WUJ/3ebR1jAJOuj2Qaob9elCNIGjMcoi5x8Jq', 'ROLE_CLI', '1');

-- Cliente
insert into clientes (cli_ranking, cli_nome, cli_sexo, cli_dtnasc, cli_usu_id)
values ('0', 'Theo Heitor Henrique da Paz', 'Masculino', '1975-06-14', '5');

-- Documento
insert into documentos (doc_tipo, doc_numero, doc_cli_id)
values ('CPF', '587.484.127-08', '4');

-- Endereço
insert into enderecos (end_entrega, end_cobranca, end_descricao, end_tipolog, end_logradouro, end_numero, end_cep, end_complemento, end_cidade, end_estado, end_pais, end_observacao, end_cli_id)
values ('1', '0', 'Casa', 'Rua', 'Guaçuí', '810', '29177-280', null, 'Serra', 'ES', 'Brasil', null, '4');
insert into enderecos (end_entrega, end_cobranca, end_descricao, end_tipolog, end_logradouro, end_numero, end_cep, end_complemento, end_cidade, end_estado, end_pais, end_observacao, end_cli_id)
values ('0', '1', 'Mãe', 'Rua', 'Guaçuí', '810', '29177-280', null, 'Serra', 'ES', 'Brasil', null, '4');

insert into enderecos_cobrancas_padroes (ecp_end_id, ecp_cli_id)
values ('7', '4');
insert into enderecos_entregas_padroes (eep_end_id, eep_cli_id)
values ('8', '4');

-- Telefones
insert into telefones (tel_tipo, tel_ddd, tel_numero, tel_cli_id)
values ('Celular', '(27)', '99594-3161', '4');

-- Cartao
insert into cartoes (crt_nome, crt_numero, crt_validade, crt_cvv, crt_ban_id, crt_cli_id)
values ('Theo Heitor Henrique da Paz', '1234 5688 8885 5221', '07/20', '333', '1', '4');

-- Cartao Padrao
insert into cartoes_padroes (ctp_crt_id, ctp_cli_id)
values ('4', '4');



-- Usuario - Cliente 5
insert into usuarios (usu_email, usu_senha, usu_regra, usu_ativo)
values ('cliente5@gprint3d.com', '$2a$10$G6feYQ9OLuVyvic3WUJ/3ebR1jAJOuj2Qaob9elCNIGjMcoi5x8Jq', 'ROLE_CLI', '1');

-- Cliente
insert into clientes (cli_ranking, cli_nome, cli_sexo, cli_dtnasc, cli_usu_id)
values ('0', 'Calebe Mateus Daniel Barbosa', 'Feminino', '1955-03-10', '6');

-- Documento
insert into documentos (doc_tipo, doc_numero, doc_cli_id)
values ('CPF', '471.966.383-44', '5');

-- Endereço
insert into enderecos (end_entrega, end_cobranca, end_descricao, end_tipolog, end_logradouro, end_numero, end_cep, end_complemento, end_cidade, end_estado, end_pais, end_observacao, end_cli_id)
values ('1', '0', 'Casa', 'Rua', 'Antônio Lucas de Carvalho', '884', '58419373', null, 'Campina Grande', 'PB', 'Brasil', null, '5');
insert into enderecos (end_entrega, end_cobranca, end_descricao, end_tipolog, end_logradouro, end_numero, end_cep, end_complemento, end_cidade, end_estado, end_pais, end_observacao, end_cli_id)
values ('0', '1', 'Mãe', 'Rua', 'Antônio Lucas de Carvalho', '884', '58419373', null, 'Campina Grande', 'PB', 'Brasil', null, '5');

insert into enderecos_cobrancas_padroes (ecp_end_id, ecp_cli_id)
values ('9', '5');
insert into enderecos_entregas_padroes (eep_end_id, eep_cli_id)
values ('10', '5');

-- Telefones
insert into telefones (tel_tipo, tel_ddd, tel_numero, tel_cli_id)
values ('Celular', '(83)', '98330-0010', '5');

-- Cartao
insert into cartoes (crt_nome, crt_numero, crt_validade, crt_cvv, crt_ban_id, crt_cli_id)
values ('Calebe Mateus Daniel Barbosa', '1234 5688 8885 5221', '07/20', '333', '1', '5');

-- Cartao Padrao
insert into cartoes_padroes (ctp_crt_id, ctp_cli_id)
values ('5', '5');