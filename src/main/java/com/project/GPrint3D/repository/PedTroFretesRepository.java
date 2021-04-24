package com.project.GPrint3D.repository;

import com.project.GPrint3D.model.PedTroFretesModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PedTroFretesRepository extends JpaRepository<PedTroFretesModel, Integer>
{
    // Procura por nome do cliente, retorna apenas uma entrada
    @Query(value = "SELECT * FROM pedidos_trocas_fretes WHERE ptf_id = ?", nativeQuery = true)
    public PedTroFretesModel findOneById (Integer id);

    // Procura por nome do cliente, retorna apenas uma entrada
    @Query(value = "SELECT * FROM pedidos_trocas_fretes WHERE ptf_pdt_id = ?", nativeQuery = true)
    public PedTroFretesModel findByPedId (Integer id);
}
