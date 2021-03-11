package com.project.GPrint3D.repository;

import com.project.GPrint3D.model.ProdutosModel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutosRepository extends JpaRepository<ProdutosModel, Integer>
{
    
}
