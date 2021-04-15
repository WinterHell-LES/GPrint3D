package com.project.GPrint3D.repository;

import java.util.List;

import com.project.GPrint3D.model.CuponsTrocasModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CuponsTrocasRepository extends JpaRepository<CuponsTrocasModel, Integer>
{
    // Procura por id do cliente
    @Query(value = "SELECT * FROM cupons_trocas WHERE cpt_cli_id = ?", nativeQuery = true)
    public List<CuponsTrocasModel> findAllByCliente(Integer id);
}
