package com.project.GPrint3D.repository;

import com.project.GPrint3D.model.ProdutosModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProdutosRepository extends JpaRepository<ProdutosModel, Integer>
{
    @Query(value = "SELECT * FROM produtos WHERE prd_nome = ?", nativeQuery = true)
    public ProdutosModel findOneByNome(String nome);
}
