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
    @Query(value = "SELECT * FROM usuarios WHERE ativo_usuario = 0", nativeQuery = true)
    public List<UsuariosModel> findAllNotAtivo();

    // Procura por nome de usuário
    @Query(value = "SELECT * FROM usuarios WHERE user_usuario = ?", nativeQuery = true)
    public UsuariosModel findByUsername(String username);

    // Atualiza a senha para o usuario referente
    @Modifying
    @Transactional(readOnly = false)
    @Query(value = "UPDATE usuarios SET password_usuario = ?1 WHERE id_usuario = ?2", nativeQuery = true)
    public void updatePass(String password, Integer id);
}
