package com.project.GPrint3D.repository;

import com.project.GPrint3D.model.TelefonesModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TelefonesRepository extends JpaRepository<TelefonesModel, Integer>
{
    // Procura por nome do cliente
    @Query(value = "SELECT * FROM telefones WHERE tel_cli_id = ?", nativeQuery = true)
    public TelefonesModel findByClienteId(Integer id);
}