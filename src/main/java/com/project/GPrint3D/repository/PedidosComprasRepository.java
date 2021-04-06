package com.project.GPrint3D.repository;

import com.project.GPrint3D.model.PedidosComprasModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PedidosComprasRepository extends JpaRepository<PedidosComprasModel, Integer>
{
    // Procura por Id do pedido
   @Query(value = "SELECT * FROM carrinhos WHERE car_id = ?", nativeQuery = true)
   public PedidosComprasModel findOneById(Integer id);
}
