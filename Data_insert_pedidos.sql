use crud_gprint3d;

-- Pedidos
insert into pedidos_compras(pdc_id, pdc_numero, pdc_datainicio, pdc_datafim, pdc_statuspedido, pdc_statuslogistica, pdc_cli_id, pdc_end_id)
values (1, 'GP3PDC210420221907', CURDATE(), null, 4, 5, 1, 1),
	   (2, 'GP3PDC210420222056', CURDATE(), null, 4, 5, 1, 1),
	   (3, 'GP3PDC210305222056', '2021-03-05', null, 4, 5, 2, 1),
	   (4, 'GP3PDC210320222056', '2021-03-20', null, 4, 5, 2, 1),
	   (5, 'GP3PDC210205222056', '2021-02-05', null, 4, 5, 3, 1),
	   (6, 'GP3PDC210220222056', '2021-02-20', null, 4, 5, 3, 1),
	   (7, 'GP3PDC210105222056', '2021-01-05', null, 4, 5, 4, 1),
	   (8, 'GP3PDC210120222056', '2021-01-20', null, 4, 5, 4, 1),
	   (9, 'GP3PDC201205222056', '2020-12-05', null, 4, 5, 5, 1),
	   (10, 'GP3PDC201220222056', '2020-12-20', null, 4, 5, 5, 1);


-- Produtos
insert into pedidos_compras_produtos(ppd_quantidade, ppd_status, ppd_pdc_id, ppd_prd_id)
values (1, 1, 1, 1),
	   (2, 0, 1, 2),
	   (3, 0, 1, 3),
	   (4, 0, 1, 10),
	   (5, 0, 1, 20),
	   (6, 0, 1, 6);

insert into pedidos_compras_produtos(ppd_quantidade, ppd_status, ppd_pdc_id, ppd_prd_id)
values (1, 1, 2, 10),
	   (2, 0, 2, 11),
	   (3, 1, 2, 12),
	   (4, 0, 2, 13);
       
insert into pedidos_compras_produtos(ppd_quantidade, ppd_status, ppd_pdc_id, ppd_prd_id)
values (1, 1, 3, 1),
	   (2, 0, 3, 44),
	   (3, 0, 3, 3),
	   (4, 0, 3, 30),
	   (5, 0, 3, 5),
	   (6, 0, 3, 15);

insert into pedidos_compras_produtos(ppd_quantidade, ppd_status, ppd_pdc_id, ppd_prd_id)
values (1, 1, 4, 43),
	   (2, 0, 4, 27),
	   (3, 1, 4, 12),
	   (4, 0, 4, 13);
       
insert into pedidos_compras_produtos(ppd_quantidade, ppd_status, ppd_pdc_id, ppd_prd_id)
values (1, 1, 5, 1),
	   (2, 0, 5, 2),
	   (3, 0, 5, 33),
	   (4, 0, 5, 4),
	   (5, 0, 5, 49),
	   (6, 0, 5, 6);

insert into pedidos_compras_produtos(ppd_quantidade, ppd_status, ppd_pdc_id, ppd_prd_id)
values (1, 1, 6, 10),
	   (2, 0, 6, 50),
	   (3, 1, 6, 12),
	   (4, 0, 6, 36);
       
insert into pedidos_compras_produtos(ppd_quantidade, ppd_status, ppd_pdc_id, ppd_prd_id)
values (1, 1, 7, 29),
	   (2, 0, 7, 2),
	   (3, 0, 7, 3),
	   (4, 0, 7, 13),
	   (5, 0, 7, 5),
	   (6, 0, 7, 7);

insert into pedidos_compras_produtos(ppd_quantidade, ppd_status, ppd_pdc_id, ppd_prd_id)
values (1, 1, 8, 38),
	   (2, 0, 8, 11),
	   (3, 1, 8, 43),
	   (4, 0, 8, 24);
       
insert into pedidos_compras_produtos(ppd_quantidade, ppd_status, ppd_pdc_id, ppd_prd_id)
values (1, 1, 9, 54),
	   (2, 0, 9, 53),
	   (3, 0, 9, 3),
	   (4, 0, 9, 12),
	   (5, 0, 9, 5),
	   (6, 0, 9, 14);

insert into pedidos_compras_produtos(ppd_quantidade, ppd_status, ppd_pdc_id, ppd_prd_id)
values (1, 1, 10, 28),
	   (2, 0, 10, 34),
	   (3, 1, 10, 12),
	   (4, 0, 10, 49);
       
       
-- Catões
insert into pedidos_compras_cartoes(pct_valor, pct_crt_id, pct_pdc_id)
values (18283.4, 1, 1),
	   (4923.6, 1, 2),
       (14501.4, 1, 3),
       (3130.6, 1, 4),
       (19574.4, 1, 5),
       (3976.6, 1, 6),
       (17488.4, 1, 7),
       (3572.6, 1, 8),
       (10063.4, 1, 9),
	   (4433.6, 1, 10);
       
insert into pedidos_compras_fretes(pcf_empresa, pcf_modalidade, pcf_prazo, pcf_valor, pcf_pdc_id)
values ('Correios', 'SEDEX', 2, 999.00, 1),
	   ('Correios', 'SEDEX', 3, 1111.00, 2),
       ('Correios', 'SEDEX', 2, 1234.00, 3),
       ('Correios', 'SEDEX', 4, 678.00, 4),
       ('Correios', 'SEDEX', 2, 987.00, 5),
       ('Correios', 'SEDEX', 1, 876.00, 6),
       ('Correios', 'SEDEX', 2, 976.00, 7),
       ('Correios', 'SEDEX', 3, 786.00, 8),
       ('Correios', 'SEDEX', 2, 789.00, 9),
	   ('Correios', 'SEDEX', 5, 976.00, 10);

insert into pedidos_trocas (pdt_numero, pdt_quantidade, pdt_data, pdt_escolha, pdt_statuspedido, pdt_statuslogistica, pdt_descricao, pdt_retorno, pdt_ppd_id, pdt_pdc_id, pdt_cli_id)
values ('GP3DCT210425031029', 1, '2021-04-25', 2, 2, 0, 'Defeito', 0, 1, 1, 1),
	   ('GP3DCT210425031043', 3, '2021-04-25', 2, 2, 0, 'Defeito', 0, 9, 2, 1),
       ('GP3DCT210425031057', 1, '2021-04-25', 2, 2, 0, 'Defeito', 0, 7, 2, 1);
       
insert into cupons_promocoes (cpp_nome, cpp_codigo, cpp_desconto, cpp_validade, cpp_ctg_id)
values ('GPRINT3D20OFF', 'GP3DCP210425173316', 20, '2050-01-01', 1);

insert into cupons_trocas (cpt_status, cpt_data, cpt_validade, cpt_valor, cpt_saldo, cpt_codigo, cpt_cli_id)
values (1, '2021-04-25', '2021-08-03', 1899.90, 1899.90, 'GP3DCT210425031219', 1),
	   (1, '2021-04-25', '2021-08-03', 419.70, 419.870, 'GP3DCT210425031226', 1),
       (1, '2021-04-25', '2021-08-03', 99.90, 99.90, 'GP3DCT210425031231', 1);