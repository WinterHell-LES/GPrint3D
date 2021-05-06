use crud_gprint3d;

-- TRIGGERS

-- Controle de estoque
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
	DECLARE prd_qnt		DECIMAL(6,2);
    
    SELECT prd_quantidade INTO prd_qnt FROM produtos WHERE prd_id = NEW.sai_prd_id;
    
	IF prd_qnt < NEW.sai_quantidade THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'QUANTIDADE DE PRODUTOS INSUFICIENTE';
    ELSE
		IF prd_qnt = NEW.sai_quantidade THEN
			UPDATE produtos SET prd_ativo = '0' WHERE prd_id = NEW.sai_prd_id;
        END IF;
        
		UPDATE produtos SET prd_quantidade = prd_quantidade - NEW.sai_quantidade WHERE prd_id = NEW.sai_prd_id;
    END IF;
END; $$

-- Controle de precificações
DELIMITER $$
DROP TRIGGER IF EXISTS tg_produto_precificacao; $$
CREATE TRIGGER tg_produto_precificacao AFTER INSERT ON entradas FOR EACH ROW
BEGIN
	DECLARE id_prc, desp_var, desp_fix, marg_luc	DECIMAL(6,2);
	
    SELECT prd_prc_id INTO id_prc FROM produtos WHERE prd_id = NEW.ent_prd_id;
	SELECT prc_desp_var INTO desp_var FROM precificacoes WHERE prc_id = id_prc; 
    SELECT prc_desp_fix INTO desp_fix FROM precificacoes WHERE prc_id = id_prc;
    SELECT prc_marg_luc INTO marg_luc FROM precificacoes WHERE prc_id = id_prc;
	
	UPDATE produtos SET prd_preco = (NEW.ent_custo / NEW.ent_quantidade) / ((100 - desp_var - desp_fix - marg_luc) / 100) WHERE prd_id = NEW.ent_prd_id;
END; $$

-- Controle de trocas
DELIMITER $$
DROP TRIGGER IF EXISTS tg_troca_produto; $$
CREATE TRIGGER tg_troca_produto AFTER INSERT ON pedidos_trocas FOR EACH ROW
BEGIN
	UPDATE pedidos_compras_produtos SET ppd_status = 1 WHERE ppd_id = NEW.pdt_ppd_id;
END; $$


-- Controle de logs
-- Bandeiras
DELIMITER $$
DROP TRIGGER IF EXISTS tg_ins_bandeiras_log; $$
CREATE TRIGGER tg_ins_bandeiras_log AFTER INSERT ON bandeiras FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'INSERT', 'Nova linha criada', 'bandeiras', NEW.ban_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_upd_bandeiras_log; $$
CREATE TRIGGER tg_upd_bandeiras_log AFTER UPDATE ON bandeiras FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'UPDATE', 'Linha atualizada', 'bandeiras', NEW.ban_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_del_bandeiras_log; $$
CREATE TRIGGER tg_del_bandeiras_log AFTER DELETE ON bandeiras FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'DELETE', 'Linha excluida', 'bandeiras', OLD.ban_id);
END; $$



-- Carrinhos
DELIMITER $$
DROP TRIGGER IF EXISTS tg_ins_carrinhos_log; $$
CREATE TRIGGER tg_ins_carrinhos_log AFTER INSERT ON carrinhos FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'INSERT', 'Nova linha criada', 'carrinhos', NEW.car_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_upd_carrinhos_log; $$
CREATE TRIGGER tg_upd_carrinhos_log AFTER UPDATE ON carrinhos FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'UPDATE', 'Linha atualizada', 'carrinhos', NEW.car_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_del_carrinhos_log; $$
CREATE TRIGGER tg_del_carrinhos_log AFTER DELETE ON carrinhos FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'DELETE', 'Linha excluida', 'carrinhos', OLD.car_id);
END; $$



