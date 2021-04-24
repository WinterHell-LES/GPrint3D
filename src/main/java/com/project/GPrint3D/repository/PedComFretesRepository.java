package com.project.GPrint3D.repository;

import com.project.GPrint3D.model.PedComFretesModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PedComFretesRepository extends JpaRepository<PedComFretesModel, Integer>
{
    // Procura por nome do cliente, retorna apenas uma entrada
    @Query(value = "SELECT * FROM pedidos_compras_fretes WHERE pcf_id = ?", nativeQuery = true)
    public PedComFretesModel findOneById (Integer id);

    // Procura por nome do cliente, retorna apenas uma entrada
    @Query(value = "SELECT * FROM pedidos_compras_fretes WHERE pcf_pdc_id = ?", nativeQuery = true)
    public PedComFretesModel findByPedId (Integer id);
}
