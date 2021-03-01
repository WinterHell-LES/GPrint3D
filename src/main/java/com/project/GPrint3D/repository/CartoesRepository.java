package com.project.GPrint3D.repository;

import com.project.GPrint3D.model.CartoesModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CartoesRepository extends JpaRepository<CartoesModel, Integer>
{
    // Procura por nome do cliente
    @Query(value = "SELECT * FROM cartoes WHERE crt_cli_id = ?", nativeQuery = true)
    public CartoesModel findByClienteId(Integer id);

    // Procura por nome do cliente, retorna apenas uma entrada
    @Query(value = "SELECT * FROM cartoes WHERE crt_id = ?", nativeQuery = true)
    public CartoesModel findOneById(Integer id);
}