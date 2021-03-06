package com.project.GPrint3D.repository;

import java.util.List;

import com.project.GPrint3D.model.CuponsTrocasModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CuponsTrocasRepository extends JpaRepository<CuponsTrocasModel, Integer>
{
    // Procura por id do cupom
    @Query(value = "SELECT * FROM cupons_trocas WHERE cpt_id = ?", nativeQuery = true)
    public CuponsTrocasModel findOneById (Integer id);

    // Procura por id do cliente
    @Query(value = "SELECT * FROM cupons_trocas WHERE cpt_cli_id = ?", nativeQuery = true)
    public List<CuponsTrocasModel> findAllByCliente (Integer id);
    // Procura por id do cliente
    @Query(value = "SELECT * FROM cupons_trocas WHERE cpt_cli_id = ? AND cpt_status = 1", nativeQuery = true)
    public List<CuponsTrocasModel> findAllAtivoByCliente (Integer id);

    // Procura pelo código do cupom
    @Query(value = "SELECT * FROM cupons_trocas WHERE cpt_codigo = ?", nativeQuery = true)
    public CuponsTrocasModel findByCodigo (String codigo);
}
