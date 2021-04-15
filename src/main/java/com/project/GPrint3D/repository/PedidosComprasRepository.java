package com.project.GPrint3D.repository;

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
    public List<PedidosComprasModel> findAllByCliente(Integer id);

    // Procura por Id do pedido
    @Query(value = "SELECT * FROM pedidos_compras WHERE pdc_id = ?", nativeQuery = true)
    public PedidosComprasModel findOneById(Integer id);

    // Procura pelo número do pedido
    @Query(value = "SELECT * FROM pedidos_compras WHERE pdc_numero = ?", nativeQuery = true)
    public PedidosComprasModel findByNumeroPedido(String numero);

    // Atualiza o status do pedido para o pedido referente
    @Modifying
    @Transactional(readOnly = false)
    @Query(value = "UPDATE pedidos_compras SET pdc_statuspedido = ?1 WHERE pdc_id = ?2", nativeQuery = true)
    public void updateStatusPedido(Integer status, Integer id);

    // Atualiza o status da logitica para o pedido referente
    @Modifying
    @Transactional(readOnly = false)
    @Query(value = "UPDATE pedidos_compras SET pdc_statuslogistica = ?1 WHERE pdc_id = ?2", nativeQuery = true)
    public void updateStatusLogistica(Integer status, Integer id);
}
