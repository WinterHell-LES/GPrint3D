DROP DATABASE IF EXISTS crud_gprint3d;
CREATE DATABASE crud_gprint3d;
USE crud_gprint3d;

DROP TABLE IF EXISTS cidades;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE cidades (
    cid_id         INT,
    cid_descricao  VARCHAR(255),
    cid_dtcad      DATE,
    cid_est_id     INT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

ALTER TABLE cidades ADD CONSTRAINT pk_cid PRIMARY KEY ( cid_id );
ALTER TABLE cidades CHANGE COLUMN cid_id cid_id INT NOT NULL AUTO_INCREMENT;

DROP TABLE IF EXISTS clientes;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE clientes (
    cli_id      INT,
    cli_nome    VARCHAR(100),
    cli_usu_id  INT,
    cli_end_id  INT,
    cli_doc_id  INT,
    cli_tpc_id  INT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

ALTER TABLE clientes ADD CONSTRAINT pk_cli PRIMARY KEY ( cli_id );
ALTER TABLE clientes CHANGE COLUMN cli_id cli_id INT NOT NULL AUTO_INCREMENT;

DROP TABLE IF EXISTS documentos;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE documentos (
    doc_id        INT,
    doc_numero    VARCHAR(100),
    doc_validade  DATE,
    doc_dtcad     DATE,
    doc_tpd_id    INT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

ALTER TABLE documentos ADD CONSTRAINT pk_doc PRIMARY KEY ( doc_id );
ALTER TABLE documentos CHANGE COLUMN doc_id doc_id INT NOT NULL AUTO_INCREMENT;

DROP TABLE IF EXISTS enderecos;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE enderecos (
    end_id           INT,
    end_logradouro   VARCHAR(100),
    end_numero       VARCHAR(10),
    end_cep          VARCHAR(20),
    end_complemento  VARCHAR(255),
    end_dtcad        DATE,
    end_cid_id       INT,
    end_tpe_id       INT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

ALTER TABLE enderecos ADD CONSTRAINT pk_end PRIMARY KEY ( end_id );
ALTER TABLE enderecos CHANGE COLUMN end_id end_id INT NOT NULL AUTO_INCREMENT;

DROP TABLE IF EXISTS estados;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE estados (
    est_id         INT,
    est_descricao  VARCHAR(255),
    est_dtcad      DATE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

ALTER TABLE estados ADD CONSTRAINT pk_est PRIMARY KEY ( est_id );
ALTER TABLE estados CHANGE COLUMN est_id est_id INT NOT NULL AUTO_INCREMENT;

DROP TABLE IF EXISTS tipoclientes;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE tipoclientes (
    tpc_id         INT,
    tpc_nome       VARCHAR(255),
    tpc_descricao  VARCHAR(255),
    tpc_dtcad      DATE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

ALTER TABLE tipoclientes ADD CONSTRAINT pk_tpc PRIMARY KEY ( tpc_id );
ALTER TABLE tipoclientes CHANGE COLUMN tpc_id tpc_id INT NOT NULL AUTO_INCREMENT;

DROP TABLE IF EXISTS tipodocumentos;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE tipodocumentos (
    tpd_id         INT,
    tpd_nome       VARCHAR(255),
    tpd_descricao  VARCHAR(255),
    tpd_dtcad      DATE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

ALTER TABLE tipodocumentos ADD CONSTRAINT pk_tpd PRIMARY KEY ( tpd_id );
ALTER TABLE tipodocumentos CHANGE COLUMN tpd_id tpd_id INT NOT NULL AUTO_INCREMENT;

DROP TABLE IF EXISTS tipoenderecos;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE tipoenderecos (
    tpe_id         INT NOT NULL,
    tpe_nome       VARCHAR(255),
    tpe_descricao  VARCHAR(255),
    tpe_dtcad      DATE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

ALTER TABLE tipoenderecos ADD CONSTRAINT pk_tpe PRIMARY KEY ( tpe_id );
ALTER TABLE tipoenderecos CHANGE COLUMN tpe_id tpe_id INT NOT NULL AUTO_INCREMENT;

DROP TABLE IF EXISTS usuarios;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE usuarios (
    usu_id     INT NOT NULL,
    usu_email  VARCHAR(100),
    usu_senha  VARCHAR(255),
    usu_regra  VARCHAR(20),
    usu_ativo  BOOLEAN NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

ALTER TABLE usuarios ADD CONSTRAINT pk_usu PRIMARY KEY ( usu_id );
ALTER TABLE usuarios CHANGE COLUMN usu_id usu_id INT NOT NULL AUTO_INCREMENT;
ALTER TABLE usuarios ADD UNIQUE INDEX uk_usu (usu_email ASC) VISIBLE;
;

ALTER TABLE cidades
    ADD CONSTRAINT fk_cid_est FOREIGN KEY ( cid_est_id )
        REFERENCES estados ( est_id );

ALTER TABLE clientes
    ADD CONSTRAINT fk_cli_doc FOREIGN KEY ( cli_doc_id )
        REFERENCES documentos ( doc_id );

ALTER TABLE clientes
    ADD CONSTRAINT fk_cli_end FOREIGN KEY ( cli_end_id )
        REFERENCES enderecos ( end_id );

ALTER TABLE clientes
    ADD CONSTRAINT fk_cli_tpc FOREIGN KEY ( cli_tpc_id )
        REFERENCES tipoclientes ( tpc_id );

ALTER TABLE clientes
    ADD CONSTRAINT fk_cli_usu FOREIGN KEY ( cli_usu_id )
        REFERENCES usuarios ( usu_id );

ALTER TABLE documentos
    ADD CONSTRAINT fk_doc_tpd FOREIGN KEY ( doc_tpd_id )
        REFERENCES tipodocumentos ( tpd_id );

ALTER TABLE enderecos
    ADD CONSTRAINT fk_end_cid FOREIGN KEY ( end_cid_id )
        REFERENCES cidades ( cid_id );

ALTER TABLE enderecos
    ADD CONSTRAINT fk_end_tpe FOREIGN KEY ( end_tpe_id )
        REFERENCES tipoenderecos ( tpe_id );
        
-- Usuário - Administrador
insert into usuarios (usu_email, usu_senha, usu_regra, usu_ativo)
values ('admin@gprint3d.com', '$2a$10$TMfY1IunLWQy/wfKgltNZ.jyaJObeOdAfBK2VPicJzVh10P0nnDQO', 'ROLE_ADM', 1);