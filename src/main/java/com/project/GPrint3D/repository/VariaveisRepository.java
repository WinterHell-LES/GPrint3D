package com.project.GPrint3D.repository;

import com.project.GPrint3D.model.VariaveisModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VariaveisRepository extends JpaRepository<VariaveisModel, Integer>
{
    // Procura por id da variavel
    @Query(value = "SELECT * FROM sis_variaveis WHERE var_id = ?", nativeQuery = true)
    public VariaveisModel findOneById(Integer id);
}
