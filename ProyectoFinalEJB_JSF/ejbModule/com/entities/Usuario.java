package com.entities;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

import com.enums.Perfil;

/**
 * Entity implementation class for Entity: Usuario
 *
 */
@Entity
@Table(name="USUARIOS")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	   
	@Id
	@SequenceGenerator(name="USUARIOS_IDUSUARIO_GENERATOR", sequenceName="SEQ_ID_USUARIO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USUARIOS_IDUSUARIO_GENERATOR")
	@Column(name="ID_USUARIO")
	private long idUsuario;
	private String nombre;
	private String apellido;
	private String usuario;
	private String correo;
	private String contrase�a;
	
	@Enumerated(EnumType.STRING)
	private Perfil perfil;
	
	
	public Usuario() {
		
	}

    	
		
	  
	public Usuario(long idUsuario, String nombre, String apellido, String usuario, String correo, String contrase�a,
			Perfil perfil) {
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.apellido = apellido;
		this.usuario = usuario;
		this.correo = correo;
		this.contrase�a = contrase�a;
		this.perfil = perfil;
	}




	public long getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}   
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}   
	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}   
	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}   
	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}   
	public String getContrase�a() {
		return this.contrase�a;
	}

	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}   
	public Perfil getPerfil() {
		return this.perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
   
}
