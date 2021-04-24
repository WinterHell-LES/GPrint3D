use crud_gprint3d;

-- Admin senha admin
insert into usuarios (usu_email, usu_senha, usu_regra, usu_ativo)
values ('admin@gprint3d.com', '$2a$10$TMfY1IunLWQy/wfKgltNZ.jyaJObeOdAfBK2VPicJzVh10P0nnDQO', 'ROLE_ADM', '1');

-- Variáveis
insert into sis_variaveis (var_id, var_cep, var_categoria, var_temptroca, var_tempcarrinho, var_validcupom, var_rank_1, var_rank_2, var_rank_3, var_rank_4, var_cor_almax, var_cor_almin, var_cor_lamax, var_cor_lamin, var_cor_prmax, var_cor_prmin, var_cor_somdimmax, var_cor_somdimmin, var_cor_pemax, var_cor_pemin)
values (1, '08745-290', 2, 7, 7, 100, 300, 400, 500, 600, 1000.0, 10.0, 1000.0, 100.0, 1000.0, 150.0, 2000.0, 260.0, 30.0, 1.0);

-- Bandeiras
insert into bandeiras (ban_nome, ban_ativo)
values 	('Mastercard', '1'),
		('Visa', '1'),
        ('Elo', '1');

-- Categorias
insert into categorias (ctg_nome, ctg_descricao, ctg_ativo)
values 	('Impressoras 3D', '', '1'),
		('Filamentos ABS', '', '1'),
		('Filamentos PLA', '', '1'),
		('Filamentos PETG', '', '1'),
		('Resinas', '', '1'),
		('Partes', '', '1');

-- Precificações
insert into precificacoes (prc_desp_var, prc_desp_fix, prc_marg_luc)
values (15.0, 25.0, 10.0);

