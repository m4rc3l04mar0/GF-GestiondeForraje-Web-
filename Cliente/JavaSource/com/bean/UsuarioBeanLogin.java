package com.bean;


import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;

import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.ejb.UsuariosEJBBean;
import com.entities.Usuario;



@Named("usuarioLogin")
@SessionScoped

public class UsuarioBeanLogin implements Serializable {
	private static final long serialVersionUID = 1L;
private Usuario currentUser = new Usuario();
	
	@EJB
	private UsuariosEJBBean usuariosEJBBean;
	
	
	private long Id;
	private String usuario;
	private String password;
	
	
	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getNombre() {
		return usuario;
	}

	public void setNombre(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setCurrentUser(Usuario currentUser) {
		this.currentUser=currentUser;
	}
	
	public Usuario getCurrentUser() {
		return currentUser;
	}
	
	public String login() {
		Usuario result = usuariosEJBBean.login(currentUser.getUsuario(),currentUser.getContraseña());
		if(result!=null) {
			currentUser=result;
			return "nuevoUsuario";
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("username and password not found"));
		return null;
	}

}
