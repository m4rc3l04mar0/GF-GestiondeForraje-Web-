package com.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

import com.enums.Estado;
import com.enums.Pastura;

/**
 * Entity implementation class for Entity: Potrero
 *
 */
@Entity
@Table(name="POTREROS")
@NamedQuery(name="Potrero.findAll", query="SELECT t FROM Potrero t")
public class Potrero implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	@Id
	@SequenceGenerator(name="POTREROS_IDPOTRERO_GENERATOR", sequenceName="SEQ_ID_POTRERO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="POTREROS_IDPOTRERO_GENERATOR")
	@Column(name="ID_POTRERO")
	private long idPotrero;
	private String nombre;
	private long areaHa;
	@Enumerated(EnumType.STRING)
	private Pastura pastura;
	@Enumerated(EnumType.STRING)
	private Estado estado;
	private String observaciones;
	
	
	/*
	@ManyToOne
	@JoinColumn(name="ID_PREDIO")
	@Transient
	private Predio predio;
	*/
	private long idPredio;
	
	public Potrero() {
		
	}   
	
	
	
	


	public Potrero(long idPotrero, String nombre, long areaHa, Pastura pastura, Estado estado, String observaciones,
			long idPredio) {
		super();
		this.idPotrero = idPotrero;
		this.nombre = nombre;
		this.areaHa = areaHa;
		this.pastura = pastura;
		this.estado = estado;
		this.observaciones = observaciones;
		this.idPredio = idPredio;
	}






	public long getIdPotrero() {
		return this.idPotrero;
	}

	public void setIdPotrero(long idPotrero) {
		this.idPotrero = idPotrero;
	}   
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}   
	public long getAreaHa() {
		return this.areaHa;
	}

	public void setAreaHa(long areaHa) {
		this.areaHa = areaHa;
	}   
	public Pastura getPastura() {
		return this.pastura;
	}

	public void setPastura(Pastura pastura) {
		this.pastura = pastura;
	}   
	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}   
	

	public long getIdPredio() {
		return idPredio;
	}


	public void setIdPredio(long idPredio) {
		this.idPredio = idPredio;
	}






	public String getObservaciones() {
		return observaciones;
	}


	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	
   
}