-- Produtos
insert into produtos (prd_nome, prd_descricao, prd_dim_prd_al, prd_dim_prd_la, prd_dim_prd_pr, prd_dim_prd_pe, prd_dim_emb_al, prd_dim_emb_la, prd_dim_emb_pr, prd_dim_emb_pe, prd_fabricante, prd_modelo, prd_quantidade, prd_preco, prd_ativo, prd_prc_id)
values
-- Impressoras
('Impressora 3D Creality Ender-3', 
'A Creality Ender-3 é uma impressora 3D com uma estrutura em alumínio anodizado, capaz de imprimir tanto por cartão SD quanto por cabo miniUSB',
700.0, 500.0, 500.0, 20.0, 800.0, 600.0, 600.0, 30.0,
'CREALITY',
'Ender-3',
0,
1899.9, '1', 1),
('Impressora 3D Anycubic I3 Mega S', 
'A Anycubic I3 Mega S é uma impressora 3D com uma estrutura em alumínio e muito fácil de utilizar, sendo capaz de imprimir tanto por cartão SD quanto por cabo USB-B. As camadas, entre 0,1 mm e 0,4 mm, fornecem uma ótima resolução para suas impressões.',
700.0, 500.0, 500.0, 20.0, 800.0, 600.0, 600.0, 30.0,
'ANYCUBIC',
'I3 Mega S',
0,
2449.90, '1', 1),
('Impressora 3D Creality LD-002R', 
'A Creality LD-002R é uma impressora 3D de Resina que possui uma tecnologia baseada em uma tela LCD Ultra HD 2K de alta resolução com luz UV para curar fotopolímeros camada por camada.',
700.0, 500.0, 500.0, 20.0, 800.0, 600.0, 600.0, 30.0,
'CREALITY',
'LD-002R',
0,
2699.90, '1', 1),
('Impressora 3D Anycubic Photon S', 
'A Impressora 3D Anycubic Photon S, uma impressora com tecnologia baseada em uma tela LCD Ultra HD 2K de alta resolução com luz UV-LED de comprimento de onda por volta dos 405mm, permitindo uma camada com uma resolução incrível.',
700.0, 500.0, 500.0, 20.0, 800.0, 600.0, 600.0, 30.0,
'ANYCUBIC',
'Photon S',
0,
3999.90, '1', 1),
('Impressora 3D Creality Ender-3 Pro', 
'A Ender-3 PRO conta com diversos upgrades em relação a sua antecessora. O que já fica bem claro é a nova mesa aquecida: com um sistema C-MAG, a mesa torna-se mais flexível, o que facilita e muito a extração das peças.',
700.0, 500.0, 500.0, 20.0, 800.0, 600.0, 600.0, 30.0,
'CREALITY',
'Ender-3 Pro',
0,
2149.90, '1', 1),
('Impressora 3D Anycubic Chiron', 
'A Anycubic Chiron é uma impressora 3D com uma estrutura em alumínio e muito fácil de utilizar, sendo capaz de imprimir tanto por cartão SD quanto por cabo USB-B.',
700.0, 500.0, 500.0, 20.0, 800.0, 600.0, 600.0, 30.0,
'ANYCUBIC',
'Chiron',
0,
4999.90, '1', 1),
('Impressora 3D Creality Ender-5',
'A Creality Ender-5 é a impressora 3D ideal para quem procura uma máquina com mais espaço de impressão no eixo Z e que imprime com alta precisão.',
700.0, 500.0, 500.0, 20.0, 800.0, 600.0, 600.0, 30.0,
'CREALITY',
'Ender-5',
0,
4099.90, '1', 1),
-- Filamentos ABS
('Filamento ABS 1.75mm 1kg Branco', 
'O Filamento ABS de alta qualidade para impressora 3D, com diâmetro de 1,75 mm e rolo de 1Kg, é compatível com a maioria das impressoras 3D do mercado.',
100.0, 250.0, 250.0, 1.0, 120.0, 300.0, 300.0, 1.5,
'CREALITY',
'ABS',
0,
99.90, '1', 1),
('Filamento ABS 1.75mm 1kg Azul', 
'O Filamento ABS de alta qualidade para impressora 3D, com diâmetro de 1,75 mm e rolo de 1Kg, é compatível com a maioria das impressoras 3D do mercado.',
100.0, 250.0, 250.0, 1.0, 120.0, 300.0, 300.0, 1.5,
'CREALITY',
'ABS',
0,
99.90, '1', 1),
('Filamento ABS 1.75mm 1kg Cinza', 
'O Filamento ABS de alta qualidade para impressora 3D, com diâmetro de 1,75 mm e rolo de 1Kg, é compatível com a maioria das impressoras 3D do mercado.',
100.0, 250.0, 250.0, 1.0, 120.0, 300.0, 300.0, 1.5,
'CREALITY',
'ABS',
0,
99.90, '1', 1),
('Filamento ABS 1.75mm 1kg Vermelho', 
'O Filamento ABS de alta qualidade para impressora 3D, com diâmetro de 1,75 mm e rolo de 1Kg, é compatível com a maioria das impressoras 3D do mercado.',
100.0, 250.0, 250.0, 1.0, 120.0, 300.0, 300.0, 1.5,
'CREALITY',
'ABS',
0,
99.90, '1', 1),
-- Filamentos PLA
('Filamento PLA 1.75mm 1Kg Preto', 
'Filamento PLA Cliever para impressora 3D, com diâmetro de 1,75 mm, rolo de 1Kg, compatível com a maioria das impressoras 3D do mercado.',
100.0, 250.0, 250.0, 1.0, 120.0, 300.0, 300.0, 1.5,
'Cliever',
'PLA',
0,
139.90, '1', 1),
('Filamento PLA 1.75mm 1Kg Cinza', 
'Filamento PLA Cliever para impressora 3D, com diâmetro de 1,75 mm, rolo de 1Kg, compatível com a maioria das impressoras 3D do mercado.',
100.0, 250.0, 250.0, 1.0, 120.0, 300.0, 300.0, 1.5,
'Cliever',
'PLA',
0,
139.90, '1', 1),
('Filamento PLA 1.75mm 1Kg Branco', 
'Filamento PLA Cliever para impressora 3D, com diâmetro de 1,75 mm, rolo de 1Kg, compatível com a maioria das impressoras 3D do mercado.',
100.0, 250.0, 250.0, 1.0, 120.0, 300.0, 300.0, 1.5,
'Cliever',
'PLA',
0,
139.90, '1', 1),
('Filamento PLA 1.75mm 1Kg Verde', 
'Filamento PLA Cliever para impressora 3D, com diâmetro de 1,75 mm, rolo de 1Kg, compatível com a maioria das impressoras 3D do mercado.',
100.0, 250.0, 250.0, 1.0, 120.0, 300.0, 300.0, 1.5,
'Cliever',
'PLA',
0,
139.90, '1', 1),
('Filamento PLA 1.75mm 1Kg Vermelho', 
'Filamento PLA Cliever para impressora 3D, com diâmetro de 1,75 mm, rolo de 1Kg, compatível com a maioria das impressoras 3D do mercado.',
100.0, 250.0, 250.0, 1.0, 120.0, 300.0, 300.0, 1.5,
'Cliever',
'PLA',
0,
139.90, '1', 1),
('Filamento PLA 1.75mm 200g Branco', 
'Filamento PLA Cliever para impressora 3D, com diâmetro de 1,75 mm, rolo de 200g, compatível com a maioria das impressoras 3D do mercado.',
100.0, 250.0, 250.0, 1.0, 120.0, 300.0, 300.0, 1.5,
'Cliever',
'PLA',
0,
49.90, '1', 1),
('Filamento PLA 1.75mm 1Kg Dourado', 
'Filamento PLA Cliever para impressora 3D, com diâmetro de 1,75 mm, rolo de 1Kg, compatível com a maioria das impressoras 3D do mercado.',
100.0, 250.0, 250.0, 1.0, 120.0, 300.0, 300.0, 1.5,
'Cliever',
'PLA',
0,
139.90, '1', 1),
('Filamento PLA 1.75mm 1Kg Azul', 
'Filamento PLA Cliever para impressora 3D, com diâmetro de 1,75 mm, rolo de 1Kg, compatível com a maioria das impressoras 3D do mercado.',
100.0, 250.0, 250.0, 1.0, 120.0, 300.0, 300.0, 1.5,
'Cliever',
'PLA',
0,
139.90, '1', 1),
('Filamento PLA 1.75mm 1Kg Marrom', 
'Filamento PLA Cliever para impressora 3D, com diâmetro de 1,75 mm, rolo de 1Kg, compatível com a maioria das impressoras 3D do mercado.',
100.0, 250.0, 250.0, 1.0, 120.0, 300.0, 300.0, 1.5,
'Cliever',
'PLA',
0,
139.90, '1', 1),
('Filamento PLA Wood Madeira 1.75mm 1Kg', 
'Filamento PLA Cliever para impressora 3D, com diâmetro de 1,75 mm, rolo de 1Kg, compatível com a maioria das impressoras 3D do mercado.',
100.0, 250.0, 250.0, 1.0, 120.0, 300.0, 300.0, 1.5,
'Cliever',
'PLA',
0,
139.90, '1', 1),
('Filamento PETG 1.75mm 1kg Vermelho', 
'O Filamento PETG é uma ótima opção se você precisa de um filamento com maior resistência mecânica e tolerância à altas temperaturas.',
100.0, 250.0, 250.0, 1.0, 120.0, 300.0, 300.0, 1.5,
'CREALITY',
'PETG',
0,
129.90, '1', 1),
('Filamento PETG 1.75mm 1kg Verde', 
'O Filamento PETG é uma ótima opção se você precisa de um filamento com maior resistência mecânica e tolerância à altas temperaturas.',
100.0, 250.0, 250.0, 1.0, 120.0, 300.0, 300.0, 1.5,
'CREALITY',
'PETG',
0,
129.90, '1', 1),
('Filamento PETG 1.75mm 1kg Preto', 
'O Filamento PETG é uma ótima opção se você precisa de um filamento com maior resistência mecânica e tolerância à altas temperaturas.',
100.0, 250.0, 250.0, 1.0, 120.0, 300.0, 300.0, 1.5,
'CREALITY',
'PETG',
0,
129.90, '1', 1),
('Filamento PETG 1.75mm 1kg Azul', 
'O Filamento PETG é uma ótima opção se você precisa de um filamento com maior resistência mecânica e tolerância à altas temperaturas.',
100.0, 250.0, 250.0, 1.0, 120.0, 300.0, 300.0, 1.5,
'CREALITY',
'PETG',
0,
129.90, '1', 1),
('Filamento PETG 1.75mm 1kg Cinza', 
'O Filamento PETG é uma ótima opção se você precisa de um filamento com maior resistência mecânica e tolerância à altas temperaturas.',
100.0, 250.0, 250.0, 1.0, 120.0, 300.0, 300.0, 1.5,
'CREALITY',
'PETG',
0,
129.90, '1', 1),
('Filamento PETG 1.75mm 1kg Branco', 
'O Filamento PETG é uma ótima opção se você precisa de um filamento com maior resistência mecânica e tolerância à altas temperaturas.',
100.0, 250.0, 250.0, 1.0, 120.0, 300.0, 300.0, 1.5,
'CREALITY',
'PETG',
0,
129.90, '1', 1),
-- Resina
('Resina para Impressora 3D 500g Verde', 
'A Resina para Impressora 3D UV é a matéria-prima utilizada em impressoras de resina.',
100.0, 50.0, 50.0, 0.5, 110.0, 60.0, 60.0, 0.5,
'CREALITY',
'Resina',
0,
189.90, '1', 1),
('Resina para Impressora 3D 250g Verde', 
'A Resina para Impressora 3D UV é a matéria-prima utilizada em impressoras de resina.',
100.0, 50.0, 50.0, 0.25, 110.0, 60.0, 60.0, 0.25,
'CREALITY',
'Resina',
0,
92.90, '1', 1),
('Resina para Impressora 3D 500g Branco', 
'A Resina para Impressora 3D UV é a matéria-prima utilizada em impressoras de resina.',
100.0, 50.0, 50.0, 0.5, 110.0, 60.0, 60.0, 0.5,
'CREALITY',
'Resina',
0,
189.90, '1', 1),
('Resina para Impressora 3D 500g Transparente', 
'A Resina para Impressora 3D UV é a matéria-prima utilizada em impressoras de resina.',
100.0, 50.0, 50.0, 0.5, 110.0, 60.0, 60.0, 0.5,
'CREALITY',
'Resina',
0,
129.90, '1', 1),
('Resina para Impressora 3D 500g Azul', 
'A Resina para Impressora 3D UV é a matéria-prima utilizada em impressoras de resina.',
100.0, 50.0, 50.0, 0.5, 110.0, 60.0, 60.0, 0.5,
'CREALITY',
'Resina',
0,
189.90, '1', 1),
('Resina para Impressora 3D 250g Preto', 
'A Resina para Impressora 3D UV é a matéria-prima utilizada em impressoras de resina.',
100.0, 50.0, 50.0, 0.25, 110.0, 60.0, 60.0, 0.25,
'CREALITY',
'Resina',
0,
92.90, '1', 1),
('Resina para Impressora 3D 500g Cinza', 
'A Resina para Impressora 3D UV é a matéria-prima utilizada em impressoras de resina.',
100.0, 50.0, 50.0, 0.5, 110.0, 60.0, 60.0, 0.5,
'CREALITY',
'Resina',
0,
189.90, '1', 1),
('Resina para Impressora 3D 250g Branca', 
'A Resina para Impressora 3D UV é a matéria-prima utilizada em impressoras de resina.',
100.0, 50.0, 50.0, 0.25, 110.0, 60.0, 60.0, 0.25,
'CREALITY',
'Resina',
0,
92.90, '1', 1),
('Resina para Impressora 3D 250g Transparente', 
'A Resina para Impressora 3D UV é a matéria-prima utilizada em impressoras de resina.',
100.0, 50.0, 50.0, 0.25, 110.0, 60.0, 60.0, 0.25,
'CREALITY',
'Resina',
0,
92.90, '1', 1),
('Resina para Impressora 3D 250g Azul', 
'A Resina para Impressora 3D UV é a matéria-prima utilizada em impressoras de resina.',
100.0, 50.0, 50.0, 0.25, 110.0, 60.0, 60.0, 0.25,
'CREALITY',
'Resina',
0,
92.90, '1', 1),
('Resina para Impressora 3D 500g Preto', 
'A Resina para Impressora 3D UV é a matéria-prima utilizada em impressoras de resina.',
100.0, 50.0, 50.0, 0.5, 110.0, 60.0, 60.0, 0.5,
'CREALITY',
'Resina',
0,
189.90, '1', 1),
('Resina para Impressora 3D 250g Bege', 
'A Resina para Impressora 3D UV é a matéria-prima utilizada em impressoras de resina.',
100.0, 50.0, 50.0, 0.25, 110.0, 60.0, 60.0, 0.25,
'CREALITY',
'Resina',
0,
92.90, '1', 1),
('Resina para Impressora 3D 250g Cinza', 
'A Resina para Impressora 3D UV é a matéria-prima utilizada em impressoras de resina.',
100.0, 50.0, 50.0, 0.25, 110.0, 60.0, 60.0, 0.25,
'CREALITY',
'Resina',
0,
92.90, '1', 1),
('Resina para Impressora 3D 500g Bege', 
'A Resina para Impressora 3D UV é a matéria-prima utilizada em impressoras de resina.',
100.0, 50.0, 50.0, 0.5, 110.0, 60.0, 60.0, 0.5,
'CREALITY',
'Resina',
0,
189.90, '1', 1),
-- Partes
('Chave Fim de Curso para Impressora 3D RAMPS RepRap', 
'Utilize a chave de fim de curso para impressora 3D para determinar o limite máximo que os motores podem atingir sem danificar ou forçar motores e peças da impressora 3D.',
100.0, 100.0, 100.0, 1.0, 110.0, 110.0, 110.0, 1.0,
'RepRap',
'Partes',
0,
12.90, '1', 1),
('Nozzle Bico Hotend 1,75 para Impressora 3d', 
'O Bico de Cobre Nozzle para Impressora 3D é um bico próprio para filamentos de 1,75 mm de impressoras 3D. O seu corpo é um fuso com o tamanho de parafuso M6 para você que precisa repor uma peça ou que está montando uma impressora 3D.',
100.0, 100.0, 100.0, 1.0, 110.0, 110.0, 110.0, 1.0,
'RepRap',
'Partes',
0,
8.90, '1', 1),
('Ventilador Radial Cooler 12V 50mm para Impressora 3D', 
'Utilize este ventilador radial para evitar superaquecimento de placas controladoras, dissipadores de calor, motores e outras partes da sua impressora 3D',
100.0, 100.0, 100.0, 1.0, 110.0, 110.0, 110.0, 1.0,
'RepRap',
'Partes',
0,
17.90, '1', 1),
('Chave Fim de Curso para Impressora 3D com Cabo', 
'Esta chave de fim de curso para impressora 3D é indicada para a montagem da sua impressora 3D ou como peça de reposição, podendo ser utilizada também em outros projetos e máquinas que necessitem de um dispositivo limitador de movimento.',
100.0, 100.0, 100.0, 1.0, 110.0, 110.0, 110.0, 1.0,
'RepRap',
'Partes',
0,
12.90, '1', 1),
('Agulha de Limpeza para Bico de Impressora 3D RepRap', 
'Utilize a agulha de limpeza para bico de impressora 3D na limpeza e conservação da sua impressora, eliminando entupimentos e deixando o equipamento pronto para trabalhar em poucos minutos.',
100.0, 100.0, 100.0, 1.0, 110.0, 110.0, 110.0, 1.0,
'RepRap',
'Partes',
0,
8.90, '1', 1),
('Display Gráfico LCD 128×64 para Impressora 3D RAMPS RepRap', 
'Display Gráfico LCD 128×64 para utilização em impressoras 3D RAMPS RepRap, com slot para cartão SD no verso, permitindo impressão offline.',
100.0, 100.0, 100.0, 1.0, 110.0, 110.0, 110.0, 1.0,
'RepRap',
'Partes',
0,
119.90, '1', 1),
('Cabo para Motor de Passo para Impressora 3D', 
'O Cabo para Motor de Passo para Impressora 3D é ideal para você conectar o motor à sua placa de controle, como a Impressora 3D Shield para Arduino Mega RAMPS 1.4 RepRap.',
100.0, 100.0, 100.0, 1.0, 110.0, 110.0, 110.0, 1.0,
'RepRap',
'Partes',
0,
26.90, '1', 1),
('Cabo para Impressora com Filtro 2 Metros', 
'Este Cabo para Impressora com Filtro 2 Metros foi desenvolvido para conectar notebooks e computadores a diversas marcas e modelos de impressoras, etc. Com garantia de oferecer transmissão de sinal sem interferências e oscilações.',
100.0, 100.0, 100.0, 1.0, 110.0, 110.0, 110.0, 1.0,
'RepRap',
'Partes',
0,
9.90, '1', 1),
('Hotend E3D V6 de Impressora 3D com Bico de 0.3 mm', 
'O Hotend E3D V6 é um sistema de aquecimento de filamentos muito popular. Devido a sua alta qualidade, um ótimo aquecimento, arrefecimento, e por ser leve, o sistema E3D acaba sendo uma opção muito interessante.',
100.0, 100.0, 100.0, 1.0, 110.0, 110.0, 110.0, 1.0,
'RepRap',
'Partes',
0,
139.90, '1', 1),
('Placa de Aquecimento para Impressora 3D', 
'Com a mesa de aquecimento para Impressora 3D, você pode trocar a sua peça com defeito por essa novinha aqui! Com uma alimentação de 12V ou 24V, a qual independe a polaridade',
20.0, 300.0, 300.0, 1.0, 30.0, 300.0, 300.0, 1.0,
'RepRap',
'Partes',
0,
99.90, '1', 1),
('Filme de Liberação FEP para Impressora 3D', 
'Este filme FEP de liberação para impressora 3D é um filme especial feito com resina FEP de alta pureza! Possui uma boa não-viscosidade, resistência ao calor, insolação elétrica, etc.',
20.0, 300.0, 300.0, 1.0, 30.0, 300.0, 300.0, 1.0,
'RepRap',
'Partes',
0,
59.90, '1', 1),
('Kit CNC e Impressora 3D com Arduino', 
'Agora você não tem mais desculpas para iniciar o seu projeto de impressora 3D ou CNC com Arduino! Com esse kit, você tem todo o material necessário para começar a programar e controlar até 4 motores de passo.',
100.0, 100.0, 100.0, 1.0, 110.0, 110.0, 110.0, 1.0,
'Arduino',
'Partes',
0,
99.90, '1', 1),
('Kit Impressora 3D RAMPS RepRap com Arduino', 
'Este é um kit avançado para montagem de impressora 3D, com tudo o que você precisa para começar a montar o seu projeto de impressora com placa Mega, obtendo ganhos em performance e capacidade de processamento.',
100.0, 100.0, 100.0, 1.0, 110.0, 110.0, 110.0, 1.0,
'Arduino',
'Partes',
0,
99.90, '1', 1);

