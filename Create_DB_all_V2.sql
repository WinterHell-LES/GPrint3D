DROP DATABASE IF EXISTS crud_gprint3d;
CREATE DATABASE crud_gprint3d;
USE crud_gprint3d;
	
DROP TABLE IF EXISTS cartoes;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE cartoes (
    crt_id           	MEDIUMINT,
    crt_bandeira		VARCHAR(20),
    crt_nome         	VARCHAR(50),
    crt_numero       	VARCHAR(50),
    crt_validade     	DATE,
    crt_cvv          	VARCHAR(10),
    crt_cli_id  		MEDIUMINT NOT NULL
);

ALTER TABLE cartoes ADD CONSTRAINT pk_crt PRIMARY KEY ( crt_id );
ALTER TABLE cartoes CHANGE COLUMN crt_id crt_id MEDIUMINT NOT NULL AUTO_INCREMENT;

DROP TABLE IF EXISTS clientes;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE clientes (
    cli_id           	MEDIUMINT,
    cli_tipo         	VARCHAR(20),
    cli_nome         	VARCHAR(255),
    cli_sobrenome		VARCHAR(255),
    cli_sexo			VARCHAR(20),
    cli_dtnasc			DATE,
    cli_usu_id  		MEDIUMINT NOT NULL
);

ALTER TABLE clientes ADD CONSTRAINT pk_cli PRIMARY KEY ( cli_id );
ALTER TABLE clientes CHANGE COLUMN cli_id cli_id MEDIUMINT NOT NULL AUTO_INCREMENT;

DROP TABLE IF EXISTS telefones;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE telefones (
    tel_id           	MEDIUMINT,
    tel_tipo         	VARCHAR(20),
    tel_ddd         	VARCHAR(10),
    tel_numero			VARCHAR(20),
    tel_cli_id			MEDIUMINT NOT NULL
);

ALTER TABLE telefones ADD CONSTRAINT pk_tel PRIMARY KEY ( tel_id );
ALTER TABLE telefones CHANGE COLUMN tel_id tel_id MEDIUMINT NOT NULL AUTO_INCREMENT;

DROP TABLE IF EXISTS documentos;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE documentos (
    doc_id           	MEDIUMINT,
    doc_nome         	VARCHAR(20),
    doc_numero       	VARCHAR(50),
    doc_validade     	DATE,
    doc_cli_id  	MEDIUMINT NOT NULL
);

ALTER TABLE documentos ADD CONSTRAINT pk_doc PRIMARY KEY ( doc_id );
ALTER TABLE documentos CHANGE COLUMN doc_id doc_id MEDIUMINT NOT NULL AUTO_INCREMENT;

DROP TABLE IF EXISTS enderecos;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE enderecos (
    end_id           	MEDIUMINT,
    end_entrega			BOOLEAN,
    end_cobranca		BOOLEAN,
    end_descricao		VARCHAR(255),
    end_tipolog        	VARCHAR(20),
    end_logradouro   	VARCHAR(255),
    end_numero       	VARCHAR(10),
    end_cep          	VARCHAR(20),
    end_complemento  	VARCHAR(255),
    end_cidade       	VARCHAR(100),
    end_estado       	VARCHAR(100),
    end_pais       		VARCHAR(100),
    end_observacao		VARCHAR(255),
    end_cli_id  		MEDIUMINT NOT NULL
);

ALTER TABLE enderecos ADD CONSTRAINT pk_end PRIMARY KEY ( end_id );
ALTER TABLE enderecos CHANGE COLUMN end_id end_id MEDIUMINT NOT NULL AUTO_INCREMENT;

DROP TABLE IF EXISTS usuarios;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE usuarios (
    usu_id     			MEDIUMINT,
    usu_email  			VARCHAR(255),
    usu_senha  			VARCHAR(255),
    usu_regra 			VARCHAR(20),
    usu_ativo  			BOOLEAN
);

ALTER TABLE usuarios ADD CONSTRAINT pk_usu PRIMARY KEY ( usu_id );
ALTER TABLE usuarios CHANGE COLUMN usu_id usu_id MEDIUMINT NOT NULL AUTO_INCREMENT;
ALTER TABLE usuarios ADD UNIQUE INDEX uk_usu (usu_email ASC) VISIBLE;

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
        
-- Usuário - Administrador
insert into usuarios (usu_email, usu_senha, usu_regra, usu_ativo)
values ('admin@gprint3d.com', '$2a$10$TMfY1IunLWQy/wfKgltNZ.jyaJObeOdAfBK2VPicJzVh10P0nnDQO', 'ROLE_ADM', 1);