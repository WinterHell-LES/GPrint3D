package com.project.GPrint3D.repository;

import com.project.GPrint3D.model.CuponsPromocoesModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CuponsPromocoesRepository extends JpaRepository<CuponsPromocoesModel, Integer>
{
    // Procura por id do cupom
    @Query(value = "SELECT * FROM cupons_promocoes WHERE cpp_id = ?", nativeQuery = true)
    public CuponsPromocoesModel findOneById (Integer id);

    // Procura por codigo do cupom
    @Query(value = "SELECT * FROM cupons_promocoes WHERE cpp_codigo = ?", nativeQuery = true)
    public CuponsPromocoesModel findByCodigo (String codigo);
}