-- Categorias - Produtos
insert into categorias_produtos (cpr_ctg_id, cpr_prd_id)
values 	(1, 1), -- Impressoras
		(1, 2),
        (1, 3),
        (1, 4),
        (1, 5),
        (1, 6),
        (1, 7),
        (2, 8), -- ABS
        (2, 9),
        (2, 10),
        (2, 11),
        (3, 12), -- PLA
        (3, 13),
        (3, 14),
        (3, 15),
        (3, 16),
        (3, 17),
        (3, 18),
        (3, 19),
        (3, 20),
        (3, 21),
        (4, 22), -- PETG
        (4, 23),
        (4, 24),
        (4, 25),
        (4, 26),
        (4, 27),
        (5, 28), -- Resina
        (5, 29),
        (5, 30),
        (5, 31),
        (5, 32),
        (5, 33),
        (5, 34),
        (5, 35),
        (5, 36), 
        (5, 37),
        (5, 38),
        (5, 39),
        (5, 40),
        (5, 41),
        (6, 42), -- Partes
        (6, 43),
        (6, 44),
        (6, 45),
        (6, 46),
        (6, 47),
        (6, 48),
        (6, 49),
        (6, 50),
        (6, 51),
        (6, 52),
        (6, 53),
        (6, 54);

-- Entradas
insert into entradas (ent_quantidade, ent_fornecedor, ent_custo, ent_data, ent_usu_id, ent_prd_id)
values 	(10, 'Creality', '9499.50', CURDATE(), 1, 1), -- Impressoras
		(10, 'Anycubic', '12249.50', CURDATE(), 1, 2),
        (10, 'Creality', '13499.50', CURDATE(), 1, 3),
        (10, 'Anycubic', '20999.50', CURDATE(), 1, 4),
        (10, 'Creality', '10749.50', CURDATE(), 1, 5),
        (10, 'Anycubic', '24999.50', CURDATE(), 1, 6),
        (10, 'Anycubic', '20499.50', CURDATE(), 1, 7),
        (10, 'Creality 3D', '499.50', CURDATE(), 1, 8), -- ABS
        (10, 'Creality 3D', '499.50', CURDATE(), 1, 9),
        (10, 'Creality 3D', '499.50', CURDATE(), 1, 10),
        (10, 'Creality 3D', '499.50', CURDATE(), 1, 11),
        (10, 'Creality 3D', '699.50', CURDATE(), 1, 12), -- PLA
        (10, 'Creality 3D', '699.50', CURDATE(), 1, 13),
        (10, 'Creality 3D', '699.50', CURDATE(), 1, 14),
        (10, 'Creality 3D', '699.50', CURDATE(), 1, 15),
        (10, 'Creality 3D', '699.50', CURDATE(), 1, 16),
        (10, 'Creality 3D', '249.50', CURDATE(), 1, 17),
        (10, 'Creality 3D', '699.50', CURDATE(), 1, 18),
        (10, 'Creality 3D', '699.50', CURDATE(), 1, 19),
        (10, 'Creality 3D', '699.50', CURDATE(), 1, 20),
        (10, 'Creality 3D', '699.50', CURDATE(), 1, 21),
        (10, 'Creality 3D', '699.50', CURDATE(), 1, 22), -- PETG
        (10, 'Creality 3D', '649.50', CURDATE(), 1, 23),
        (10, 'Creality 3D', '649.50', CURDATE(), 1, 24),
        (10, 'Creality 3D', '649.50', CURDATE(), 1, 25),
        (10, 'Creality 3D', '649.50', CURDATE(), 1, 26),
        (10, 'Creality 3D', '649.50', CURDATE(), 1, 27),
        (10, 'Creality 3D', '949.50', CURDATE(), 1, 28), -- Resina
        (10, 'Creality 3D', '464.50', CURDATE(), 1, 29),
        (10, 'Creality 3D', '949.50', CURDATE(), 1, 30),
        (10, 'Creality 3D', '949.50', CURDATE(), 1, 31),
        (10, 'Creality 3D', '949.50', CURDATE(), 1, 32),
        (10, 'Creality 3D', '464.50', CURDATE(), 1, 33),
        (10, 'Creality 3D', '949.50', CURDATE(), 1, 34),
        (10, 'Creality 3D', '464.50', CURDATE(), 1, 35),
        (10, 'Creality 3D', '464.50', CURDATE(), 1, 36),
        (10, 'Creality 3D', '464.50', CURDATE(), 1, 37),
        (10, 'Creality 3D', '949.50', CURDATE(), 1, 38),
        (10, 'Creality 3D', '464.50', CURDATE(), 1, 39),
        (10, 'Creality 3D', '464.50', CURDATE(), 1, 40),
        (10, 'Creality 3D', '949.50', CURDATE(), 1, 41),
        (10, 'Creality 3D', '64.50', CURDATE(), 1, 42), -- Partes
        (10, 'Creality 3D', '44.50', CURDATE(), 1, 43),
        (10, 'Creality 3D', '89.50', CURDATE(), 1, 44),
        (10, 'Creality 3D', '64.50', CURDATE(), 1, 45),
        (10, 'Creality 3D', '44.50', CURDATE(), 1, 46),
        (10, 'Creality 3D', '599.50', CURDATE(), 1, 47),
        (10, 'Creality 3D', '134.50', CURDATE(), 1, 48),
        (10, 'Creality 3D', '49.50', CURDATE(), 1, 49),
        (10, 'Creality 3D', '699.50', CURDATE(), 1, 50),
        (10, 'Creality 3D', '499.50', CURDATE(), 1, 51),
        (10, 'Creality 3D', '299.50', CURDATE(), 1, 52),
        (10, 'Creality 3D', '499.50', CURDATE(), 1, 53),
        (10, 'Creality 3D', '499.50', CURDATE(), 1, 54);

