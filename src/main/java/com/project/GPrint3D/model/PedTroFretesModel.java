package com.project.GPrint3D.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "PEDIDOS_TROCAS_FRETES")
public class PedTroFretesModel
{
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ptf_id", insertable = false, updatable = false)
	private Integer ptfId;

	@NotEmpty(message = "Nome é obrigatório")
    @Column(name = "ptf_empresa")
	private String ptfEmpresa;

	@NotEmpty(message = "Nome é obrigatório")
    @Column(name = "ptf_modalidade")
	private String ptfModalidade;

	@NotEmpty(message = "Nome é obrigatório")
    @Column(name = "ptf_prazo")
	private String ptfPrazo;

    @NotEmpty(message = "Preço é obrigatório")
    @Column(name = "ptf_valor")
    private double ptfValor;

    @OneToOne
    @JoinColumn(name = "ptf_pdt_id", referencedColumnName = "pdt_id")
    private PedidosTrocasModel pedidoTroca;

    public PedTroFretesModel() 
	{
		super();

		this.ptfId = 0;
		this.ptfEmpresa = "";
		this.ptfModalidade = "";
		this.ptfPrazo = "";
		this.ptfValor = 0.00;
	}

	public PedTroFretesModel(Integer ptfId, String ptfEmpresa, String ptfModalidade, String ptfPrazo, Double ptfValor) 
	{
		super( );

		this.ptfId = ptfId;
		this.ptfEmpresa = ptfEmpresa;
		this.ptfModalidade = ptfModalidade;
		this.ptfPrazo = ptfPrazo;
		this.ptfValor = ptfValor;
	}

    public Integer getPtfId() {
        return this.ptfId;
    }

    public void setPtfId(Integer ptfId) {
        this.ptfId = ptfId;
    }

    public String getPtfEmpresa() {
        return this.ptfEmpresa;
    }

    public void setPtfEmpresa(String ptfEmpresa) {
        this.ptfEmpresa = ptfEmpresa;
    }

    public String getPtfModalidade() {
        return this.ptfModalidade;
    }

    public void setPtfModalidade(String ptfModalidade) {
        this.ptfModalidade = ptfModalidade;
    }

    public String getPtfPrazo() {
        return this.ptfPrazo;
    }

    public void setPtfPrazo(String ptfPrazo) {
        this.ptfPrazo = ptfPrazo;
    }

    public double getPtfValor() {
        return this.ptfValor;
    }

    public void setPtfValor(double ptfValor) {
        this.ptfValor = ptfValor;
    }

    public PedidosTrocasModel getPedidoTroca() {
        return this.pedidoTroca;
    }

    public void setPedidoTroca(PedidosTrocasModel pedidoTroca) {
        this.pedidoTroca = pedidoTroca;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PedTroFretesModel)) {
            return false;
        }
        PedTroFretesModel pedTroFretesModel = (PedTroFretesModel) o;
        return Objects.equals(ptfId, pedTroFretesModel.ptfId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ptfId);
    }

}
