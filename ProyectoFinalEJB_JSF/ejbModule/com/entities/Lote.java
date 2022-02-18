package com.entities;

import java.io.Serializable;
import java.lang.String;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Lote
 *
 */
@Entity
@Table(name="LOTES")
@NamedQuery(name="Lote.findAll", query="SELECT l FROM Lote l")
public class Lote implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	@Id
	@SequenceGenerator(name="LOTES_IDLOTE_GENERATOR", sequenceName="SEQ_ID_LOTE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LOTES_IDLOTE_GENERATOR")
	@Column(name="ID_LOTE")
	private long idLote;
	private String nombre;
	private Date fecha;
	private long nAnimales;
	private BigDecimal consumoForrajeAnimal;
	private String observaciones;
	
	/*
	@ManyToOne
	@JoinColumn(name="ID_PREDIO")
	@Transient
	private Predio predio;
	*/
	private long idPredio;

	public Lote() {
		
	}   
	
	
		


	public Lote(long idLote, String nombre, Date fecha, long nAnimales, BigDecimal consumoForrajeAnimal,
			String observaciones, long idPredio) {
		this.idLote = idLote;
		this.nombre = nombre;
		this.fecha = fecha;
		this.nAnimales = nAnimales;
		this.consumoForrajeAnimal = consumoForrajeAnimal;
		this.observaciones = observaciones;
		this.idPredio = idPredio;
	}





	public long getIdLote() {
		return this.idLote;
	}

	public void setIdLote(long idLote) {
		this.idLote = idLote;
	}   
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}   
	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}   
	public long getNAnimales() {
		return this.nAnimales;
	}

	public void setNAnimales(long nAnimales) {
		this.nAnimales = nAnimales;
	}   
	public BigDecimal getConsumoForrajeAnimal() {
		return this.consumoForrajeAnimal;
	}

	public void setConsumoForrajeAnimal(BigDecimal consumoForrajeAnimal) {
		this.consumoForrajeAnimal = consumoForrajeAnimal;
	}   
	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}





	public long getIdPredio() {
		return idPredio;
	}





	public void setIdPredio(long idPredio) {
		this.idPredio = idPredio;
	}


	


	


   
}
