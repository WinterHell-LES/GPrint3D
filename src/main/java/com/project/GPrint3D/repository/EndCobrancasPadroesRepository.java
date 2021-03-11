package com.project.GPrint3D.repository;

import com.project.GPrint3D.model.EndCobrancasPadroesModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface EndCobrancasPadroesRepository extends JpaRepository<EndCobrancasPadroesModel, Integer>
{
    // Atualiza o id do endereço
    @Modifying
    @Transactional(readOnly = false)
    @Query(value = "UPDATE enderecos_cobrancas_padroes SET ecp_end_id = ?1 WHERE ecp_cli_id = ?2", nativeQuery = true)
    public void updateSenha(Integer idEndereco, Integer idCliente);
}