-- Cartões
DELIMITER $$
DROP TRIGGER IF EXISTS tg_ins_cartoes_log; $$
CREATE TRIGGER tg_ins_cartoes_log AFTER INSERT ON cartoes FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'INSERT', 'Nova linha criada', 'cartoes', NEW.crt_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_upd_cartoes_log; $$
CREATE TRIGGER tg_upd_cartoes_log AFTER UPDATE ON cartoes FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'UPDATE', 'Linha atualizada', 'cartoes', NEW.crt_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_del_cartoes_log; $$
CREATE TRIGGER tg_del_cartoes_log AFTER DELETE ON cartoes FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'DELETE', 'Linha excluida', 'cartoes', OLD.crt_id);
END; $$



-- Cartões padrões
DELIMITER $$
DROP TRIGGER IF EXISTS tg_ins_cartoes_padroes_log; $$
CREATE TRIGGER tg_ins_cartoes_padroes_log AFTER INSERT ON cartoes_padroes FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'INSERT', 'Nova linha criada', 'cartoes_padroes', NEW.ctp_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_upd_cartoes_padroes_log; $$
CREATE TRIGGER tg_upd_cartoes_padroes_log AFTER UPDATE ON cartoes_padroes FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'UPDATE', 'Linha atualizada', 'cartoes_padroes', NEW.ctp_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_del_cartoes_padroes_log; $$
CREATE TRIGGER tg_del_cartoes_padroes_log AFTER DELETE ON cartoes_padroes FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'DELETE', 'Linha excluida', 'cartoes_padroes', OLD.ctp_id);
END; $$



-- Categorias
DELIMITER $$
DROP TRIGGER IF EXISTS tg_ins_categorias_log; $$
CREATE TRIGGER tg_ins_categorias_log AFTER INSERT ON categorias FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'INSERT', 'Nova linha criada', 'categorias', NEW.ctg_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_upd_categorias_log; $$
CREATE TRIGGER tg_upd_categorias_log AFTER UPDATE ON categorias FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'UPDATE', 'Linha atualizada', 'categorias', NEW.ctg_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_del_categorias_log; $$
CREATE TRIGGER tg_del_categorias_log AFTER DELETE ON categorias FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'DELETE', 'Linha excluida', 'categorias', OLD.ctg_id);
END; $$



-- Categorias produtos
DELIMITER $$
DROP TRIGGER IF EXISTS tg_ins_categorias_produtos_log; $$
CREATE TRIGGER tg_ins_categorias_produtos_log AFTER INSERT ON categorias_produtos FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'INSERT', 'Nova linha criada', 'categorias_produtos', NEW.cpr_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_upd_categorias_produtos_log; $$
CREATE TRIGGER tg_upd_categorias_produtos_log AFTER UPDATE ON categorias_produtos FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'UPDATE', 'Linha atualizada', 'categorias_produtos', NEW.cpr_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_del_categorias_produtos_log; $$
CREATE TRIGGER tg_del_categorias_produtos_log AFTER DELETE ON categorias_produtos FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'DELETE', 'Linha excluida', 'categorias_produtos', OLD.cpr_id);
END; $$



-- Clientes
DELIMITER $$
DROP TRIGGER IF EXISTS tg_ins_clientes_log; $$
CREATE TRIGGER tg_ins_clientes_log AFTER INSERT ON clientes FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'INSERT', 'Nova linha criada', 'clientes', NEW.cli_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_upd_clientes_log; $$
CREATE TRIGGER tg_upd_clientes_log AFTER UPDATE ON clientes FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'UPDATE', 'Linha atualizada', 'clientes', NEW.cli_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_del_clientes_log; $$
CREATE TRIGGER tg_del_clientes_log AFTER DELETE ON clientes FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'DELETE', 'Linha excluida', 'clientes', OLD.cli_id);
END; $$



-- Cupons promoções
DELIMITER $$
DROP TRIGGER IF EXISTS tg_ins_cupons_promocoes_log; $$
CREATE TRIGGER tg_ins_cupons_promocoes_log AFTER INSERT ON cupons_promocoes FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'INSERT', 'Nova linha criada', 'cupons_promocoes', NEW.cpp_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_upd_cupons_promocoes_log; $$
CREATE TRIGGER tg_upd_cupons_promocoes_log AFTER UPDATE ON cupons_promocoes FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'UPDATE', 'Linha atualizada', 'cupons_promocoes', NEW.cpp_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_del_cupons_promocoes_log; $$
CREATE TRIGGER tg_del_cupons_promocoes_log AFTER DELETE ON cupons_promocoes FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'DELETE', 'Linha excluida', 'cupons_promocoes', OLD.cpp_id);
END; $$



