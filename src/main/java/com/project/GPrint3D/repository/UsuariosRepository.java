package com.project.GPrint3D.repository;

import java.util.List;

import com.project.GPrint3D.model.UsuariosModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UsuariosRepository extends JpaRepository<UsuariosModel, Integer>
{
    // Listagem de usuários não ativos
    @Query(value = "SELECT * FROM usuarios WHERE usu_aitvo = 0", nativeQuery = true)
    public List<UsuariosModel> findAllNotAtivo ();

    // Procura por nome de usuário
    @Query(value = "SELECT * FROM usuarios WHERE usu_email = ?", nativeQuery = true)
    public UsuariosModel findByEmail (String email);

    // Procura por id do usuário
    @Query(value = "SELECT * FROM usuarios WHERE usu_id = ?", nativeQuery = true)
    public UsuariosModel findOneById (Integer id);

    // Atualiza a senha para o usuario referente
    @Modifying
    @Transactional(readOnly = false)
    @Query(value = "UPDATE usuarios SET usu_senha = ?1 WHERE usu_id = ?2", nativeQuery = true)
    public void updateSenha (String senha, Integer id);

    // Atualiza a ativação para o usuario referente
    @Modifying
    @Transactional(readOnly = false)
    @Query(value = "UPDATE usuarios SET usu_ativo = ?1 WHERE usu_id = ?2", nativeQuery = true)
    public void updadeAtiva (Boolean ativo, Integer id);
}