-- Clientes senha cliente cvv 123
-- Usuario - Cliente 1
insert into usuarios (usu_email, usu_senha, usu_regra, usu_ativo)
values ('cliente1@gprint3d.com', '$2a$10$G6feYQ9OLuVyvic3WUJ/3ebR1jAJOuj2Qaob9elCNIGjMcoi5x8Jq', 'ROLE_CLI', '1');

-- Cliente
insert into clientes (cli_ranking, cli_pontos, cli_nome, cli_sexo, cli_dtnasc, cli_usu_id)
values (0, 0, 'Sérgio Levi', 'Masculino', '1945-09-23', '2');

-- Documento
insert into documentos (doc_tipo, doc_numero, doc_cli_id)
values ('CPF', '912.941.685-09', '1');

-- Endereço
insert into enderecos (end_entrega, end_cobranca, end_descricao, end_tipolog, end_logradouro, end_numero, end_cep, end_complemento, end_cidade, end_estado, end_pais, end_observacao, end_cli_id)
values ('1', '0', 'Casa', 'Rua', 'General Osório', '683', '79904-596', null, 'Ponta Porã', 'MS', 'Brasil', null, '1');
insert into enderecos (end_entrega, end_cobranca, end_descricao, end_tipolog, end_logradouro, end_numero, end_cep, end_complemento, end_cidade, end_estado, end_pais, end_observacao, end_cli_id)
values ('0', '1', 'Mãe', 'Rua', 'General Osório', '683', '79904-596', null, 'Ponta Porãs', 'MS', 'Brasil', null, '1');

