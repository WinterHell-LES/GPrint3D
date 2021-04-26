package com.project.GPrint3D.repository;

import java.util.List;

import com.project.GPrint3D.model.CategoriasProdutosModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoriasProdutosRepository extends JpaRepository<CategoriasProdutosModel, Integer>
{
    // Procura por nome do cliente
    @Query(value = "SELECT * FROM categorias_produtos WHERE cpr_prd_id = ?", nativeQuery = true)
    public List<CategoriasProdutosModel> findAllByProdutosId (Integer id);
}
