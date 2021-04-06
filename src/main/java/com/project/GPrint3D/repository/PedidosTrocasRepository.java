package com.project.GPrint3D.repository;

import com.project.GPrint3D.model.PedidosTrocasModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PedidosTrocasRepository extends JpaRepository<PedidosTrocasModel, Integer>
{
    // Procura por Id do pedido
   @Query(value = "SELECT * FROM carrinhos WHERE car_id = ?", nativeQuery = true)
   public PedidosTrocasModel findOneById(Integer id);
}