insert into enderecos_entregas_padroes (eep_end_id, eep_cli_id)
values ('1', '1');
insert into enderecos_cobrancas_padroes (ecp_end_id, ecp_cli_id)
values ('2', '1');

-- Telefones
insert into telefones (tel_tipo, tel_ddd, tel_numero, tel_cli_id)
values ('Comercial', '(67)', '3697-5899', '1');

-- Cartao
insert into cartoes(crt_nome, crt_numero, crt_validade, crt_cvv, crt_ban_id, crt_cli_id)
values ('Sérgio Levi Yuri Melo', '1234 5688 8885 5221', '07/20', '$2a$10$AAA/43qvp1TX0GaMGwGAFufPqMtMIqf./DQQYUR3Cq0UStqWFwhUy', '1', '1');

-- Cartao Padrao
insert into cartoes_padroes (ctp_crt_id, ctp_cli_id)
values ('1', '1');

-- Pedidos
insert into pedidos_compras(pdc_numero, pdc_datainicio, pdc_datafim, pdc_statuspedido, pdc_statuslogistica, pdc_cli_id, pdc_end_id)
values ('10101010', CURDATE(), null, 0, 0, 1, 1);

insert into pedidos_compras(pdc_numero, pdc_datainicio, pdc_datafim, pdc_statuspedido, pdc_statuslogistica, pdc_cli_id, pdc_end_id)
values ('10101011', CURDATE(), null, 0, 0, 1, 1);

