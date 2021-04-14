-- DATABASE
DROP DATABASE IF EXISTS crud_gprint3d;
CREATE DATABASE crud_gprint3d;
USE crud_gprint3d;

-- TABLES
DROP TABLE IF EXISTS carrinhos;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE carrinhos (
    car_id          	MEDIUMINT NOT NULL AUTO_INCREMENT,
    car_cli_id  		MEDIUMINT,
    car_cli_temp		VARCHAR(255),
    CONSTRAINT pk_car PRIMARY KEY ( car_id ),
    CONSTRAINT uk_car UNIQUE ( car_cli_temp )
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS bandeiras;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE bandeiras (
    ban_id          	MEDIUMINT NOT NULL AUTO_INCREMENT,
    ban_nome	  		VARCHAR(100) NOT NULL,
    ban_ativo			BOOLEAN,
    CONSTRAINT pk_ban PRIMARY KEY ( ban_id )
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS cartoes;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE cartoes (
    crt_id           	MEDIUMINT NOT NULL AUTO_INCREMENT,
    crt_nome         	VARCHAR(50) NOT NULL,
    crt_numero       	VARCHAR(50) NOT NULL,
    crt_validade     	VARCHAR(10) NOT NULL,
    crt_cvv          	VARCHAR(255) NOT NULL,
    crt_ban_id			MEDIUMINT NOT NULL,
    crt_cli_id  		MEDIUMINT NOT NULL,
    CONSTRAINT pk_crt PRIMARY KEY ( crt_id ),
    CONSTRAINT uk_crt UNIQUE ( crt_numero, crt_cli_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS cartoes_padroes;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE cartoes_padroes (
    ctp_id           	MEDIUMINT NOT NULL AUTO_INCREMENT,
    ctp_crt_id		    MEDIUMINT NOT NULL,
    ctp_cli_id          MEDIUMINT NOT NULL,
    CONSTRAINT pk_ctp PRIMARY KEY ( ctp_id ),
    CONSTRAINT uk_ctp UNIQUE ( ctp_crt_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS categorias;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE categorias (
    ctg_id         		MEDIUMINT NOT NULL AUTO_INCREMENT,
    ctg_nome       		VARCHAR(100),
    ctg_descricao  		VARCHAR(255),
    ctg_ativo			BOOLEAN,
    CONSTRAINT pk_ctg PRIMARY KEY ( ctg_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS categorias_produtos;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE categorias_produtos (
    cpr_id         		MEDIUMINT NOT NULL AUTO_INCREMENT,
    cpr_ctg_id    		MEDIUMINT NOT NULL,
    cpr_prd_id  		MEDIUMINT NOT NULL,
    CONSTRAINT pk_cpr PRIMARY KEY ( cpr_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS clientes;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE clientes (
    cli_id           	MEDIUMINT NOT NULL AUTO_INCREMENT,
    cli_ranking			MEDIUMINT DEFAULT 0,
    cli_pontos			MEDIUMINT DEFAULT 0,
    cli_nome	       	VARCHAR(255) NOT NULL,
    cli_sexo			VARCHAR(20) NOT NULL,
    cli_dtnasc			DATE NOT NULL,
    cli_usu_id  		MEDIUMINT NOT NULL,
    CONSTRAINT pk_cli PRIMARY KEY ( cli_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS cupons_promocoes;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE cupons_promocoes (
    cpp_id             	MEDIUMINT NOT NULL AUTO_INCREMENT,
    cpp_nome           	VARCHAR(100) NOT NULL,
    cpp_codigo         	VARCHAR(255) NOT NULL,
    cpp_desconto       	MEDIUMINT NOT NULL,
    cpp_validade       	DATE NOT NULL,
    cpp_ctg_id  		MEDIUMINT NOT NULL,
    CONSTRAINT pk_cpp PRIMARY KEY ( cpp_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS cupons_trocas;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE cupons_trocas (
    cpt_id             	MEDIUMINT NOT NULL AUTO_INCREMENT,
    cpt_status          BOOLEAN,
    cpt_validade        DATE NOT NULL,
    cpt_valor       	DECIMAL(8,2) NOT NULL,
    cpt_saldo       	DECIMAL(8,2) NOT NULL,
    cpt_codigo			VARCHAR(20) NOT NULL,
    cpt_cli_id  		MEDIUMINT NOT NULL,
    CONSTRAINT pk_cpt PRIMARY KEY ( cpt_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS historicos_cupons_trocas;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE historicos_cupons_trocas (
    hct_id             	MEDIUMINT NOT NULL AUTO_INCREMENT,
    hct_data	        VARCHAR(100) NOT NULL,
    hct_saldo       	DECIMAL(8,2) NOT NULL,
    hct_cpt_id  		MEDIUMINT NOT NULL,
    CONSTRAINT pk_hct PRIMARY KEY ( hct_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS documentos;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE documentos (
    doc_id           	MEDIUMINT NOT NULL AUTO_INCREMENT,
    doc_tipo         	VARCHAR(20) NOT NULL,
    doc_numero       	VARCHAR(50) NOT NULL,
    doc_cli_id  		MEDIUMINT NOT NULL,
    CONSTRAINT pk_doc PRIMARY KEY ( doc_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS enderecos;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE enderecos (
    end_id           	MEDIUMINT NOT NULL AUTO_INCREMENT,
    end_entrega			BOOLEAN,
    end_cobranca		BOOLEAN,
    end_descricao		VARCHAR(255) NOT NULL,
    end_tipolog        	VARCHAR(20) NOT NULL,
    end_logradouro   	VARCHAR(255) NOT NULL,
    end_numero       	VARCHAR(10) NOT NULL,
    end_cep          	VARCHAR(20) NOT NULL,
    end_complemento  	VARCHAR(255),
    end_cidade       	VARCHAR(100) NOT NULL,
    end_estado       	VARCHAR(100) NOT NULL,
    end_pais       		VARCHAR(100) NOT NULL,
    end_observacao		VARCHAR(255),
    end_cli_id  		MEDIUMINT NOT NULL,
    CONSTRAINT pk_end PRIMARY KEY ( end_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS enderecos_cobrancas_padroes;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE enderecos_cobrancas_padroes (
    ecp_id           	MEDIUMINT NOT NULL AUTO_INCREMENT,
    ecp_end_id		    MEDIUMINT NOT NULL,
    ecp_cli_id		    MEDIUMINT NOT NULL,
    CONSTRAINT pk_ecp PRIMARY KEY ( ecp_id ),
    CONSTRAINT uk_ecp UNIQUE ( ecp_end_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS enderecos_entregas_padroes;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE enderecos_entregas_padroes (
    eep_id           	MEDIUMINT NOT NULL AUTO_INCREMENT,
    eep_end_id		    MEDIUMINT NOT NULL,
    eep_cli_id		    MEDIUMINT NOT NULL,
    CONSTRAINT pk_eep PRIMARY KEY ( eep_id ),
    CONSTRAINT uk_eep UNIQUE ( eep_end_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS entradas;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE entradas (
    ent_id           	MEDIUMINT NOT NULL AUTO_INCREMENT,
    ent_quantidade		MEDIUMINT NOT NULL,
    ent_fornecedor		VARCHAR(255) NOT NULL,
    ent_custo			DECIMAL(8,2) NOT NULL,
    ent_data			DATE NOT NULL,
    ent_usu_id			MEDIUMINT NOT NULL,
    ent_prd_id		  	MEDIUMINT NOT NULL,
    CONSTRAINT pk_ent PRIMARY KEY ( ent_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS fotos;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE fotos (
	fto_id				MEDIUMINT NOT NULL AUTO_INCREMENT,
    fto_nome			VARCHAR(100) NOT NULL,
    fto_data			DATE,
    fto_content			MEDIUMBLOB,
    fto_prd_id  		MEDIUMINT NOT NULL,
    CONSTRAINT pk_fto PRIMARY KEY ( fto_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS pedidos_compras;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE pedidos_compras (
    pdc_id            	MEDIUMINT NOT NULL AUTO_INCREMENT,
    pdc_numero			VARCHAR(100) NOT NULL,
    pdc_data			DATE NOT NULL,
    pdc_statuspedido	MEDIUMINT NOT NULL DEFAULT 0,
    pdc_statuslogistica	MEDIUMINT NOT NULL DEFAULT 0,
    pdc_cli_id   		MEDIUMINT NOT NULL,
    pdc_end_id  		MEDIUMINT NOT NULL,
    CONSTRAINT pk_pdc PRIMARY KEY ( pdc_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS pedidos_trocas;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE pedidos_trocas (
    pdt_id            	MEDIUMINT NOT NULL AUTO_INCREMENT,
    pdt_numero			VARCHAR(100) NOT NULL,
    pdt_quantidade		MEDIUMINT NOT NULL,
    pdt_data			DATE NOT NULL,
    pdt_escolha			SMALLINT NOT NULL DEFAULT 0,
    pdt_statuspedido	MEDIUMINT NOT NULL DEFAULT 0,
    pdt_statuslogistica	MEDIUMINT NOT NULL DEFAULT 0,
    pdt_descricao		VARCHAR(255),
    pdt_ppd_id  		MEDIUMINT NOT NULL,
    pdt_pdc_id			MEDIUMINT NOT NULL,
    pdt_cli_id			MEDIUMINT NOT NULL,
    CONSTRAINT pk_pdt PRIMARY KEY ( pdt_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS pedidos_compras_cartoes;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE pedidos_compras_cartoes (
	pct_id				MEDIUMINT NOT NULL AUTO_INCREMENT,
    pct_valor			DECIMAL(8,2) NOT NULL,
    pct_crt_id  		MEDIUMINT NOT NULL,
    pct_pdc_id  		MEDIUMINT NOT NULL,
    CONSTRAINT pk_pct PRIMARY KEY ( pct_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS pedidos_compras_cupons_promocoes;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE pedidos_compras_cupons_promocoes (
	pcp_id				MEDIUMINT NOT NULL AUTO_INCREMENT,
    pcp_cpp_id   		MEDIUMINT NOT NULL,
    pcp_pdc_id  		MEDIUMINT NOT NULL,
    CONSTRAINT pk_pcp PRIMARY KEY ( pcp_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS pedidos_compras_produtos;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE pedidos_compras_produtos (
	ppd_id				MEDIUMINT NOT NULL AUTO_INCREMENT,
    ppd_quantidade		MEDIUMINT NOT NULL,
    ppd_status			MEDIUMINT NOT NULL DEFAULT 0,
    ppd_pdc_id   		MEDIUMINT NOT NULL,
    ppd_prd_id  		MEDIUMINT NOT NULL,
    CONSTRAINT pk_ppd PRIMARY KEY ( ppd_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS produtos;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE produtos (
    prd_id             	MEDIUMINT NOT NULL AUTO_INCREMENT,
    prd_nome           	VARCHAR(255) NOT NULL,
    prd_descricao      	VARCHAR(255),
    prd_dim_prd_al  	DECIMAL(6,2), 
    prd_dim_prd_la		DECIMAL(6,2),
    prd_dim_prd_pr		DECIMAL(6,2),
    prd_dim_prd_pe		DECIMAL(6,2),
    prd_dim_emb_al		DECIMAL(6,2),
    prd_dim_emb_la		DECIMAL(6,2),
    prd_dim_emb_pr		DECIMAL(6,2),
    prd_dim_emb_pe		DECIMAL(6,2),
    prd_fabricante		VARCHAR(100),
    prd_modelo			VARCHAR(100),
    prd_quantidade     	MEDIUMINT DEFAULT 0,
    prd_reservado		MEDIUMINT DEFAULT 0,
    prd_preco          	DECIMAL(8,2) DEFAULT 0.0,
    prd_ativo			BOOLEAN,
    prd_prc_id			MEDIUMINT NOT NULL,
    CONSTRAINT pk_prd PRIMARY KEY ( prd_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS produtos_carrinhos;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE produtos_carrinhos (
	pcr_id             	MEDIUMINT NOT NULL AUTO_INCREMENT,
    pcr_prd_id  		MEDIUMINT NOT NULL,
    pcr_car_id  		MEDIUMINT NOT NULL,
    pcr_quantidade      MEDIUMINT NOT NULL,
    pcr_date			DATE NOT NULL,
    pcr_ativo			BOOLEAN NOT NULL,
    CONSTRAINT pk_pcr PRIMARY KEY ( pcr_id )
    /*CONSTRAINT uk_pcr UNIQUE KEY ( pcr_car_id, pcr_prd_id )*/
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS produtos_justificativas;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE produtos_justificativas (
	pju_id             	MEDIUMINT NOT NULL AUTO_INCREMENT,
    pju_produto			VARCHAR(255) NOT NULL,
    pju_categorias		VARCHAR(255) NOT NULL,
    pju_acao  			VARCHAR(255) NOT NULL,
    pju_justificativa	VARCHAR(255) NOT NULL,
    pju_data			DATE,
    CONSTRAINT pk_pju PRIMARY KEY ( pju_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS precificacoes;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE precificacoes (
	prc_id             	MEDIUMINT NOT NULL AUTO_INCREMENT,
    prc_desp_var		DECIMAL(6,2) NOT NULL,
    prc_desp_fix		DECIMAL(6,2) NOT NULL,
    prc_marg_luc		DECIMAL(6,2) NOT NULL,
    CONSTRAINT pk_prc PRIMARY KEY ( prc_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS saidas;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE saidas (
    sai_id           	MEDIUMINT NOT NULL AUTO_INCREMENT,
    sai_descricao		VARCHAR(255) NOT NULL,
    sai_quantidade		MEDIUMINT NOT NULL,
    sai_data			DATE NOT NULL,
    sai_usu_id			MEDIUMINT NOT NULL,
    sai_prd_id		  	MEDIUMINT NOT NULL,
    CONSTRAINT pk_sai PRIMARY KEY ( sai_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS telefones;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE telefones (
    tel_id           	MEDIUMINT NOT NULL AUTO_INCREMENT,
    tel_tipo         	VARCHAR(20) NOT NULL,
    tel_ddd         	VARCHAR(10) NOT NULL,
    tel_numero			VARCHAR(20) NOT NULL,
    tel_cli_id			MEDIUMINT NOT NULL,
    CONSTRAINT pk_tel PRIMARY KEY ( tel_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS usuarios;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE usuarios (
    usu_id     			MEDIUMINT NOT NULL AUTO_INCREMENT,
    usu_email  			VARCHAR(100) NOT NULL,
    usu_senha  			VARCHAR(255) NOT NULL,
    usu_regra 			VARCHAR(20) NOT NULL,
    usu_ativo  			BOOLEAN NOT NULL,
    CONSTRAINT pk_usu PRIMARY KEY ( usu_id ),
    CONSTRAINT uk_usu UNIQUE ( usu_email )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS log_transacoes;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE log_transacoes (
    log_id     			MEDIUMINT NOT NULL AUTO_INCREMENT,
    log_data  			VARCHAR(100) NOT NULL,
    log_acao			VARCHAR(50) NOT NULL,
    log_descricao  		VARCHAR(255) NOT NULL,
    log_tabela			VARCHAR(50) NOT NULL,
    log_dado	 		MEDIUMINT NOT NULL,
    CONSTRAINT pk_log PRIMARY KEY ( log_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS sis_variaveis;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE sis_variaveis (
    var_id     			MEDIUMINT NOT NULL AUTO_INCREMENT,
    var_cep  			VARCHAR(20) NOT NULL,
    var_categoria		MEDIUMINT NOT NULL DEFAULT 2,
    var_temptroca		MEDIUMINT NOT NULL,
    var_rank_1			MEDIUMINT NOT NULL,
    var_rank_2			MEDIUMINT NOT NULL,
    var_rank_3			MEDIUMINT NOT NULL,
    var_rank_4			MEDIUMINT NOT NULL,
    CONSTRAINT pk_var PRIMARY KEY ( var_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- FOREIGN KEYS
ALTER TABLE carrinhos
    ADD CONSTRAINT fk_car_cli FOREIGN KEY ( car_cli_id )
        REFERENCES clientes ( cli_id );

ALTER TABLE cartoes
    ADD CONSTRAINT fk_crt_cli FOREIGN KEY ( crt_cli_id )
        REFERENCES clientes ( cli_id );
        
ALTER TABLE cartoes
    ADD CONSTRAINT fk_crt_ban FOREIGN KEY ( crt_ban_id )
        REFERENCES bandeiras ( ban_id );

ALTER TABLE cartoes_padroes
    ADD CONSTRAINT fk_ctp_crt FOREIGN KEY ( ctp_crt_id )
        REFERENCES cartoes ( crt_id );

ALTER TABLE cartoes_padroes
    ADD CONSTRAINT fk_ctp_cli FOREIGN KEY ( ctp_cli_id )
        REFERENCES clientes ( cli_id );
        
ALTER TABLE categorias_produtos
	ADD CONSTRAINT fk_cpr_ctg FOREIGN KEY ( cpr_ctg_id )
		REFERENCES categorias ( ctg_id );
        
ALTER TABLE categorias_produtos
	ADD CONSTRAINT fk_cpr_prd FOREIGN KEY ( cpr_prd_id )
		REFERENCES produtos ( prd_id );

ALTER TABLE cupons_promocoes
    ADD CONSTRAINT fk_cpp_ctg FOREIGN KEY ( cpp_ctg_id )
        REFERENCES categorias ( ctg_id );
        
ALTER TABLE cupons_trocas
    ADD CONSTRAINT fk_cpt_cli FOREIGN KEY ( cpt_cli_id )
        REFERENCES clientes ( cli_id );
        
ALTER TABLE historicos_cupons_trocas
    ADD CONSTRAINT fk_hct_cpt FOREIGN KEY ( hct_cpt_id )
        REFERENCES cupons_trocas ( cpt_id );

ALTER TABLE enderecos
    ADD CONSTRAINT fk_end_cli FOREIGN KEY ( end_cli_id )
        REFERENCES clientes ( cli_id );

ALTER TABLE enderecos_cobrancas_padroes
    ADD CONSTRAINT fk_ecp_end FOREIGN KEY ( ecp_end_id )
        REFERENCES enderecos ( end_id );

ALTER TABLE enderecos_cobrancas_padroes
    ADD CONSTRAINT fk_ecp_cli FOREIGN KEY ( ecp_cli_id )
        REFERENCES clientes ( cli_id );

ALTER TABLE enderecos_entregas_padroes
    ADD CONSTRAINT fk_eep_end FOREIGN KEY ( eep_end_id )
        REFERENCES enderecos ( end_id );

ALTER TABLE enderecos_entregas_padroes
    ADD CONSTRAINT fk_eep_cli FOREIGN KEY ( eep_cli_id )
        REFERENCES clientes ( cli_id );

ALTER TABLE entradas
    ADD CONSTRAINT fk_ent_prd FOREIGN KEY ( ent_prd_id )
        REFERENCES produtos ( prd_id );
        
ALTER TABLE fotos
	ADD CONSTRAINT fk_fto_prd FOREIGN KEY ( fto_prd_id )
		REFERENCES produtos ( prd_id );

ALTER TABLE documentos
    ADD CONSTRAINT fk_doc_cli FOREIGN KEY ( doc_cli_id )
        REFERENCES clientes ( cli_id );

ALTER TABLE pedidos_compras
    ADD CONSTRAINT fk_pdc_cli FOREIGN KEY ( pdc_cli_id )
        REFERENCES clientes ( cli_id );

ALTER TABLE pedidos_compras
    ADD CONSTRAINT fk_pdc_end FOREIGN KEY ( pdc_end_id )
        REFERENCES enderecos ( end_id );

ALTER TABLE pedidos_trocas
    ADD CONSTRAINT fk_pdt_ppd FOREIGN KEY ( pdt_ppd_id )
        REFERENCES pedidos_compras_produtos ( ppd_id );
        
ALTER TABLE pedidos_trocas
    ADD CONSTRAINT fk_pdt_cli FOREIGN KEY ( pdt_cli_id )
        REFERENCES clientes ( cli_id );

ALTER TABLE pedidos_trocas
    ADD CONSTRAINT fk_pdt_pdc FOREIGN KEY ( pdt_pdc_id )
        REFERENCES pedidos_compras ( pdc_id );

ALTER TABLE pedidos_compras_cupons_promocoes
    ADD CONSTRAINT fk_pcp_cpp FOREIGN KEY ( pcp_cpp_id )
        REFERENCES cupons_promocoes ( cpp_id );

ALTER TABLE pedidos_compras_cupons_promocoes
    ADD CONSTRAINT fk_pcp_pdc FOREIGN KEY ( pcp_pdc_id )
        REFERENCES pedidos_compras ( pdc_id );

ALTER TABLE pedidos_compras_produtos
    ADD CONSTRAINT fk_ppd_pdc_1 FOREIGN KEY ( ppd_pdc_id )
        REFERENCES pedidos_compras ( pdc_id );

ALTER TABLE pedidos_compras_produtos
    ADD CONSTRAINT fk_ppd_prd_2 FOREIGN KEY ( ppd_prd_id )
        REFERENCES produtos ( prd_id );
        
ALTER TABLE produtos
    ADD CONSTRAINT fk_prd_prc FOREIGN KEY ( prd_prc_id )
        REFERENCES precificacoes ( prc_id );

ALTER TABLE produtos_carrinhos
    ADD CONSTRAINT fk_pcr_car_1 FOREIGN KEY ( pcr_car_id )
        REFERENCES carrinhos ( car_id );

ALTER TABLE produtos_carrinhos
    ADD CONSTRAINT fk_pcr_car_2 FOREIGN KEY ( pcr_prd_id )
        REFERENCES produtos ( prd_id );

ALTER TABLE pedidos_compras_cartoes
    ADD CONSTRAINT fk_pct_crt_1 FOREIGN KEY ( pct_crt_id )
        REFERENCES cartoes ( crt_id );

ALTER TABLE pedidos_compras_cartoes
    ADD CONSTRAINT fk_pct_crt_2 FOREIGN KEY ( pct_pdc_id )
        REFERENCES pedidos_compras ( pdc_id );
        
ALTER TABLE telefones
    ADD CONSTRAINT fk_tel_cli FOREIGN KEY ( tel_cli_id )
        REFERENCES clientes ( cli_id );

ALTER TABLE saidas
    ADD CONSTRAINT fk_sai_prd FOREIGN KEY ( sai_prd_id )
        REFERENCES produtos ( prd_id );