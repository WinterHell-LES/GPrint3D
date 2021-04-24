package com.project.GPrint3D.repository;

import com.project.GPrint3D.model.EndCobrancasPadroesModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EndCobrancasPadroesRepository extends JpaRepository<EndCobrancasPadroesModel, Integer>
{
    // Procura por id do endereço
    @Query(value = "SELECT * FROM enderecos_cobrancas_padroes WHERE ecp_end_id = ?", nativeQuery = true)
    public EndCobrancasPadroesModel findByEnderecoId (Integer id);

    // Procura por id do cliente
    @Query(value = "SELECT * FROM enderecos_cobrancas_padroes WHERE ecp_cli_id = ?", nativeQuery = true)
    public EndCobrancasPadroesModel findByClienteId (Integer id);
}
