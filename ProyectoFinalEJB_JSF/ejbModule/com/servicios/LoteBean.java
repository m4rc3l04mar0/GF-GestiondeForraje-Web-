package com.servicios;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Lote;
import com.entities.Usuario;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class LoteBean
 */
@Stateless
@LocalBean
public class LoteBean {

	@PersistenceContext
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public LoteBean() {
        // TODO Auto-generated constructor stub
    }
    
    
    public void ingresarLote(Lote lote) throws ServiciosException{
    	try {
			em.persist(lote);
			em.flush();
		} catch (PersistenceException e) {
			throw new ServiciosException("Ha ocurrido un error al dar de alta un lote");
		}
    }
    
    public List<Lote> obtenerTodosLotes() throws ServiciosException{
    	List<Lote> lote = null;
    	try {
			TypedQuery<Lote> query = em.createQuery("SELECT l FROM Lote l ORDER BY l.idLote",Lote.class);
			lote = query.getResultList();
		} catch (PersistenceException e) {
			throw new ServiciosException("Ha ocurrido un error al obtener todos los Lotes");
		}
    	return lote;
    }
    
    
    public void editarLote(Lote lote) throws ServiciosException{
    	try {
			em.merge(lote);
			em.flush();
		} catch (PersistenceException e) {
			throw new ServiciosException("No se pudo actualizar el lote");
		}
    }
    
    public List<Lote> obtenerNombreLotes(String nombre) throws ServiciosException{
    	List<Lote> nombres = null;
    	try {
			if(!nombre.isEmpty()) {
				TypedQuery<Lote> query = em.createQuery("SELECT l FROM Lote l WHERE l.nombre", Lote.class);
				nombres = query.getResultList();
			}
		} catch (PersistenceException e) {
			throw new ServiciosException("Error al obtener nombre de Lotes");
		}
    	return nombres;
    }
    
    
   public void eliminarLote(Long id) throws ServiciosException{
	   	try {
			Lote lote = em.find(Lote.class, id);
			em.remove(lote);
			em.flush();
		} catch (PersistenceException e) {
			throw new ServiciosException("Ha ocurrido un error al borra un Lote");
		}
   }
   
   public String actualizar(long id_lote, String nombre) throws ServiciosException {
   	try{
      		Lote loteModifi = em.find(Lote.class, id_lote);
      		loteModifi.setNombre(loteModifi.getNombre());
   		loteModifi.setFecha(loteModifi.getFecha());
   		loteModifi.setNAnimales(loteModifi.getNAnimales());
   		loteModifi.setConsumoForrajeAnimal(loteModifi.getConsumoForrajeAnimal());
   		loteModifi.setIdPredio(loteModifi.getIdPredio());
   		loteModifi.setObservaciones(loteModifi.getObservaciones());  		
   		em.merge(loteModifi);
   		em.flush();
   		return "actualizado";
   	} catch (PersistenceException e) {
   		throw new ServiciosException("Error al actualizar el Lote ");
   	}
   }
   
   
   
   
   }
