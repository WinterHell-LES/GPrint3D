package com.project.GPrint3D.repository;

import java.sql.Date;
import java.util.List;

import com.project.GPrint3D.model.PedidosComprasModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface PedidosComprasRepository extends JpaRepository<PedidosComprasModel, Integer>
{
    // Procura por Id do cliente
    @Query(value = "SELECT * FROM pedidos_compras WHERE pdc_cli_id = ?", nativeQuery = true)
    public List<PedidosComprasModel> findAllByCliente (Integer id);

    // Procura por Id do pedido
    @Query(value = "SELECT * FROM pedidos_compras WHERE pdc_id = ?", nativeQuery = true)
    public PedidosComprasModel findOneById (Integer id);

    // Procura pelo número do pedido
    @Query(value = "SELECT * FROM pedidos_compras WHERE pdc_numero = ?", nativeQuery = true)
    public PedidosComprasModel findByNumeroPedido (String numero);

    // Atualiza o status do pedido para o pedido referente
    @Modifying
    @Transactional(readOnly = false)
    @Query(value = "UPDATE pedidos_compras SET pdc_statuspedido = ?1 WHERE pdc_id = ?2", nativeQuery = true)
    public void updateStatusPedido (Integer status, Integer id);

    // Atualiza o status da logitica para o pedido referente
    @Modifying
    @Transactional(readOnly = false)
    @Query(value = "UPDATE pedidos_compras SET pdc_statuslogistica = ?1 WHERE pdc_id = ?2", nativeQuery = true)
    public void updateStatusLogistica (Integer status, Integer id);

    // Atualiza o status da data de entrega para o pedido referente
    @Modifying
    @Transactional(readOnly = false)
    @Query(value = "UPDATE pedidos_compras SET pdc_datafim = ?1 WHERE pdc_id = ?2", nativeQuery = true)
    public void updateDataEntrega (Date data, Integer id);

    // Alimentação de gráfico - pedidos
    @Query(value = "SELECT " +
	                    "COUNT(DISTINCT PDC.pdc_id) AS 'Quantidade de pedidos', " +
                        "COUNT(PDC.pdc_id) AS 'Quantidade de produtos vendidos', " +
                        "SUM(PRD.prd_preco * PPD.ppd_quantidade) + SUM(DISTINCT PCF.pcf_valor) AS 'Lucro', " +
                        "UNIX_TIMESTAMP(PDC.pdc_datainicio) * 1000 AS 'Data'" +
                    "FROM " +
                        "pedidos_compras PDC " +
                    "LEFT JOIN " +
                        "pedidos_compras_produtos PPD " +
                    "ON " +
                        "PDC.pdc_id = PPD.ppd_pdc_id " +
                    "LEFT JOIN " +
                        "produtos PRD " +
                    "ON " +
                        "PPD.ppd_prd_id = PRD.prd_id " +
                    "LEFT JOIN " +
                        "pedidos_compras_fretes PCF " +
                    "ON " +
                        "PDC.pdc_id = PCF.pcf_pdc_id " +
                    "GROUP BY  " +
                        "MONTH(PDC.pdc_datainicio), YEAR(PDC.pdc_datainicio) " +
                    "ORDER BY " +
                        "YEAR(PDC.pdc_datainicio), MONTH(PDC.pdc_datainicio)", nativeQuery = true)
    public List<String> findAllByStatusPedido ();

    // Alimentação de gráfico - pedidos
    @Query(value = "SELECT " +
	                    "PRD.prd_nome AS 'Nome do produto', " +
	                    "COUNT(PDC.pdc_id) AS 'Quantidade de produtos vendidos', " +
	                    "UNIX_TIMESTAMP(PDC.pdc_datainicio) * 1000 AS 'Data' " +
                    "FROM " +
	                    "pedidos_compras PDC " +
                    "LEFT JOIN " +
                        "pedidos_compras_produtos PPD " +
                    "ON " +
                        "PDC.pdc_id = PPD.ppd_pdc_id " +
                    "LEFT JOIN " +
                        "produtos PRD " +
                    "ON " +
                        "PPD.ppd_prd_id = PRD.prd_id " +
                    "GROUP BY " +
                        "PRD.prd_id, MONTH(PDC.pdc_datainicio), YEAR(PDC.pdc_datainicio) " +
                    "ORDER BY " +
                        "YEAR(PDC.pdc_datainicio), MONTH(PDC.pdc_datainicio), COUNT(PDC.pdc_id) DESC", nativeQuery = true)
    public List<String> findAllByStatusProduto();

    // Alimentação de gráfico - pedidos
    @Query(value = "SELECT " +
                        "CTG.ctg_nome AS 'Categoria do produto', " +
                        "COUNT(PDC.pdc_id) AS 'Quantidade de produtos vendidos', " +
                        "UNIX_TIMESTAMP(PDC.pdc_datainicio) * 1000 AS 'Data' " +
                    "FROM " +
                        "pedidos_compras PDC " +
                    "LEFT JOIN " +
                        "pedidos_compras_produtos PPD " +
                    "ON " +
                        "PDC.pdc_id = PPD.ppd_pdc_id " +
                    "LEFT JOIN " +
                        "produtos PRD " +
                    "ON " +
                        "PPD.ppd_prd_id = PRD.prd_id " +
                    "LEFT JOIN " +
                        "categorias_produtos CPR " +
                    "ON " +
                        "CPR.cpr_prd_id = PRD.prd_id " +
                    "LEFT JOIN " +
                        "categorias CTG " +
                    "ON " +
                        "CPR.cpr_ctg_id = CTG.ctg_id " +
                    "GROUP BY " +
                        "CTG.ctg_nome, MONTH(PDC.pdc_datainicio), YEAR(PDC.pdc_datainicio) " +
                    "ORDER BY " +
                        "YEAR(PDC.pdc_datainicio), MONTH(PDC.pdc_datainicio), COUNT(PDC.pdc_id) DESC", nativeQuery = true)
    public List<String> findAllByStatusCategoria();
}
