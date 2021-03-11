package com.project.GPrint3D.repository;

import com.project.GPrint3D.model.CartoesPadroesModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CartoesPadroesRepository extends JpaRepository<CartoesPadroesModel, Integer> 
{
    // Atualiza o id do cartão
    @Modifying
    @Transactional(readOnly = false)
    @Query(value = "UPDATE cartoes_padroes SET ctp_crt_id = ?1 WHERE ctp_cli_id = ?2", nativeQuery = true)
    public void updateSenha(Integer idCartao, Integer idCliente);
}
