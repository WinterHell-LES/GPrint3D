package com.project.GPrint3D.repository;

import com.project.GPrint3D.model.PrecificacoesModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PrecificacoesRepository extends JpaRepository<PrecificacoesModel, Integer>
{
    // Procura por id da bandeira
    @Query(value = "SELECT * FROM precificacoes WHERE prc_id = ?", nativeQuery = true)
    public PrecificacoesModel findOneById (Integer id);
}