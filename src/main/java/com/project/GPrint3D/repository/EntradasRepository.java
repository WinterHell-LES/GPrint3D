package com.project.GPrint3D.repository;

import com.project.GPrint3D.model.EntradasModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EntradasRepository extends JpaRepository<EntradasModel, Integer>
{
    // Procura pelo id do produto
    @Query(value = "SELECT * FROM entradas WHERE ent_prd_id = ? ORDER BY ent_id DESC limit 1", nativeQuery = true)
    public EntradasModel findByProduto (Integer id);
}
