package com.project.GPrint3D.repository;

import java.sql.Date;

import com.project.GPrint3D.model.PrdCarrinhosModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface PrdCarrinhosRepository extends JpaRepository<PrdCarrinhosModel, Integer>
{
    // Procura por id do produto no carrinho
    @Query(value = "SELECT * FROM produtos_carrinhos WHERE pcr_id = ?", nativeQuery = true)
    public PrdCarrinhosModel findOneById (Integer id);

    // Atualiza a quantidade e status do produto no carrinho
    @Modifying
    @Transactional(readOnly = false)
    @Query(value = "UPDATE produtos_carrinhos SET pcr_quantidade = ?1, pcr_ativo = ?2 WHERE pcr_id = ?3", nativeQuery = true)
    public void updateStatusPrdCarrinho (Integer quantidade, boolean status, Integer id);

    // Atualiza a quantidade e status do produto no carrinho
    @Modifying
    @Transactional(readOnly = false)
    @Query(value = "UPDATE produtos_carrinhos SET pcr_quantidade = ?1, pcr_ativo = ?2, pcr_data = ?3 WHERE pcr_id = ?4", nativeQuery = true)
    public void updateStatusAtivaPrdCarrinho (Integer quantidade, boolean status, Date data, Integer id);

    // Deleta produtos do carrinho pelo id
    @Modifying
    @Transactional(readOnly = false)
    @Query(value = "DELETE FROM produtos_carrinhos WHERE pcr_id = ?", nativeQuery = true)
    public void deleteByPcrId (Integer id);

    // Deleta todos os produtos do carrinho pelo id
    @Modifying
    @Transactional(readOnly = false)
    @Query(value = "DELETE FROM produtos_carrinhos WHERE pcr_car_id = ?", nativeQuery = true)
    public void deleteAllByCarId (Integer id);
}
