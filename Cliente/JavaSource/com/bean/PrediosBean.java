package com.bean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.PersistenceException;

import org.primefaces.event.RowEditEvent;

import com.entities.Predio;
import com.entities.Usuario;
import com.exception.ServiciosException;
import com.servicios.PredioBean;

/**
 * Session Bean implementation class PrediosBean
 */
@Named("predio")
@SessionScoped
public class PrediosBean implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Predio pre = new Predio();

	/**
     * Default constructor. 
     */
	
	private static PrediosBean repository = new PrediosBean();
	
	
    public static PrediosBean getInstance(){
		return repository;
	}
    
    
    @EJB
    private PredioBean predioBean;
    
    private long idPredio;
	private String nombre;
	private long areaHa;
	private String observaciones;
	
	private List<Predio> predios;
	private List<Predio> prediosL;
	
	private int i;
	
	
	
	public PredioBean getPredioBean() {
		return predioBean;
	}
	public void setPredioBean(PredioBean predioBean) {
		this.predioBean = predioBean;
	}
	public long getIdPredio() {
		return idPredio;
	}
	public void setIdPredio(long idPredio) {
		this.idPredio = idPredio;
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
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	
	
	public String ingresarPredio() throws ServiciosException{
		
		Predio pre = new Predio(0L, nombre, areaHa, observaciones);
		try {
			predioBean.ingresarPredio(pre);
		} catch (PersistenceException e) {
			// TODO: handle exception
			throw new ServiciosException("Error al agregar un Predio");
		}
		return "mostrar";
	}
	
	
	public void eliminarPredio() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			context.addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Predio eliminado correctamente"));
			predioBean.eliminarPredio(idPredio);
		} catch (ServiciosException e) {
			// TODO: handle exception
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Error al eliminar Predio"));
		}
	}
	
	public void actualizarPredio() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			context.addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transacción Existosa", "Predio modificado correctamente"));
			predioBean.actualizar(idPredio, nombre);
		} catch (Exception e) {
			// TODO: handle exception
			context.addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Error al modificar el Predio"));
		}
	}
	
	public List<Predio> getPredios(){
		try {
			predios = predioBean.obtenerTodosPredios();
		} catch (ServiciosException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return predios;
	}
	
	public long[] getPrediosL() throws ServiciosException{
		prediosL = predioBean.obtenerTodosPredios();
		long[] arrayidPredios = new long[prediosL.size()];
		i=0;
		for(Predio p : prediosL) {
			arrayidPredios[i] = p.getIdPredio();
			i++;
		}
		return arrayidPredios;
	}
	
	public void onRowEdit(RowEditEvent<Predio> event) {
		 
		 pre.setNombre(String.valueOf(event.getObject().getNombre()));
		 
		 
		 try {
			predioBean.actualizar(Long.valueOf(event.getObject().getIdPredio()),nombre);
		} catch (ServiciosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }

	    public void onRowCancel(RowEditEvent<Predio> event) {
	    	FacesMessage msg = new FacesMessage("Edición Cancelada", String.valueOf(event.getObject().getNombre()));
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	    }		
	
}