-- Cupons trocas
DELIMITER $$
DROP TRIGGER IF EXISTS tg_ins_cupons_trocas_log; $$
CREATE TRIGGER tg_ins_cupons_trocas_log AFTER INSERT ON cupons_trocas FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'INSERT', 'Nova linha criada', 'cupons_trocas', NEW.cpt_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_upd_cupons_trocas_log; $$
CREATE TRIGGER tg_upd_cupons_trocas_log AFTER UPDATE ON cupons_trocas FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'UPDATE', 'Linha atualizada', 'cupons_trocas', NEW.cpt_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_del_cupons_trocas_log; $$
CREATE TRIGGER tg_del_cupons_trocas_log AFTER DELETE ON cupons_trocas FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'DELETE', 'Linha excluida', 'cupons_trocas', OLD.cpt_id);
END; $$



-- Documentos
DELIMITER $$
DROP TRIGGER IF EXISTS tg_ins_documentos_log; $$
CREATE TRIGGER tg_ins_documentos_log AFTER INSERT ON documentos FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'INSERT', 'Nova linha criada', 'documentos', NEW.doc_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_upd_documentos_log; $$
CREATE TRIGGER tg_upd_documentos_log AFTER UPDATE ON documentos FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'UPDATE', 'Linha atualizada', 'documentos', NEW.doc_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_del_documentos_log; $$
CREATE TRIGGER tg_del_documentos_log AFTER DELETE ON documentos FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'DELETE', 'Linha excluida', 'documentos', OLD.doc_id);
END; $$



-- Endereços
DELIMITER $$
DROP TRIGGER IF EXISTS tg_ins_enderecos_log; $$
CREATE TRIGGER tg_ins_enderecos_log AFTER INSERT ON enderecos FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'INSERT', 'Nova linha criada', 'enderecos', NEW.end_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_upd_enderecos_log; $$
CREATE TRIGGER tg_upd_enderecos_log AFTER UPDATE ON enderecos FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'UPDATE', 'Linha atualizada', 'enderecos', NEW.end_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_del_enderecos_log; $$
CREATE TRIGGER tg_del_enderecos_log AFTER DELETE ON enderecos FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'DELETE', 'Linha excluida', 'enderecos', OLD.end_id);
END; $$



-- Endereços cobranças padrões
DELIMITER $$
DROP TRIGGER IF EXISTS tg_ins_enderecos_cobrancas_padroes_log; $$
CREATE TRIGGER tg_ins_enderecos_cobrancas_padroes_log AFTER INSERT ON enderecos_cobrancas_padroes FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'INSERT', 'Nova linha criada', 'enderecos_cobrancas_padroes', NEW.ecp_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_upd_enderecos_cobrancas_padroes_log; $$
CREATE TRIGGER tg_upd_enderecos_cobrancas_padroes_log AFTER UPDATE ON enderecos_cobrancas_padroes FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'UPDATE', 'Linha atualizada', 'enderecos_cobrancas_padroes', NEW.ecp_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_del_enderecos_cobrancas_padroes_log; $$
CREATE TRIGGER tg_del_enderecos_cobrancas_padroes_log AFTER DELETE ON enderecos_cobrancas_padroes FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'DELETE', 'Linha excluida', 'enderecos_cobrancas_padroes', OLD.ecp_id);
END; $$



-- Endereços entregas padrões
DELIMITER $$
DROP TRIGGER IF EXISTS tg_ins_enderecos_entregas_padroes_log; $$
CREATE TRIGGER tg_ins_enderecos_entregas_padroes_log AFTER INSERT ON enderecos_entregas_padroes FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'INSERT', 'Nova linha criada', 'enderecos_entregas_padroes', NEW.eep_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_upd_enderecos_entregas_padroes_log; $$
CREATE TRIGGER tg_upd_enderecos_entregas_padroes_log AFTER UPDATE ON enderecos_entregas_padroes FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'UPDATE', 'Linha atualizada', 'enderecos_entregas_padroes', NEW.eep_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_del_enderecos_entregas_padroes_log; $$
CREATE TRIGGER tg_del_enderecos_entregas_padroes_log AFTER DELETE ON enderecos_entregas_padroes FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'DELETE', 'Linha excluida', 'enderecos_entregas_padroes', OLD.eep_id);
END; $$



