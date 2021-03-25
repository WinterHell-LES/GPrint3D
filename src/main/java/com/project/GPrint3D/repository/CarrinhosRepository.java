package com.project.GPrint3D.repository;

import com.project.GPrint3D.model.CarrinhosModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CarrinhosRepository extends JpaRepository<CarrinhosModel, Integer>
{
    // Procura por nome do cliente
   @Query(value = "SELECT * FROM carrinho WHERE car_id = ?", nativeQuery = true)
   public CarrinhosModel findOneById(Integer id);

     // Procura por nome do cliente
    @Query(value = "SELECT * FROM carrinho WHERE car_cli_id = ?", nativeQuery = true)
    public CarrinhosModel findByClienteId(Integer id);
}
