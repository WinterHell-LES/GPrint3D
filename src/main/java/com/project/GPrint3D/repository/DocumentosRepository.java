package com.project.GPrint3D.repository;

import com.project.GPrint3D.model.DocumentosModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DocumentosRepository extends JpaRepository<DocumentosModel, Integer>
{
    // Procura por nome do cliente
    @Query(value = "SELECT * FROM documentos WHERE doc_cli_id = ?", nativeQuery = true)
    public DocumentosModel findByClienteId (Integer id);
}
