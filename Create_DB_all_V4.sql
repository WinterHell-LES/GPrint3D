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
    car_cli_id  		MEDIUMINT NOT NULL,
    CONSTRAINT pk_car PRIMARY KEY ( car_id )
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS cartoes;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE cartoes (
    crt_id           	MEDIUMINT NOT NULL AUTO_INCREMENT,
    crt_bandeira		VARCHAR(20) NOT NULL,
    crt_nome         	VARCHAR(50) NOT NULL,
    crt_numero       	VARCHAR(50) NOT NULL,
    crt_validade     	VARCHAR(10) NOT NULL,
    crt_cvv          	VARCHAR(255) NOT NULL,
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
    CONSTRAINT pk_ctg PRIMARY KEY ( ctg_id )
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS clientes;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE clientes (
    cli_id           	MEDIUMINT NOT NULL AUTO_INCREMENT,
    cli_ranking			VARCHAR(20) NOT NULL DEFAULT 'Bronze',
    cli_nome	       	VARCHAR(255) NOT NULL,
    cli_sexo			VARCHAR(20) NOT NULL,
    cli_dtnasc			DATE NOT NULL,
    cli_usu_id  		MEDIUMINT NOT NULL,
    CONSTRAINT pk_cli PRIMARY KEY ( cli_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS cupons;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE cupons (
    cpn_id             	MEDIUMINT NOT NULL AUTO_INCREMENT,
    cpn_nome           	VARCHAR(100) NOT NULL,
    cpn_codigo         	VARCHAR(255) NOT NULL,
    cpn_desconto       	MEDIUMINT NOT NULL,
    cpn_validade       	DATE NOT NULL,
    cpn_ctg_id  		MEDIUMINT NOT NULL,
    CONSTRAINT pk_cpn PRIMARY KEY ( cpn_id )
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
    ent_descricao		VARCHAR(255) NOT NULL,
    ent_quantidade		MEDIUMINT NOT NULL,
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

DROP TABLE IF EXISTS pedidos;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE pedidos (
    ped_id            	MEDIUMINT NOT NULL AUTO_INCREMENT,
    ped_data			DATE NOT NULL,
    ped_cli_id   		MEDIUMINT NOT NULL,
    ped_end_id  		MEDIUMINT NOT NULL,
    CONSTRAINT pk_ped PRIMARY KEY ( ped_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS pedidos_cartoes;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE pedidos_cartoes (
	pct_id				MEDIUMINT NOT NULL AUTO_INCREMENT,
    pct_crt_id  		MEDIUMINT NOT NULL,
    pct_ped_id  		MEDIUMINT NOT NULL,
    CONSTRAINT pk_pct PRIMARY KEY ( pct_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS pedidos_cupons;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE pedidos_cupons (
	pcp_id				MEDIUMINT NOT NULL AUTO_INCREMENT,
    pcp_cpn_id   		MEDIUMINT NOT NULL,
    pcp_ped_id  		MEDIUMINT NOT NULL,
    CONSTRAINT pk_pcp PRIMARY KEY ( pcp_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS pedidos_produtos;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE pedidos_produtos (
	ppd_id				MEDIUMINT NOT NULL AUTO_INCREMENT,
    ppd_ped_id   		MEDIUMINT NOT NULL,
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
    prd_quantidade     	MEDIUMINT NOT NULL,
    prd_preco          	FLOAT NOT NULL,
    prd_ctg_id  		MEDIUMINT NOT NULL,
    CONSTRAINT pk_prd PRIMARY KEY ( prd_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS produtos_carrinhos;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE produtos_carrinhos (
	pcr_id             	MEDIUMINT NOT NULL AUTO_INCREMENT,
    pcr_prd_id  		MEDIUMINT NOT NULL,
    pcr_car_id  		MEDIUMINT NOT NULL,
    CONSTRAINT pk_pcr PRIMARY KEY ( pcr_id )
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

-- FOREIGN KEYS
ALTER TABLE carrinhos
    ADD CONSTRAINT fk_car_cli FOREIGN KEY ( car_cli_id )
        REFERENCES clientes ( cli_id );

ALTER TABLE cartoes
    ADD CONSTRAINT fk_crt_cli FOREIGN KEY ( crt_cli_id )
        REFERENCES clientes ( cli_id );

ALTER TABLE cartoes_padroes
    ADD CONSTRAINT fk_ctp_crt FOREIGN KEY ( ctp_crt_id )
        REFERENCES cartoes ( crt_id );

ALTER TABLE cartoes_padroes
    ADD CONSTRAINT fk_ctp_cli FOREIGN KEY ( ctp_cli_id )
        REFERENCES clientes ( cli_id );

ALTER TABLE cupons
    ADD CONSTRAINT fk_cpn_ctg FOREIGN KEY ( cpn_ctg_id )
        REFERENCES categorias ( ctg_id );

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

ALTER TABLE pedidos
    ADD CONSTRAINT fk_ped_cli FOREIGN KEY ( ped_cli_id )
        REFERENCES clientes ( cli_id );

ALTER TABLE pedidos
    ADD CONSTRAINT fk_ped_end FOREIGN KEY ( ped_end_id )
        REFERENCES enderecos ( end_id );

ALTER TABLE produtos
    ADD CONSTRAINT fk_prd_ctg FOREIGN KEY ( prd_ctg_id )
        REFERENCES categorias ( ctg_id );

ALTER TABLE pedidos_cupons
    ADD CONSTRAINT fk_pcp_cpn_1 FOREIGN KEY ( pcp_cpn_id )
        REFERENCES cupons ( cpn_id );

ALTER TABLE pedidos_cupons
    ADD CONSTRAINT fk_pcp_cpn_2 FOREIGN KEY ( pcp_ped_id )
        REFERENCES pedidos ( ped_id );

ALTER TABLE pedidos_produtos
    ADD CONSTRAINT fk_ppd_ped_1 FOREIGN KEY ( ppd_ped_id )
        REFERENCES pedidos ( ped_id );

ALTER TABLE pedidos_produtos
    ADD CONSTRAINT fk_ppd_ped_2 FOREIGN KEY ( ppd_prd_id )
        REFERENCES produtos ( prd_id );

ALTER TABLE produtos_carrinhos
    ADD CONSTRAINT fk_pcr_car_1 FOREIGN KEY ( pcr_car_id )
        REFERENCES carrinhos ( car_id );

ALTER TABLE produtos_carrinhos
    ADD CONSTRAINT fk_pcr_car_2 FOREIGN KEY ( pcr_prd_id )
        REFERENCES produtos ( prd_id );

ALTER TABLE pedidos_cartoes
    ADD CONSTRAINT fk_pct_crt_1 FOREIGN KEY ( pct_crt_id )
        REFERENCES cartoes ( crt_id );

ALTER TABLE pedidos_cartoes
    ADD CONSTRAINT fk_pct_crt_2 FOREIGN KEY ( pct_ped_id )
        REFERENCES pedidos ( ped_id );
        
ALTER TABLE telefones
    ADD CONSTRAINT fk_tel_cli FOREIGN KEY ( tel_cli_id )
        REFERENCES clientes ( cli_id );

ALTER TABLE saidas
    ADD CONSTRAINT fk_sai_prd FOREIGN KEY ( sai_prd_id )
        REFERENCES produtos ( prd_id );
        
-- TRIGGERS
DELIMITER $$
DROP TRIGGER IF EXISTS tg_entrada_produto; $$
CREATE TRIGGER tg_entrada_produto AFTER INSERT ON entradas FOR EACH ROW
BEGIN
	UPDATE produtos SET prd_quantidade = prd_quantidade + NEW.ent_quantidade WHERE prd_id = NEW.ent_prd_id;
END; $$

DELIMITER $$
DROP TRIGGER IF EXISTS tg_saida_produto; $$
CREATE TRIGGER tg_saida_produto BEFORE INSERT ON saidas FOR EACH ROW
BEGIN
	IF prd_quantidade < prd_quantidade THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'QUANTIDADE DE PRODUTOS INSUFICIENTE';
    ELSE
		UPDATE produtos SET prd_quantidade = prd_quantidade - prd_quantidade WHERE prd_id = NEW.sai_prd_id;
    END IF;
END; $$

-- USERS - ADM
insert into usuarios (usu_email, usu_senha, usu_regra, usu_ativo)
values ('admin@gprint3d.com', '$2a$10$TMfY1IunLWQy/wfKgltNZ.jyaJObeOdAfBK2VPicJzVh10P0nnDQO', 'ROLE_ADM', 1);