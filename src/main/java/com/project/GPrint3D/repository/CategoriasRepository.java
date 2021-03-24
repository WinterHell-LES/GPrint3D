package com.project.GPrint3D.repository;

import com.project.GPrint3D.model.CategoriasModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CategoriasRepository extends JpaRepository<CategoriasModel, Integer>
{
    // Procura por nome do produto
    @Query(value = "SELECT * FROM categorias WHERE ctg_nome = ?", nativeQuery = true)
    public CategoriasModel findOneByNome(String nome);

    // Procura por id da categoria
    @Query(value = "SELECT * FROM categorias WHERE ctg_id = ?", nativeQuery = true)
    public CategoriasModel findOneById(Integer id);

    // Atualiza a ativação para a categoria referente
    @Modifying
    @Transactional(readOnly = false)
    @Query(value = "UPDATE categorias SET ctg_ativo = ?1 WHERE ctg_id = ?2", nativeQuery = true)
    public void updadeAtiva(Boolean ativo, Integer id);
}
