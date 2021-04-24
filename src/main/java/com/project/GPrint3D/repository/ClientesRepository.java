package com.project.GPrint3D.repository;

import com.project.GPrint3D.model.ClientesModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClientesRepository extends JpaRepository<ClientesModel, Integer>
{
    // Procura por id do usuário
    @Query(value = "SELECT * FROM clientes WHERE cli_usu_id = ?", nativeQuery = true)
    public ClientesModel findByUsuarioId (Integer id);

    // Procura por id do cliente
    @Query(value = "SELECT * FROM clientes WHERE cli_id = ?", nativeQuery = true)
    public ClientesModel findOneById (Integer id);
}
