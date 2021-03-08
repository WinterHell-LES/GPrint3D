package com.project.GPrint3D.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FOTOS")
public class FotosModel 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fto_id", insertable = false, updatable = false)
    private Integer ftoId;

    private String ftoNome;

    private long ftoSize;

    private Date ftoUploadDate;

    private byte[] ftoContent;
}
