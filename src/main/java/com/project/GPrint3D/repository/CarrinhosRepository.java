package com.project.GPrint3D.repository;

import com.project.GPrint3D.model.CarrinhosModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CarrinhosRepository extends JpaRepository<CarrinhosModel, Integer>
{
    // Procura por Id do carrinho
   @Query(value = "SELECT * FROM carrinhos WHERE car_id = ?", nativeQuery = true)
   public CarrinhosModel findOneById(Integer id);

     // Procura por id do cliente
    @Query(value = "SELECT * FROM carrinhos WHERE car_cli_id = ?", nativeQuery = true)
    public CarrinhosModel findByClienteId(Integer id);

    // Procura por temporary cliente id
    @Query(value = "SELECT * FROM carrinhos WHERE car_cli_temp = ?", nativeQuery = true)
    public CarrinhosModel findByClienteTempId(String id);
}
