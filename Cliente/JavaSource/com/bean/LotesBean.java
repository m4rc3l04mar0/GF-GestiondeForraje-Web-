package com.bean;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.PersistenceException;

import org.primefaces.event.RowEditEvent;

import com.entities.Lote;
import com.entities.Potrero;
import com.entities.Predio;
import com.entities.Usuario;
import com.exception.ServiciosException;
import com.servicios.LoteBean;
import com.servicios.PotreroBean;
import com.servicios.PredioBean;


/**
 * Session Bean implementation class LotesBean
 */
@Named("lote")
@SessionScoped
public class LotesBean implements Serializable {
	

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Default constructor. 
     */
private static LotesBean repository = new LotesBean();
	
	private Lote lots = new Lote();
	
	public static LotesBean getInstance(){
		return repository;
	}
	
	
	
	@EJB
	private LoteBean loteBean;
	@EJB
	private PredioBean prediosBean;
	
	private long idLote;
	private String nombre;
	private Date fecha;
	private long nAnimales;
	private BigDecimal consumoForrajeAnimal;
	private String observaciones;
	private long idpredio;
	
	
	private List<Lote> lotesL;
	private List<Lote> lotes;
	
	
	private List<Predio> prediosL;
	
	
	private int i;

	public LoteBean getLoteBean() {
		return loteBean;
	}
	public void setLoteBean(LoteBean loteBean) {
		this.loteBean = loteBean;
	}
	public long getIdLote() {
		return idLote;
	}
	public void setIdLote(long idLote) {
		this.idLote = idLote;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public long getnAnimales() {
		return nAnimales;
	}
	public void setnAnimales(long nAnimales) {
		this.nAnimales = nAnimales;
	}
	public BigDecimal getConsumoForrajeAnimal() {
		return consumoForrajeAnimal;
	}
	public void setConsumoForrajeAnimal(BigDecimal consumoForrajeAnimal) {
		this.consumoForrajeAnimal = consumoForrajeAnimal;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	public long[] getPrediosL() throws ServiciosException{
		prediosL = prediosBean.obtenerTodosPredios();
		long[] arrayidPredios = new long[prediosL.size()];
		i=0;
		for(Predio p : prediosL) {
			arrayidPredios[i]=p.getIdPredio();
			i++;
		}
		return arrayidPredios;
	}
	
public long getIdpredio() {
		return idpredio;
	}
	public void setIdpredio(long idpredio) {
		this.idpredio = idpredio;
	}
public String ingresarLote() throws ServiciosException{
		
		Lote lot = new Lote(0L, nombre, fecha, nAnimales, consumoForrajeAnimal, observaciones, idpredio);
		try {       
			loteBean.ingresarLote(lot);
		} catch (PersistenceException e) {
			// TODO: handle exception
			throw new ServiciosException("Error al agregar un Lote");
		}
		return "mostrar";
	}
	
	
	public void eliminarLote() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			context.addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Potrero eliminado correctamente"));
			loteBean.eliminarLote(idLote);
		} catch (ServiciosException e) {
			// TODO: handle exception
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Error al eliminar Lote"));
		}
	}
	
	public void actualizarLote() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			context.addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transacción Existosa", "Lote modificado correctamente"));
			loteBean.actualizar(idLote,nombre);
		} catch (Exception e) {
			// TODO: handle exception
			context.addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Error al modificar el Lote"));
		}
	}
	
	public List<Lote> getLotes(){
		try {
			lotes = loteBean.obtenerTodosLotes();
		} catch (ServiciosException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return lotes;
	}
	
	public long[] getLotesL() throws ServiciosException{
		lotesL = loteBean.obtenerTodosLotes();
		long[] arrayidLotes = new long[lotesL.size()];
		i=0;
		for(Lote l : lotesL) {
			arrayidLotes[i] = l.getIdLote();
			i++;
		}
		return arrayidLotes;
	}
	
	
	public void onRowEdit(RowEditEvent<Potrero> event) {
		 
		 lots.setNombre(String.valueOf(event.getObject().getNombre()));
		 
		 try {
			loteBean.actualizar(Long.valueOf(event.getObject().getIdPotrero()),nombre);
		} catch (ServiciosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }

	    public void onRowCancel(RowEditEvent<Lote> event) {
	    	FacesMessage msg = new FacesMessage("Edición Cancelada", String.valueOf(event.getObject().getNombre()));
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	    }		
	
}
		
	

