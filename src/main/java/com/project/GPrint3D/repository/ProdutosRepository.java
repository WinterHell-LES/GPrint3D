package com.project.GPrint3D.repository;

import java.util.List;

import com.project.GPrint3D.model.ProdutosModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ProdutosRepository extends JpaRepository<ProdutosModel, Integer>
{
    // Procura por nome do produto
    @Query(value = "SELECT * FROM produtos WHERE prd_nome = ?", nativeQuery = true)
    public ProdutosModel findOneByNome(String nome);

    // Procura por id do produto
    @Query(value = "SELECT * FROM produtos WHERE prd_id = ?", nativeQuery = true)
    public ProdutosModel findOneById(Integer id);

    // Procura por nome parecido do produto
    @Query(value = "SELECT * FROM produtos WHERE prd_nome LIKE %?%", nativeQuery = true)
    public List<ProdutosModel> findAllLike(String pesquisa);

    // Atualiza a ativação para o produto referente
    @Modifying
    @Transactional(readOnly = false)
    @Query(value = "UPDATE produtos SET prd_ativo = ?1 WHERE prd_id = ?2", nativeQuery = true)
    public void updadeAtiva(Boolean ativo, Integer id);
}