-- Entradas
DELIMITER $$
DROP TRIGGER IF EXISTS tg_ins_entradas_log; $$
CREATE TRIGGER tg_ins_entradas_log AFTER INSERT ON entradas FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'INSERT', 'Nova linha criada', 'entradas', NEW.ent_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_upd_entradas_log; $$
CREATE TRIGGER tg_upd_entradas_log AFTER UPDATE ON entradas FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'UPDATE', 'Linha atualizada', 'entradas', NEW.ent_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_del_entradas_log; $$
CREATE TRIGGER tg_del_entradas_log AFTER DELETE ON entradas FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'DELETE', 'Linha excluida', 'entradas', OLD.ent_id);
END; $$



-- Fotos
DELIMITER $$
DROP TRIGGER IF EXISTS tg_ins_fotos_log; $$
CREATE TRIGGER tg_ins_fotos_log AFTER INSERT ON fotos FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'INSERT', 'Nova linha criada', 'fotos', NEW.fto_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_upd_fotos_log; $$
CREATE TRIGGER tg_upd_fotos_log AFTER UPDATE ON fotos FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'UPDATE', 'Linha atualizada', 'fotos', NEW.fto_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_del_fotos_log; $$
CREATE TRIGGER tg_del_fotos_log AFTER DELETE ON fotos FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'DELETE', 'Linha excluida', 'fotos', OLD.fto_id);
END; $$



-- Histórico cupons trocas
DELIMITER $$
DROP TRIGGER IF EXISTS tg_ins_historicos_cupons_trocas_log; $$
CREATE TRIGGER tg_ins_historicos_cupons_trocas_log AFTER INSERT ON historicos_cupons_trocas FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'INSERT', 'Nova linha criada', 'historicos_cupons_trocas', NEW.hct_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_upd_historicos_cupons_trocas_log; $$
CREATE TRIGGER tg_upd_historicos_cupons_trocas_log AFTER UPDATE ON historicos_cupons_trocas FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'UPDATE', 'Linha atualizada', 'historicos_cupons_trocas', NEW.hct_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_del_historicos_cupons_trocas_log; $$
CREATE TRIGGER tg_del_historicos_cupons_trocas_log AFTER DELETE ON historicos_cupons_trocas FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'DELETE', 'Linha excluida', 'historicos_cupons_trocas', OLD.hct_id);
END; $$



-- Pedidos compras
DELIMITER $$
DROP TRIGGER IF EXISTS tg_ins_pedidos_compras_log; $$
CREATE TRIGGER tg_ins_pedidos_compras_log AFTER INSERT ON pedidos_compras FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'INSERT', 'Nova linha criada', 'pedidos_compras', NEW.pdc_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_upd_pedidos_compras_log; $$
CREATE TRIGGER tg_upd_pedidos_compras_log AFTER UPDATE ON pedidos_compras FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'UPDATE', 'Linha atualizada', 'pedidos_compras', NEW.pdc_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_del_pedidos_compras_log; $$
CREATE TRIGGER tg_del_pedidos_compras_log AFTER DELETE ON pedidos_compras FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'DELETE', 'Linha excluida', 'pedidos_compras', OLD.pdc_id);
END; $$



-- Pedidos compras cartões
DELIMITER $$
DROP TRIGGER IF EXISTS tg_ins_pedidos_compras_cartoes_log; $$
CREATE TRIGGER tg_ins_pedidos_compras_cartoes_log AFTER INSERT ON pedidos_compras_cartoes FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'INSERT', 'Nova linha criada', 'pedidos_compras_cartoes', NEW.pct_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_upd_pedidos_compras_cartoes_log; $$
CREATE TRIGGER tg_upd_pedidos_compras_cartoes_log AFTER UPDATE ON pedidos_compras_cartoes FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'UPDATE', 'Linha atualizada', 'pedidos_compras_cartoes', NEW.pct_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_del_pedidos_compras_cartoes_log; $$
CREATE TRIGGER tg_del_pedidos_compras_cartoes_log AFTER DELETE ON pedidos_compras_cartoes FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'DELETE', 'Linha excluida', 'pedidos_compras_cartoes', OLD.pct_id);
END; $$



