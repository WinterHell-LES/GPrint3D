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
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "PEDIDOS_COMPRAS_FRETES")
public class PedComFretesModel
{
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pcf_id", insertable = false, updatable = false)
	private Integer pcfId;

	@NotEmpty(message = "Nome é obrigatório")
    @Column(name = "pcf_empresa")
	private String pcfEmpresa;

	@NotEmpty(message = "Nome é obrigatório")
    @Column(name = "pcf_modalidade")
	private String pcfModalidade;

	@NotEmpty(message = "Nome é obrigatório")
    @Column(name = "pcf_prazo")
	private String pcfPrazo;

    @NotNull(message = "Preço é obrigatório")
    @Column(name = "pcf_valor")
    private double pcfValor;

    @OneToOne
    @JoinColumn(name = "pcf_pdc_id", referencedColumnName = "pdc_id")
    private PedidosComprasModel pedidoCompra;

    public PedComFretesModel() 
	{
		super();

		this.pcfId = 0;
		this.pcfEmpresa = "";
		this.pcfModalidade = "";
		this.pcfPrazo = "";
		this.pcfValor = 0.00;
	}

	public PedComFretesModel(Integer pcfId, String pcfEmpresa, String pcfModalidade, String pcfPrazo, Double pcfValor) 
	{
		super( );

		this.pcfId = pcfId;
		this.pcfEmpresa = pcfEmpresa;
		this.pcfModalidade = pcfModalidade;
		this.pcfPrazo = pcfPrazo;
		this.pcfValor = pcfValor;
	}

    public Integer getPcfId() {
        return this.pcfId;
    }

    public void setPcfId(Integer pcfId) {
        this.pcfId = pcfId;
    }

    public String getPcfEmpresa() {
        return this.pcfEmpresa;
    }

    public void setPcfEmpresa(String pcfEmpresa) {
        this.pcfEmpresa = pcfEmpresa;
    }

    public String getPcfModalidade() {
        return this.pcfModalidade;
    }

    public void setPcfModalidade(String pcfModalidade) {
        this.pcfModalidade = pcfModalidade;
    }

    public String getPcfPrazo() {
        return this.pcfPrazo;
    }

    public void setPcfPrazo(String pcfPrazo) {
        this.pcfPrazo = pcfPrazo;
    }

    public double getPcfValor() {
        return this.pcfValor;
    }

    public void setPcfValor(double pcfValor) {
        this.pcfValor = pcfValor;
    }

    public PedidosComprasModel getPedidoCompra() {
        return this.pedidoCompra;
    }

    public void setPedidoCompra(PedidosComprasModel pedidoCompra) {
        this.pedidoCompra = pedidoCompra;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PedComFretesModel)) {
            return false;
        }
        PedComFretesModel pedComFretesModel = (PedComFretesModel) o;
        return Objects.equals(pcfId, pedComFretesModel.pcfId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pcfId);
    }
}
