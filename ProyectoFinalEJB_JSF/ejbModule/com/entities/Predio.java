package com.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Predio
 *
 */
@Entity
@Table(name="PREDIOS")
@NamedQuery(name="Predio.findAll", query="SELECT d FROM Predio d")
public class Predio implements Serializable {
	private static final long serialVersionUID = 1L;
	 
	
	@Id
	@SequenceGenerator(name="PREDIOS_IDPREDIO_GENERATOR", sequenceName="SEQ_ID_PREDIO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PREDIOS_IDPREDIO_GENERATOR")
	@Column(name="ID_PREDIO")
	private long idPredio;
	private String nombre;
	private long areaHa;
	private String observaciones;
	
		
	@OneToMany(mappedBy="predio")
	@Transient
	private List<Potrero> potreros;
	
	
	@OneToMany(mappedBy="predio")
	@Transient
	private List<Lote> lotes;
	
    public Predio() {
		
	}   
    
      
       
  


	public Predio(long idPredio, String nombre, long areaHa, String observaciones) {
		super();
		this.idPredio = idPredio;
		this.nombre = nombre;
		this.areaHa = areaHa;
		this.observaciones = observaciones;
		
	}



	public long getIdPredio() {
		return this.idPredio;
	}

	public void setIdPredio(long idPredio) {
		this.idPredio = idPredio;
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
	

		
	public String getObservaciones() {
		return observaciones;
	}



	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	public List<Potrero> getPotreros(){
		return this.potreros;
		
	}
	
	public void setPotreros(List<Potrero> potreros) {
		this.potreros =potreros;
	}
   /*
	public Potrero addPotrero(Potrero potrero) {
		getPotreros().add(potrero);
        potrero.setIdPredio(this);
        return potrero;
	}
     public Potrero removePotrero(Potrero potrero) {
    	 getPotreros().remove(potrero);
    	 potrero.setPredio(null);
    	 return potrero;
    } 
     */
     
     
     
     public List<Lote> getLotes(){
 		return this.lotes;
 		
 	}
 	
 	public void setLotes(List<Lote> lotes) {
 		this.lotes = lotes;
 	}
    /*
 	public Lote addLotes(Lote lote) {
 		getLotes().add(lote);
         lote.setIdPredio(this);
         return lote;
 	}
      public Lote removeLote(Lote lote) {
     	 getLotes().remove(lote);
     	 lote.setPredio(null);
     	 return lote;
     }   
	*/
}
