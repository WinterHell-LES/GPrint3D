package com.project.GPrint3D.repository;

import com.project.GPrint3D.model.EnderecosModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EnderecosRepository extends JpaRepository<EnderecosModel, Integer>
{
    // Procura por nome do cliente
    @Query(value = "SELECT * FROM enderecos WHERE end_cli_id = ?", nativeQuery = true)
    public EnderecosModel findByClienteId(Integer id);
}
