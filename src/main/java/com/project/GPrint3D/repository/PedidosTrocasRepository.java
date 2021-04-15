package com.project.GPrint3D.repository;

import java.util.List;

import com.project.GPrint3D.model.PedidosTrocasModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface PedidosTrocasRepository extends JpaRepository<PedidosTrocasModel, Integer>
{
    // Procura por Id do cliente
    @Query(value = "SELECT * FROM pedidos_trocas WHERE pdt_escolha = 1 OR pdt_escolha = 4 ", nativeQuery = true)
    public List<PedidosTrocasModel> findAllByEscolha();
   
    // Procura por Id do cliente
    @Query(value = "SELECT * FROM pedidos_trocas WHERE pdt_cli_id = ?", nativeQuery = true)
    public List<PedidosTrocasModel> findAllByCliente(Integer id);

    // Procura por Id do pedido
    @Query(value = "SELECT * FROM pedidos_trocas WHERE pdt_id = ?", nativeQuery = true)
    public PedidosTrocasModel findOneById(Integer id);

    // Atualiza a escolha do pedido para o pedido referente
    @Modifying
    @Transactional(readOnly = false)
    @Query(value = "UPDATE pedidos_trocas SET pdt_escolha = ?1 WHERE pdt_id = ?2", nativeQuery = true)
    public void updateEscolha(Integer status, Integer id);

    // Atualiza o status do pedido para o pedido referente
    @Modifying
    @Transactional(readOnly = false)
    @Query(value = "UPDATE pedidos_trocas SET pdt_statuspedido = ?1 WHERE pdt_id = ?2", nativeQuery = true)
    public void updateStatusPedido(Integer status, Integer id);

    // Atualiza o status da logitica para o pedido referente
    @Modifying
    @Transactional(readOnly = false)
    @Query(value = "UPDATE pedidos_trocas SET pdt_statuslogistica = ?1 WHERE pdt_id = ?2", nativeQuery = true)
    public void updateStatusLogistica(Integer status, Integer id);
}
