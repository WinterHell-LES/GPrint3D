package com.project.GPrint3D.repository;

import com.project.GPrint3D.model.EnderecosModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EnderecosRepository extends JpaRepository<EnderecosModel, Integer>
{
    // Procura por nome do cliente
    @Query(value = "SELECT * FROM enderecos WHERE end_cli_id = ?", nativeQuery = true)
    public EnderecosModel findByClienteId (Integer id);

    // Procura por nome do cliente e endereço de entrega
    @Query(value = "SELECT * FROM enderecos WHERE end_cli_id = ? AND end_entrega = 1", nativeQuery = true)
    public EnderecosModel findByEnderecosEntrega (Integer id);

    // Procura por nome do cliente e endereço de cobrança
    @Query(value = "SELECT * FROM enderecos WHERE end_cli_id = ? AND end_cobranca = 1", nativeQuery = true)
    public EnderecosModel findByEnderecosCobranca (Integer id);

    // Procura pelo id do endereço
    @Query(value = "SELECT * FROM enderecos WHERE end_id = ?", nativeQuery = true)
    public EnderecosModel findOneById (Integer id);
}
