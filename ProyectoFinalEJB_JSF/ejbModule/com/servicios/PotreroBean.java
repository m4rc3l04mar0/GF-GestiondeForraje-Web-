package com.servicios;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Potrero;
import com.entities.Usuario;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class PotreroBean
 */
@Stateless
@LocalBean
public class PotreroBean {

	@PersistenceContext
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public PotreroBean() {
        // TODO Auto-generated constructor stub
    }
    
    
    public void ingresarPotrero(Potrero potrero) throws ServiciosException{
    	try {
			em.persist(potrero);
			em.flush();
		} catch (PersistenceException e) {
			throw new ServiciosException("Ha ocurrido un error al dar de alta un Potrero");
		}
    }
    
    public List<Potrero> obtenerTodosPotreros() throws ServiciosException{
    	List<Potrero> potrero = null;
    	try {
			TypedQuery<Potrero> query = em.createQuery("SELECT t FROM Potrero t ORDER BY t.idPotrero",Potrero.class);
			potrero = query.getResultList();
		} catch (PersistenceException e) {
			throw new ServiciosException("Ha ocurrido un error al obtener todos los Potreros");
		}
    	return potrero;
    }
    
    
    public void editarPotrero(Potrero potrero) throws ServiciosException{
    	try {
			em.merge(potrero);
			em.flush();
		} catch (PersistenceException e) {
			throw new ServiciosException("No se pudo actualizar el Potrero");
		}
    }
    
    public String actualizar(long id_potrero, String nombre) throws ServiciosException {
    	try{
       		Potrero potreroModifi = em.find(Potrero.class, id_potrero);
       		potreroModifi.setNombre(potreroModifi.getNombre());
    		potreroModifi.setAreaHa(potreroModifi.getAreaHa());
    		potreroModifi.setObservaciones(potreroModifi.getObservaciones());
    		potreroModifi.setIdPredio(potreroModifi.getIdPotrero());
    		potreroModifi.setEstado(potreroModifi.getEstado());
    		potreroModifi.setPastura(potreroModifi.getPastura());  		
    		em.merge(potreroModifi);
    		em.flush();
    		return "actualizado";
    	} catch (PersistenceException e) {
    		throw new ServiciosException("Error al actualizar el Potrero ");
    	}
    }
    
    public List<Potrero> obtenerNombrePotreros(String nombre) throws ServiciosException{
    	List<Potrero> nombres = null;
    	try {
			if(!nombre.isEmpty()) {
				TypedQuery<Potrero> query = em.createQuery("SELECT t FROM Potrero t WHERE t.potrero", Potrero.class);
				nombres = query.getResultList();
			}
		} catch (PersistenceException e) {
			throw new ServiciosException("Error al obtener nombre de Potreros");
		}
    	return nombres;
    }
    
    
   public void eliminarPotrero(Long id) throws ServiciosException{
	   	try {
			Potrero potrero = em.find(Potrero.class, id);
			em.remove(potrero);
			em.flush();
		} catch (PersistenceException e) {
			throw new ServiciosException("Ha ocurrido un error al borra un Potrero");
		}
   }
   }