insert into pedidos_compras_produtos(ppd_quantidade, ppd_pdc_id, ppd_prd_id)
values (1, 1, 1),
	   (2, 1, 2),
	   (3, 1, 3),
	   (4, 1, 4),
	   (5, 1, 5),
	   (6, 1, 6);

insert into pedidos_compras_produtos(ppd_quantidade, ppd_pdc_id, ppd_prd_id)
values (1, 2, 10),
	   (2, 2, 11),
	   (3, 2, 12),
	   (4, 2, 13);
       
insert into pedidos_compras_cartoes(pct_valor, pct_crt_id, pct_pdc_id)
values (72447.90, 1, 1),
	   (1279.00, 1, 2);
       
insert into pedidos_compras_fretes(pcf_empresa, pcf_modalidade, pcf_prazo, pcf_valor, pcf_pdc_id)
values ('Correios', 'SEDEX', 2, 1000.00, 1),
	   ('Correios', 'SEDEX', 3, 500.00, 2);


-- Usuario - Cliente 2
insert into usuarios (usu_email, usu_senha, usu_regra, usu_ativo)
values ('cliente2@gprint3d.com', '$2a$10$G6feYQ9OLuVyvic3WUJ/3ebR1jAJOuj2Qaob9elCNIGjMcoi5x8Jq', 'ROLE_CLI', '1');