-- Pedidos compras cupons promoções
DELIMITER $$
DROP TRIGGER IF EXISTS tg_ins_pedidos_compras_cupons_promocoes_log; $$
CREATE TRIGGER tg_ins_pedidos_compras_cupons_promocoes_log AFTER INSERT ON pedidos_compras_cupons_promocoes FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'INSERT', 'Nova linha criada', 'pedidos_compras_cupons_promocoes', NEW.pcp_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_upd_pedidos_compras_cupons_promocoes_log; $$
CREATE TRIGGER tg_upd_pedidos_compras_cupons_promocoes_log AFTER UPDATE ON pedidos_compras_cupons_promocoes FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'UPDATE', 'Linha atualizada', 'pedidos_compras_cupons_promocoes', NEW.pcp_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_del_pedidos_compras_cupons_promocoes_log; $$
CREATE TRIGGER tg_del_pedidos_compras_cupons_promocoes_log AFTER DELETE ON pedidos_compras_cupons_promocoes FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'DELETE', 'Linha excluida', 'pedidos_compras_cupons_promocoes', OLD.pcp_id);
END; $$



-- Pedidos compras cupons trocas
DELIMITER $$
DROP TRIGGER IF EXISTS tg_ins_pedidos_compras_cupons_trocas_log; $$
CREATE TRIGGER tg_ins_pedidos_compras_cupons_trocas_log AFTER INSERT ON pedidos_compras_cupons_trocas FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'INSERT', 'Nova linha criada', 'pedidos_compras_cupons_trocas', NEW.pct_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_upd_pedidos_compras_cupons_trocas_log; $$
CREATE TRIGGER tg_upd_pedidos_compras_cupons_trocas_log AFTER UPDATE ON pedidos_compras_cupons_trocas FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'UPDATE', 'Linha atualizada', 'pedidos_compras_cupons_trocas', NEW.pct_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_del_pedidos_compras_cupons_trocas_log; $$
CREATE TRIGGER tg_del_pedidos_compras_cupons_trocas_log AFTER DELETE ON pedidos_compras_cupons_trocas FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'DELETE', 'Linha excluida', 'pedidos_compras_cupons_trocas', OLD.pct_id);
END; $$



-- Pedidos compras fretes
DELIMITER $$
DROP TRIGGER IF EXISTS tg_ins_pedidos_compras_fretes_log; $$
CREATE TRIGGER tg_ins_pedidos_compras_fretes_log AFTER INSERT ON pedidos_compras_fretes FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'INSERT', 'Nova linha criada', 'pedidos_compras_fretes', NEW.pcf_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_upd_pedidos_compras_fretes_log; $$
CREATE TRIGGER tg_upd_pedidos_compras_fretes_log AFTER UPDATE ON pedidos_compras_fretes FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'INSERT', 'Nova linha criada', 'pedidos_compras_fretes', NEW.pcf_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_del_pedidos_compras_fretes_log; $$
CREATE TRIGGER tg_del_pedidos_compras_fretes_log AFTER DELETE ON pedidos_compras_fretes FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'INSERT', 'Nova linha criada', 'pedidos_compras_fretes', OLD.pcf_id);
END; $$



-- Pedidos compras produtos
DELIMITER $$
DROP TRIGGER IF EXISTS tg_ins_pedidos_compras_produtos_log; $$
CREATE TRIGGER tg_ins_pedidos_compras_produtos_log AFTER INSERT ON pedidos_compras_produtos FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'INSERT', 'Nova linha criada', 'pedidos_compras_produtos', NEW.ppd_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_upd_pedidos_compras_produtos_log; $$
CREATE TRIGGER tg_upd_pedidos_compras_produtos_log AFTER UPDATE ON pedidos_compras_produtos FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'UPDATE', 'Linha atualizada', 'pedidos_compras_produtos', NEW.ppd_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_del_pedidos_compras_produtos_log; $$
CREATE TRIGGER tg_del_pedidos_compras_produtos_log AFTER DELETE ON pedidos_compras_produtos FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'DELETE', 'Linha excluida', 'pedidos_produtos', OLD.ppd_id);
END; $$



