package com.project.GPrint3D.repository;

import com.project.GPrint3D.model.PedidosComprasModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PedidosComprasRepository extends JpaRepository<PedidosComprasModel, Integer>
{
    // Procura por Id do pedido
   @Query(value = "SELECT * FROM pedidos_compras WHERE car_id = ?", nativeQuery = true)
   public PedidosComprasModel findOneById(Integer id);

   // Procura pelo número do pedido
   @Query(value = "SELECT * FROM pedidos_compras WHERE pdc_numero = ?", nativeQuery = true)
   public PedidosComprasModel findByNumeroPedido(String numero);
}