-- Cliente
insert into clientes (cli_ranking, cli_pontos, cli_nome, cli_sexo, cli_dtnasc, cli_usu_id)
values (0, 0, 'Cauã Luiz Lima', 'Masculino', '1954-09-21', '3');

-- Documento
insert into documentos (doc_tipo, doc_numero, doc_cli_id)
values ('CPF', '037.843.563-97', '2');

-- Endereço
insert into enderecos (end_entrega, end_cobranca, end_descricao, end_tipolog, end_logradouro, end_numero, end_cep, end_complemento, end_cidade, end_estado, end_pais, end_observacao, end_cli_id)
values ('1', '0', 'Casa', 'Rua', 'Manoel Miguel Alves Filho', '905', '81940-500', null, 'Curitiba', 'PR', 'Brasil', null, '2');
insert into enderecos (end_entrega, end_cobranca, end_descricao, end_tipolog, end_logradouro, end_numero, end_cep, end_complemento, end_cidade, end_estado, end_pais, end_observacao, end_cli_id)
values ('0', '1', 'Mãe', 'Rua', 'Manoel Miguel Alves Filho', '905', '81940-500', null, 'Curitiba', 'PR', 'Brasil', null, '2');

insert into enderecos_entregas_padroes (eep_end_id, eep_cli_id)
values ('3', '2');
insert into enderecos_cobrancas_padroes (ecp_end_id, ecp_cli_id)
values ('4', '2');

-- Telefones
insert into telefones (tel_tipo, tel_ddd, tel_numero, tel_cli_id)
values ('Celular', '(41)', '98721-1324', '2');

-- Cartao
insert into cartoes (crt_nome, crt_numero, crt_validade, crt_cvv, crt_ban_id, crt_cli_id)
values ('Cauã Luiz Lima', '1234 5688 8885 5221', '07/20', '$2a$10$AAA/43qvp1TX0GaMGwGAFufPqMtMIqf./DQQYUR3Cq0UStqWFwhUy', '1', '2');

-- Cartao Padrao
insert into cartoes_padroes (ctp_crt_id, ctp_cli_id)
values ('2', '2');


-- Usuario - Cliente 3
insert into usuarios (usu_email, usu_senha, usu_regra, usu_ativo)
values ('cliente3@gprint3d.com', '$2a$10$G6feYQ9OLuVyvic3WUJ/3ebR1jAJOuj2Qaob9elCNIGjMcoi5x8Jq', 'ROLE_CLI', '1');

-- Cliente
insert into clientes (cli_ranking, cli_pontos, cli_nome, cli_sexo, cli_dtnasc, cli_usu_id)
values (0, 0, 'Renato Nicolas Lorenzo Araújo', 'Masculino', '1945-03-20', '4');

-- Documento
insert into documentos (doc_tipo, doc_numero, doc_cli_id)
values ('CPF', '353.085.217-19', '3');

-- Endereço
insert into enderecos (end_entrega, end_cobranca, end_descricao, end_tipolog, end_logradouro, end_numero, end_cep, end_complemento, end_cidade, end_estado, end_pais, end_observacao, end_cli_id)
values ('1', '0', 'Casa', 'Rua', 'Treze', '906', '78053-733', null, 'Cuiabá', 'MG', 'Brasil', null, '3');
insert into enderecos (end_entrega, end_cobranca, end_descricao, end_tipolog, end_logradouro, end_numero, end_cep, end_complemento, end_cidade, end_estado, end_pais, end_observacao, end_cli_id)
values ('0', '1', 'Mãe', 'Rua', 'Treze', '906', '78053-733', null, 'Cuiabá', 'MG', 'Brasil', null, '3');

insert into enderecos_entregas_padroes (eep_end_id, eep_cli_id)
values ('5', '3');
insert into enderecos_cobrancas_padroes (ecp_end_id, ecp_cli_id)
values ('6', '3');

-- Telefones
insert into telefones (tel_tipo, tel_ddd, tel_numero, tel_cli_id)
values ('Comercial', '(65)', '36706873', '3');