-- Pedidos trocas
DELIMITER $$
DROP TRIGGER IF EXISTS tg_ins_pedidos_trocas_log; $$
CREATE TRIGGER tg_ins_pedidos_trocas_log AFTER INSERT ON pedidos_trocas FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'INSERT', 'Nova linha criada', 'pedidos_trocas', NEW.pdt_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_upd_pedidos_trocas_log; $$
CREATE TRIGGER tg_upd_pedidos_trocas_log AFTER UPDATE ON pedidos_trocas FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'UPDATE', 'Linha atualizada', 'pedidos_trocas', NEW.pdt_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_del_pedidos_trocas_log; $$
CREATE TRIGGER tg_del_pedidos_trocas_log AFTER DELETE ON pedidos_trocas FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'DELETE', 'Linha excluida', 'pedidos_trocas', OLD.pdt_id);
END; $$



-- Pedidos trocas fretes
DELIMITER $$
DROP TRIGGER IF EXISTS tg_ins_pedidos_trocas_fretes_log; $$
CREATE TRIGGER tg_ins_pedidos_trocas_fretes_log AFTER INSERT ON pedidos_trocas_fretes FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'INSERT', 'Nova linha criada', 'pedidos_trocas_fretes', NEW.ptf_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_upd_pedidos_trocas_fretes_log; $$
CREATE TRIGGER tg_upd_pedidos_trocas_fretes_log AFTER UPDATE ON pedidos_trocas_fretes FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'INSERT', 'Nova linha criada', 'pedidos_trocas_fretes', NEW.ptf_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_del_pedidos_trocas_fretes_log; $$
CREATE TRIGGER tg_del_pedidos_trocas_fretes_log AFTER DELETE ON pedidos_trocas_fretes FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'INSERT', 'Nova linha criada', 'pedidos_trocas_fretes', OLD.ptf_id);
END; $$



-- Precificações
DELIMITER $$
DROP TRIGGER IF EXISTS tg_ins_precificacoes_log; $$
CREATE TRIGGER tg_ins_precificacoes_log AFTER INSERT ON precificacoes FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'INSERT', 'Nova linha criada', 'precificacoes', NEW.prc_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_upd_precificacoes_log; $$
CREATE TRIGGER tg_upd_precificacoes_log AFTER UPDATE ON precificacoes FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'INSERT', 'Nova linha criada', 'precificacoes', NEW.prc_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_del_precificacoes_log; $$
CREATE TRIGGER tg_del_precificacoes_log AFTER DELETE ON precificacoes FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'INSERT', 'Nova linha criada', 'precificacoes', OLD.prc_id);
END; $$



-- Produtos
DELIMITER $$
DROP TRIGGER IF EXISTS tg_ins_produtos_log; $$
CREATE TRIGGER tg_ins_produtos_log AFTER INSERT ON produtos FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'INSERT', 'Nova linha criada', 'produtos', NEW.prd_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_upd_produtos_log; $$
CREATE TRIGGER tg_upd_produtos_log AFTER UPDATE ON produtos FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'UPDATE', 'Linha atualizada', 'produtos', NEW.prd_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_del_produtos_log; $$
CREATE TRIGGER tg_del_produtos_log AFTER DELETE ON produtos FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'DELETE', 'Linha excluida', 'produtos', OLD.prd_id);
END; $$



-- Produtos carrinhos
DELIMITER $$
DROP TRIGGER IF EXISTS tg_ins_produtos_carrinhos_log; $$
CREATE TRIGGER tg_ins_produtos_carrinhos_log AFTER INSERT ON produtos_carrinhos FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'INSERT', 'Nova linha criada', 'produtos_carrinhos', NEW.pcr_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_upd_produtos_carrinhos_log; $$
CREATE TRIGGER tg_upd_produtos_carrinhos_log AFTER UPDATE ON produtos_carrinhos FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'UPDATE', 'Linha atualizada', 'produtos_carrinhos', NEW.pcr_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_del_produtos_carrinhos_log; $$
CREATE TRIGGER tg_del_produtos_carrinhos_log AFTER DELETE ON produtos_carrinhos FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'DELETE', 'Linha excluida', 'produtos_carrinhos', OLD.pcr_id);
END; $$



