package com.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Recorrida
 *
 */
@Entity
@Table(name="RECORRIDAS")
@NamedQuery(name="Recorrida.findAll", query="SELECT r FROM Recorrida r")
public class Recorrida implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	@Id
	@SequenceGenerator(name="RECORRIDAS_IDRECORRIDA_GENERATOR", sequenceName="SEQ_ID_RECORRIDA")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RECORRIDAS_IDRECORRIDA_GENERATOR")
	@Column(name="ID_RECORRIDA")
	private long idRecorrida;
	private long numero;
	private long idPredio;
	/*
	@OneToMany(mappedBy="predio")
	@Transient
	private List<Potrero> potreros;
	*/
	
	private Date fecha;
	private long kgMsHa;
	private long hojas;
	private long nudos;
	private String observaciones;
	

	public Recorrida() {
		
	}   
	

	
	public Recorrida(long idRecorrida, long numero, long idPredio, Date fecha, long kgMsHa, long hojas, long nudos,
			String observaciones) {
		super();
		this.idRecorrida = idRecorrida;
		this.numero = numero;
		this.idPredio = idPredio;
		this.fecha = fecha;
		this.kgMsHa = kgMsHa;
		this.hojas = hojas;
		this.nudos = nudos;
		this.observaciones = observaciones;
	}


	public long getIdRecorrida() {
		return this.idRecorrida;
	}

	public void setIdRecorrida(long idRecorrida) {
		this.idRecorrida = idRecorrida;
	}   
	public long getNumero() {
		return this.numero;
	}

	public void setNumero(long numero) {
		this.numero = numero;
	}   
	
	
	
	public long getIdPredio() {
		return idPredio;
	}



	public void setIdPredio(long idPredio) {
		this.idPredio = idPredio;
	}



	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}   
	public long getKgMsHa() {
		return this.kgMsHa;
	}

	public void setKgMsHa(long kgMsHa) {
		this.kgMsHa = kgMsHa;
	}   
	public long getHojas() {
		return this.hojas;
	}

	public void setHojas(long hojas) {
		this.hojas = hojas;
	}   
	public long getNudos() {
		return this.nudos;
	}

	public void setNudos(long nudos) {
		this.nudos = nudos;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	
   
}