-- Cartao
insert into cartoes (crt_nome, crt_numero, crt_validade, crt_cvv, crt_ban_id, crt_cli_id)
values ('Renato Nicolas Lorenzo Araújo', '1234 5688 8885 5221', '07/20', '$2a$10$AAA/43qvp1TX0GaMGwGAFufPqMtMIqf./DQQYUR3Cq0UStqWFwhUy', '1', '3');

-- Cartao Padrao
insert into cartoes_padroes (ctp_crt_id, ctp_cli_id)
values ('3', '3');



-- Usuario - Cliente 4
insert into usuarios (usu_email, usu_senha, usu_regra, usu_ativo)
values ('cliente4@gprint3d.com', '$2a$10$G6feYQ9OLuVyvic3WUJ/3ebR1jAJOuj2Qaob9elCNIGjMcoi5x8Jq', 'ROLE_CLI', '1');

-- Cliente
insert into clientes (cli_ranking, cli_pontos, cli_nome, cli_sexo, cli_dtnasc, cli_usu_id)
values (0, 0, 'Theo Heitor Henrique da Paz', 'Masculino', '1975-06-14', '5');

-- Documento
insert into documentos (doc_tipo, doc_numero, doc_cli_id)
values ('CPF', '587.484.127-08', '4');

-- Endereço
insert into enderecos (end_entrega, end_cobranca, end_descricao, end_tipolog, end_logradouro, end_numero, end_cep, end_complemento, end_cidade, end_estado, end_pais, end_observacao, end_cli_id)
values ('1', '0', 'Casa', 'Rua', 'Guaçuí', '810', '29177-280', null, 'Serra', 'ES', 'Brasil', null, '4');
insert into enderecos (end_entrega, end_cobranca, end_descricao, end_tipolog, end_logradouro, end_numero, end_cep, end_complemento, end_cidade, end_estado, end_pais, end_observacao, end_cli_id)
values ('0', '1', 'Mãe', 'Rua', 'Guaçuí', '810', '29177-280', null, 'Serra', 'ES', 'Brasil', null, '4');

insert into enderecos_entregas_padroes (eep_end_id, eep_cli_id)
values ('7', '4');
insert into enderecos_cobrancas_padroes (ecp_end_id, ecp_cli_id)
values ('8', '4');

-- Telefones
insert into telefones (tel_tipo, tel_ddd, tel_numero, tel_cli_id)
values ('Celular', '(27)', '99594-3161', '4');

-- Cartao
insert into cartoes (crt_nome, crt_numero, crt_validade, crt_cvv, crt_ban_id, crt_cli_id)
values ('Theo Heitor Henrique da Paz', '1234 5688 8885 5221', '07/20', '$2a$10$AAA/43qvp1TX0GaMGwGAFufPqMtMIqf./DQQYUR3Cq0UStqWFwhUy', '1', '4');

-- Cartao Padrao
insert into cartoes_padroes (ctp_crt_id, ctp_cli_id)
values ('4', '4');



-- Usuario - Cliente 5
insert into usuarios (usu_email, usu_senha, usu_regra, usu_ativo)
values ('cliente5@gprint3d.com', '$2a$10$G6feYQ9OLuVyvic3WUJ/3ebR1jAJOuj2Qaob9elCNIGjMcoi5x8Jq', 'ROLE_CLI', '1');

-- Cliente
insert into clientes (cli_ranking, cli_pontos, cli_nome, cli_sexo, cli_dtnasc, cli_usu_id)
values (0, 0, 'Calebe Mateus Daniel Barbosa', 'Feminino', '1955-03-10', '6');

-- Documento
insert into documentos (doc_tipo, doc_numero, doc_cli_id)
values ('CPF', '471.966.383-44', '5');

-- Endereço
insert into enderecos (end_entrega, end_cobranca, end_descricao, end_tipolog, end_logradouro, end_numero, end_cep, end_complemento, end_cidade, end_estado, end_pais, end_observacao, end_cli_id)
values ('1', '0', 'Casa', 'Rua', 'Antônio Lucas de Carvalho', '884', '58419373', null, 'Campina Grande', 'PB', 'Brasil', null, '5');
insert into enderecos (end_entrega, end_cobranca, end_descricao, end_tipolog, end_logradouro, end_numero, end_cep, end_complemento, end_cidade, end_estado, end_pais, end_observacao, end_cli_id)
values ('0', '1', 'Mãe', 'Rua', 'Antônio Lucas de Carvalho', '884', '58419373', null, 'Campina Grande', 'PB', 'Brasil', null, '5');

insert into enderecos_entregas_padroes (eep_end_id, eep_cli_id)
values ('9', '5');
insert into enderecos_cobrancas_padroes (ecp_end_id, ecp_cli_id)
values ('10', '5');

-- Telefones
insert into telefones (tel_tipo, tel_ddd, tel_numero, tel_cli_id)
values ('Celular', '(83)', '98330-0010', '5');

-- Cartao
insert into cartoes (crt_nome, crt_numero, crt_validade, crt_cvv, crt_ban_id, crt_cli_id)
values ('Calebe Mateus Daniel Barbosa', '1234 5688 8885 5221', '07/20', '$2a$10$AAA/43qvp1TX0GaMGwGAFufPqMtMIqf./DQQYUR3Cq0UStqWFwhUy', '1', '5');

-- Cartao Padrao
insert into cartoes_padroes (ctp_crt_id, ctp_cli_id)
values ('5', '5');