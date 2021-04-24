package com.project.GPrint3D.repository;

import com.project.GPrint3D.model.EndEntregasPadroesModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EndEntregasPadroesRepository extends JpaRepository<EndEntregasPadroesModel, Integer>
{
    // Procura por id do endereço
    @Query(value = "SELECT * FROM enderecos_entregas_padroes WHERE eep_end_id = ?", nativeQuery = true)
    public EndEntregasPadroesModel findByEnderecoId (Integer id);

    // Procura por id do cliente
    @Query(value = "SELECT * FROM enderecos_entregas_padroes WHERE eep_cli_id = ?", nativeQuery = true)
    public EndEntregasPadroesModel findByClienteId (Integer id);
}
