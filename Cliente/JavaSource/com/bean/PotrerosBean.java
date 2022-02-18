package com.bean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.PersistenceException;
import javax.persistence.Transient;

import org.primefaces.event.RowEditEvent;

import com.entities.Potrero;
import com.entities.Predio;
import com.entities.Usuario;
import com.enums.Estado;
import com.enums.Pastura;
import com.enums.Perfil;
import com.exception.ServiciosException;
import com.servicios.PotreroBean;



/**
 * Session Bean implementation class PotreroBean
 */
@Named("potrero")
@SessionScoped
public class PotrerosBean implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Potrero pot = new Potrero();
	/**
	 * 
	 */
private static PotrerosBean repository = new PotrerosBean();
	
	
    public static PotrerosBean getInstance(){
		return repository;
	}
    
    
    @EJB
    private PotreroBean potreroBean;
    
    
    private long idPotrero;
	private String nombre;
	private long areaHa;
	private Pastura pastura;
	private Estado estado;
	private String observaciones;
	
	private long idPredio;
	
	
	private List<Potrero> potreros;
	private List<Potrero> potrerosL;
	
	private int i;




	public PotreroBean getPotreroBean() {
		return potreroBean;
	}

	public void setPotreroBean(PotreroBean potreroBean) {
		this.potreroBean = potreroBean;
	}

	public long getIdPotrero() {
		return idPotrero;
	}

	public void setIdPotrero(long idPotrero) {
		this.idPotrero = idPotrero;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public long getAreaHa() {
		return areaHa;
	}

	public void setAreaHa(long areaHa) {
		this.areaHa = areaHa;
	}

	public Pastura getPastura() {
		return pastura;
	}

	public void setPastura(Pastura pastura) {
		this.pastura = pastura;
	}

	public Estado getEstado() {
		return estado;
	}
	
	public Pastura[] getPasturas(){
		return Pastura.values();
	}


	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public Estado[] getEstados(){
		return Estado.values();
	}

	public String getObservaciones() {
		return observaciones;
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

public String ingresarPotrero() throws ServiciosException{
		
		Potrero pot = new Potrero(0L, nombre, areaHa, pastura, estado, observaciones, idPredio);
		try {
			potreroBean.ingresarPotrero(pot);
		} catch (PersistenceException e) {
			// TODO: handle exception
			throw new ServiciosException("Error al agregar un Potrero");
		}
		return "mostrar";
	}
	
	
	public void eliminarPotrero() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			context.addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Potrero eliminado correctamente"));
			potreroBean.eliminarPotrero(idPotrero);
		} catch (ServiciosException e) {
			// TODO: handle exception
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Error al eliminar Potrero"));
		}
	}
	
	public void actualizarPotrero() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			context.addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transacción Existosa", "Potrero modificado correctamente"));
			potreroBean.actualizar(idPotrero,nombre);
		} catch (Exception e) {
			// TODO: handle exception
			context.addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Error al modificar el Predio"));
		}
	}
	
	public List<Potrero> getPotreros(){
		try {
			potreros = potreroBean.obtenerTodosPotreros();
		} catch (ServiciosException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return potreros;
	}
	
	public long[] getPotrerosL() throws ServiciosException{
		potrerosL = potreroBean.obtenerTodosPotreros();
		long[] arrayidPredios = new long[potrerosL.size()];
		i=0;
		for(Potrero p : potrerosL) {
			arrayidPredios[i] = p.getIdPotrero();
			i++;
		}
		return arrayidPredios;
	}
	
	
	public void onRowEdit(RowEditEvent<Potrero> event) {
		 
		 pot.setNombre(String.valueOf(event.getObject().getNombre()));
		 
		 try {
			potreroBean.actualizar(Long.valueOf(event.getObject().getIdPotrero()),nombre);
		} catch (ServiciosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }

	    public void onRowCancel(RowEditEvent<Potrero> event) {
	    	FacesMessage msg = new FacesMessage("Edición Cancelada", String.valueOf(event.getObject().getNombre()));
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	    }		
	
}
	
    

