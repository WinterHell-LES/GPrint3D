package com.project.GPrint3D.repository;

import com.project.GPrint3D.model.BandeirasModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface BandeirasRepository extends JpaRepository<BandeirasModel, Integer>
{
    // Procura por id da bandeira
    @Query(value = "SELECT * FROM bandeiras WHERE ban_id = ?", nativeQuery = true)
    public BandeirasModel findOneById (Integer id);

    // Atualiza a ativação para a bandeira referente
    @Modifying
    @Transactional(readOnly = false)
    @Query(value = "UPDATE bandeiras SET ban_ativo = ?1 WHERE ban_id = ?2", nativeQuery = true)
    public void updadeAtiva (Boolean ativo, Integer id);
}
