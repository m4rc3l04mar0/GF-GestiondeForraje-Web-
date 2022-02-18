package com.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.PersistenceException;

import org.primefaces.event.RowEditEvent;

import com.entities.Potrero;
import com.entities.Recorrida;
import com.entities.Usuario;
import com.enums.Perfil;
import com.exception.ServiciosException;
import com.servicios.PotreroBean;
import com.servicios.RecorridaBean;

/**
 * Session Bean implementation class RecorridasBean
 */
@Named("recorrida")
@SessionScoped
public class RecorridasBean implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Recorrida rec = new Recorrida();

	/**
     * Default constructor. 
     */
private static RecorridasBean repository = new RecorridasBean();
	
	
    public static RecorridasBean getInstance(){
		return repository;
	}
    
    
    @EJB
    private RecorridaBean recorridaBean;
    @EJB
	private PotreroBean potreroBean;
    
    private long idRecorrida;
	private long numero;
	private long idPotrero;
	private Date fecha;
	private long kgMsHa;
	private long hojas;
	private long nudos;
	private String observaciones;
	
	private List<Recorrida> recorridas;
	private List<Recorrida> recorridasL;
	
	private List<Potrero> potrerosL;
	
	private int i;


	public RecorridaBean getRecorridaBean() {
		return recorridaBean;
	}

	public void setRecorridaBean(RecorridaBean recorridaBean) {
		this.recorridaBean = recorridaBean;
	}

	public long getIdRecorrida() {
		return idRecorrida;
	}

	public void setIdRecorrida(long idRecorrida) {
		this.idRecorrida = idRecorrida;
	}

	public long getNumero() {
		return numero;
	}

	public void setNumero(long numero) {
		this.numero = numero;
	}

	
	
	public long getIdPotrero() {
		return idPotrero;
	}

	public void setIdPotrero(long idPotrero) {
		this.idPotrero = idPotrero;
	}

	public long[] getPotrerosL() throws ServiciosException{
		potrerosL = potreroBean.obtenerTodosPotreros();
		long[] arrayidPotreros = new long[potrerosL.size()];
		i=0;
		for(Potrero t : potrerosL) {
			arrayidPotreros[i]=t.getIdPotrero();
			i++;
		}
		return arrayidPotreros;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public long getKgMsHa() {
		return kgMsHa;
	}

	public void setKgMsHa(long kgMsHa) {
		this.kgMsHa = kgMsHa;
	}

	public long getHojas() {
		return hojas;
	}

	public void setHojas(long hojas) {
		this.hojas = hojas;
	}

	public long getNudos() {
		return nudos;
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
	
     public String ingresarRecorrida() throws ServiciosException{
		
		Recorrida rec = new Recorrida(0L, numero, idPotrero, fecha, kgMsHa, hojas, nudos, observaciones);
		try {
			recorridaBean.ingresarRecorridas(rec);
		} catch (PersistenceException e) {
			// TODO: handle exception
			throw new ServiciosException("Error al agregar una Recorrida");
		}
		return "mostrar";
	}
	
	
	public void eliminarRecorrida() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			context.addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Potrero eliminado correctamente"));
			recorridaBean.eliminarRecorrida(idRecorrida);
		} catch (ServiciosException e) {
			// TODO: handle exception
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Error al eliminar Potrero"));
		}
	}
	/*
	public void actualizarPotrero() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			context.addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transacción Existosa", "Potrero modificado correctamente"));
			potreroBean.actualizar();
		} catch (Exception e) {
			// TODO: handle exception
			context.addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Error al modificar el Predio"));
		}
	}
	*/
	public List<Recorrida> getRecorridas(){
		try {
			recorridas = recorridaBean.obtenerTodosRecorridas();
		} catch (ServiciosException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return recorridas;
	}
	
	public long[] getRecorridasL() throws ServiciosException{
		recorridasL = recorridaBean.obtenerTodosRecorridas();
		long[] arrayidRecorridas = new long[recorridasL.size()];
		i=0;
		for(Recorrida r : recorridasL) {
			arrayidRecorridas[i] = r.getIdRecorrida();
			i++;
		}
		return arrayidRecorridas;
	}
	
	public void onRowEdit(RowEditEvent<Recorrida> event) {
		 
		 rec.setNumero(Long.valueOf(event.getObject().getNumero()));
		 
		 try {
			recorridaBean.actualizar(Long.valueOf(event.getObject().getIdRecorrida()),numero);
		} catch (ServiciosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }

	    public void onRowCancel(RowEditEvent<Recorrida> event) {
	    	FacesMessage msg = new FacesMessage("Edición Cancelada", String.valueOf(event.getObject().getNumero()));
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	    }		
	
}
	
    