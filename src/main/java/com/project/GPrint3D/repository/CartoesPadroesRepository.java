package com.project.GPrint3D.repository;

import com.project.GPrint3D.model.CartoesPadroesModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CartoesPadroesRepository extends JpaRepository<CartoesPadroesModel, Integer>
{
    // Procura por id do cartao
    @Query(value = "SELECT * FROM cartoes_padroes WHERE ctp_crt_id = ?", nativeQuery = true)
    public CartoesPadroesModel findByCartaoId(Integer id);

    // Procura por id do cliente
    @Query(value = "SELECT * FROM cartoes_padroes WHERE ctp_cli_id = ?", nativeQuery = true)
    public CartoesPadroesModel findByClienteId(Integer id);
}
