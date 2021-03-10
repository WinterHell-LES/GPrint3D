-- DATABASE
DROP DATABASE IF EXISTS crud_gprint3d;
CREATE DATABASE crud_gprint3d;
USE crud_gprint3d;

-- TABLES
DROP TABLE IF EXISTS cartoes;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE cartoes (
    crt_id           	MEDIUMINT NOT NULL AUTO_INCREMENT,
    crt_padrao          BOOLEAN,
    crt_bandeira		VARCHAR(20) NOT NULL,
    crt_nome         	VARCHAR(50) NOT NULL,
    crt_numero       	VARCHAR(50) NOT NULL,
    crt_validade     	VARCHAR(10) NOT NULL,
    crt_cvv          	VARCHAR(10) NOT NULL,
    crt_cli_id  		MEDIUMINT NOT NULL,
    CONSTRAINT pk_crt PRIMARY KEY ( crt_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS clientes;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE clientes (
    cli_id           	MEDIUMINT NOT NULL AUTO_INCREMENT,
    cli_ranking			VARCHAR(20) NOT NULL,
    cli_nome	       	VARCHAR(255) NOT NULL,
    cli_sexo			VARCHAR(20) NOT NULL,
    cli_dtnasc			DATE NOT NULL,
    cli_usu_id  		MEDIUMINT NOT NULL,
    CONSTRAINT pk_cli PRIMARY KEY ( cli_id )
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
    end_ent_padrao      BOOLEAN,
    end_cobranca		BOOLEAN,
    end_cob_padrao      BOOLEAN,
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
ALTER TABLE clientes
    ADD CONSTRAINT fk_cli_usu FOREIGN KEY ( cli_usu_id )
        REFERENCES usuarios ( usu_id );

ALTER TABLE cartoes
    ADD CONSTRAINT fk_crt_cli FOREIGN KEY ( crt_cli_id )
        REFERENCES clientes ( cli_id );
        
ALTER TABLE telefones
    ADD CONSTRAINT fk_tel_cli FOREIGN KEY ( tel_cli_id )
        REFERENCES clientes ( cli_id );

ALTER TABLE documentos
    ADD CONSTRAINT fk_doc_cli FOREIGN KEY ( doc_cli_id )
        REFERENCES clientes ( cli_id );

ALTER TABLE enderecos
    ADD CONSTRAINT fk_end_cli FOREIGN KEY ( end_cli_id )
        REFERENCES clientes ( cli_id );
        
-- TRIGGERS


-- USERS - ADM
insert into usuarios (usu_email, usu_senha, usu_regra, usu_ativo)
values ('admin@gprint3d.com', '$2a$10$TMfY1IunLWQy/wfKgltNZ.jyaJObeOdAfBK2VPicJzVh10P0nnDQO', 'ROLE_ADM', 1);