-- Produtos justificativas
DELIMITER $$
DROP TRIGGER IF EXISTS tg_ins_produtos_justificativas_log; $$
CREATE TRIGGER tg_ins_produtos_justificativas_log AFTER INSERT ON produtos_justificativas FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'INSERT', 'Nova linha criada', 'produtos_justificativas', NEW.pju_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_upd_produtos_justificativas_log; $$
CREATE TRIGGER tg_upd_produtos_justificativas_log AFTER UPDATE ON produtos_justificativas FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'UPDATE', 'Linha atualizada', 'produtos_justificativas', NEW.pju_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_del_produtos_justificativas_log; $$
CREATE TRIGGER tg_del_produtos_justificativas_log AFTER DELETE ON produtos_justificativas FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'DELETE', 'Linha excluida', 'produtos_justificativas', OLD.pju_id);
END; $$



-- Saídas
DELIMITER $$
DROP TRIGGER IF EXISTS tg_ins_saidas_log; $$
CREATE TRIGGER tg_ins_saidas_log AFTER INSERT ON saidas FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'INSERT', 'Nova linha criada', 'saidas', NEW.sai_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_upd_saidas_log; $$
CREATE TRIGGER tg_upd_saidas_log AFTER UPDATE ON saidas FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'UPDATE', 'Linha atualizada', 'saidas', NEW.sai_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_del_saidas_log; $$
CREATE TRIGGER tg_del_saidas_log AFTER DELETE ON saidas FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'DELETE', 'Linha excluida', 'saidas', OLD.sai_id);
END; $$



-- Variáveis
DELIMITER $$
DROP TRIGGER IF EXISTS tg_ins_sis_variaveis_log; $$
CREATE TRIGGER tg_ins_sis_variaveis_log AFTER INSERT ON sis_variaveis FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'INSERT', 'Nova linha criada', 'sis_variaveis', NEW.var_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_upd_sis_variaveis_log; $$
CREATE TRIGGER tg_upd_sis_variaveis_log AFTER UPDATE ON sis_variaveis FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'UPDATE', 'Linha atualizada', 'sis_variaveis', NEW.var_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_del_sis_variaveis_log; $$
CREATE TRIGGER tg_del_sis_variaveis_log AFTER DELETE ON sis_variaveis FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'DELETE', 'Linha excluida', 'sis_variaveis', OLD.var_id);
END; $$



-- Telefones
DELIMITER $$
DROP TRIGGER IF EXISTS tg_ins_telefones_log; $$
CREATE TRIGGER tg_ins_telefones_log AFTER INSERT ON telefones FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'INSERT', 'Nova linha criada', 'telefones', NEW.tel_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_upd_telefones_log; $$
CREATE TRIGGER tg_upd_telefones_log AFTER UPDATE ON telefones FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'UPDATE', 'Linha atualizada', 'telefones', NEW.tel_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_del_telefones_log; $$
CREATE TRIGGER tg_del_telefones_log AFTER DELETE ON telefones FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'DELETE', 'Linha excluida', 'telefones', OLD.tel_id);
END; $$



-- Usuários
DELIMITER $$
DROP TRIGGER IF EXISTS tg_ins_usuarios_log; $$
CREATE TRIGGER tg_ins_usuarios_log AFTER INSERT ON usuarios FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'INSERT', 'Nova linha criada', 'usuarios', NEW.usu_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_upd_usuarios_log; $$
CREATE TRIGGER tg_upd_usuarios_log AFTER UPDATE ON usuarios FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'UPDATE', 'Linha atualizada', 'usuarios', NEW.usu_id);
END; $$
DELIMITER $$
DROP TRIGGER IF EXISTS tg_del_usuarios_log; $$
CREATE TRIGGER tg_del_usuarios_log AFTER DELETE ON usuarios FOR EACH ROW
BEGIN
	INSERT INTO log_transacoes (log_data, log_acao, log_descricao, log_tabela, log_dado) VALUES (CURDATE(), 'DELETE', 'Linha excluida', 'usuarios', OLD.usu_id);
END; $$