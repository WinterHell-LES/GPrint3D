package com.project.GPrint3D.repository;

import com.project.GPrint3D.model.ClientesModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ClientesRepository extends JpaRepository<ClientesModel, Integer>
{
    // Procura por nome de usuário
    @Query(value = "SELECT * FROM clientes WHERE cli_usu_id = ?", nativeQuery = true)
    public ClientesModel findByUsuarioId(Integer id);
}
