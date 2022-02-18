package com.servicios;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Predio;
import com.entities.Recorrida;
import com.entities.Usuario;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class Recorrida
 */
@Stateless
@LocalBean
public class RecorridaBean {

	@PersistenceContext
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public RecorridaBean() {
        // TODO Auto-generated constructor stub
    }
    
    
    public void ingresarRecorridas(Recorrida recorrida) throws ServiciosException{
    	try {
			em.persist(recorrida);
			em.flush();
		} catch (PersistenceException e) {
			throw new ServiciosException("Ha ocurrido un error al dar de alta un Recorrida");
		}
    }
    
    
    public List<Recorrida> obtenerTodosRecorridas() throws ServiciosException{
    	List<Recorrida> recorrida = null;
    	try {                                             
			TypedQuery<Recorrida> query = em.createQuery("SELECT r FROM Recorrida r ORDER BY r.idRecorrida",Recorrida.class);
			recorrida = query.getResultList();
		} catch (PersistenceException e) {
			throw new ServiciosException("Ha ocurrido un error al obtener todas las Recorridas");
		}
    	return recorrida;
    }
    
    
    public void editarRecorrida(RecorridaBean recorrida) throws ServiciosException{
    	try {
			em.merge(recorrida);
			em.flush();
		} catch (PersistenceException e) {
			throw new ServiciosException("No se pudo actualizar el Recorrida");
		}
    }
    
    public List<RecorridaBean> obtenerNombreRecorridas(String nombre) throws ServiciosException{
    	List<RecorridaBean> nombres = null;
    	try {
			if(!nombre.isEmpty()) {
				TypedQuery<RecorridaBean> query = em.createQuery("SELECT r FROM Recorrida r WHERE r.recorrida", RecorridaBean.class);
				nombres = query.getResultList();
			}
		} catch (PersistenceException e) {
			throw new ServiciosException("Error al obtener nombre de Recorridas");
		}
    	return nombres;
    }
    
    
   public void eliminarRecorrida(Long id) throws ServiciosException{
	   	try {
			RecorridaBean recorrida = em.find(RecorridaBean.class, id);
			em.remove(recorrida);
			em.flush();
		} catch (PersistenceException e) {
			throw new ServiciosException("Ha ocurrido un error al borra una Recorrida");
		}
   }
   
   public String actualizar(long id_recorrida, long numero) throws ServiciosException {
   	try{
      		Recorrida recorridaModifi = em.find(Recorrida.class, id_recorrida);
      		recorridaModifi.setNumero(recorridaModifi.getNumero());
   		recorridaModifi.setFecha(recorridaModifi.getFecha());
   		recorridaModifi.setKgMsHa(recorridaModifi.getKgMsHa());
   		recorridaModifi.setHojas(recorridaModifi.getHojas());
   		recorridaModifi.setNudos(recorridaModifi.getNudos());
   		recorridaModifi.setObservaciones(recorridaModifi.getObservaciones());  		
   		em.merge(recorridaModifi);
   		em.flush();
   		return "actualizado";
   	} catch (PersistenceException e) {
   		throw new ServiciosException("Error al actualizar el Usuario ");
   	}
   }
   
  }
