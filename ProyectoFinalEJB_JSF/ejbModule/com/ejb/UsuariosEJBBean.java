package com.ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.entities.Usuario;

/**
 * Session Bean implementation class UsuariosEJBBean
 */
@Stateless
@LocalBean
public class UsuariosEJBBean {
	@PersistenceContext
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public UsuariosEJBBean() {
        // TODO Auto-generated constructor stub
    }
    
    public Usuario login(String usuario, String contraseña) {
    	Query query = em.createQuery("select u from Usuario u where u.usuario = :usuario and u.contraseña = :contraseña");
    	query.setParameter("usuario", usuario);
    	query.setParameter("contraseña", contraseña);
    	
    	List<Usuario> result = query.getResultList();
    	
    	if(result != null && result.size() >0) {
    		return result.get(0);
    	}
    	return null;
    		
    			
    	
    }

}
