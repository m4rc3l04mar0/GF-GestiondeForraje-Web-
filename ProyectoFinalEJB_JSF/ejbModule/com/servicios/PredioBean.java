package com.servicios;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Predio;
import com.entities.Usuario;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class PredioBean
 */
@Stateless
@LocalBean
public class PredioBean {

	@PersistenceContext
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public PredioBean() {
        // TODO Auto-generated constructor stub
    }
    
    
    public void ingresarPredio(Predio predio) throws ServiciosException{
    	try {
			em.persist(predio);
			em.flush();
		} catch (PersistenceException e) {
			throw new ServiciosException("Ha ocurrido un error al dar de alta un Predio");
		}
    }
    
    public List<Predio> obtenerTodosPredios() throws ServiciosException{
    	List<Predio> predio = null;
    	try {
			TypedQuery<Predio> query = em.createQuery("SELECT d FROM Predio d ORDER BY d.idPredio",Predio.class);
			predio = query.getResultList();
		} catch (PersistenceException e) {
			throw new ServiciosException("Ha ocurrido un error al obtener todos los Predios");
		}
    	return predio;
    }
    
    
    public void editarPredio(Predio predio) throws ServiciosException{
    	try {
			em.merge(predio);
			em.flush();
		} catch (PersistenceException e) {
			throw new ServiciosException("No se pudo actualizar el Predio");
		}
    }
    
    public List<Predio> obtenerNombrePredios(String nombre) throws ServiciosException{
    	List<Predio> nombres = null;
    	try {
			if(!nombre.isEmpty()) {
				TypedQuery<Predio> query = em.createQuery("SELECT d FROM Predio d WHERE d.predio", Predio.class);
				nombres = query.getResultList();
			}
		} catch (PersistenceException e) {
			throw new ServiciosException("Error al obtener nombre de Lotes");
		}
    	return nombres;
    }
    
    
   public void eliminarPredio(Long id) throws ServiciosException{
	   	try {
			Predio predio = em.find(Predio.class, id);
			em.remove(predio);
			em.flush();
		} catch (PersistenceException e) {
			throw new ServiciosException("Ha ocurrido un error al borra un Predio");
		}
   }
   
   
   public String actualizar(long id_predio, String nombre) throws ServiciosException {
   	try{
      		Predio predioModifi = em.find(Predio.class, id_predio);
      		predioModifi.setNombre(predioModifi.getNombre());
   		predioModifi.setAreaHa(predioModifi.getAreaHa());
   		predioModifi.setObservaciones(predioModifi.getObservaciones());
   		em.merge(predioModifi);
   		em.flush();
   		return "actualizado";
   	} catch (PersistenceException e) {
   		throw new ServiciosException("Error al actualizar el Predio ");
   	}
   }
   }