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

import com.entities.Usuario;
import com.enums.Perfil;
import com.exception.ServiciosException;
import com.servicios.UsuarioBean;






@Named("usuario")
@SessionScoped
public class UsuariosBean implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

	private static UsuariosBean repository = new UsuariosBean();
	
	private Usuario usu = new Usuario();
	
	public static UsuariosBean getInstance(){
		return repository;
	}
	
	
	@EJB
	private UsuarioBean usuarioBean;
	
	private long id;
    private String nombre;
	private String apellido;
	private String usuario;
	private String correo;
	private String password;
	private Perfil perfil;
	
	
	private List<Usuario> usuariosL;
	private List<Usuario> usuarios;
	private Usuario usuarioPorId;
	
	
	private int i;
	
	
	public UsuarioBean getUsuarioBean() {
		return usuarioBean;
	}



	public void setUsuarioBean(UsuarioBean usuarioBean) {
		this.usuarioBean = usuarioBean;
	}


	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getApellido() {
		return apellido;
	}



	public void setApellido(String apellido) {
		this.apellido = apellido;
	}



	public String getUsuario() {
		return usuario;
	}



	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
    
	
	public String getCorreo() {
		return correo;
	}



	public void setCorreo(String correo) {
		this.correo = correo;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}

    
	


	public Perfil getPerfil() {
		return perfil;
	}



	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	

	public Perfil[] getPerfiles(){
		return Perfil.values();
	}

	
	

	public String ingresarUsuario() throws ServiciosException{
         
		usuario=generarUsuario(nombre,apellido);
		
		Usuario usu = new Usuario(0L, nombre,apellido,usuario,correo,password,perfil);
			try {
				usuarioBean.ingresarUsuario(usu);
				
			} catch (PersistenceException e) {
				// TODO Auto-generated catch block
				throw new ServiciosException("Error al agregar un Usuario");
			}
			return "mostrar";
			
	}
	
	public String generarUsuario(String nombre, String apellido) {
		String usu="";
		
		
			usu=nombre+"."+apellido;
			
			List<Usuario> usuarios;
			try {
				usuarios = usuarioBean.obtenerTodosUsuarios();
				for(int i=0; i<usuarios.size();i++) {
					if(usuarios.get(i).getUsuario().equals(nombre+"."+apellido)) {
						
					}else if(usuarios.get(i).getUsuario().equals(nombre+"."+apellido)) {
						System.out.println("El usuario ya existe, cambie de usuario");
						usuario="";
						
					}
					
				}
			} catch (ServiciosException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		
		return usu;
	}
	 public List<Usuario> getUsuarios() {
		  try {
			usuarios = usuarioBean.obtenerTodosUsuarios();
		} catch (ServiciosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return usuarios;
	  }
	 
	 
	 
	 public void eliminarUsuario() {
		 FacesContext context = FacesContext.getCurrentInstance();
		 try {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Usuario eliminado correctamente"));
			usuarioBean.eliminarUsuario(id);
			
		} catch (ServiciosException e) {
			// TODO: handle exception
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Error al eliminar usuario"));
		}

	 
		 }
	 
	 public void actualizarUsuario() {
			FacesContext context = FacesContext.getCurrentInstance();
			try {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Transaccion exitosa", "Usuario modificado correctamente") );
						
				usuarioBean.actualizar(id,password);	
			} catch (ServiciosException e) {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Error al modificar el  usuario"));
			}
		}
		
	 
	 public long[] getUsuariosL() throws ServiciosException{
			usuariosL = usuarioBean.obtenerTodosUsuarios();
			long[] arrayidUsuarios = new long[usuariosL.size()];
			i=0;
			for(Usuario u : usuariosL){
				arrayidUsuarios[i] = u.getIdUsuario();
				i++;
			}
			return arrayidUsuarios;     	
		}

	 public void cargaUsuarioPorId(long idUsuario) throws ServiciosException {
			usuarioPorId = usuarioBean.buscarUsuarioPorId(idUsuario);
		}

		public Usuario getUsuarioPorId() {
			return usuarioPorId;
		}

		public void setUsuarioPorId(Usuario usuarioPorId)  {
			this.usuarioPorId = usuarioPorId;
		}
		
		
		 public void onRowEdit(RowEditEvent<Usuario> event) {
			 
			 usu.setContraseña(String.valueOf(event.getObject().getContraseña()));
			 
			 try {
				usuarioBean.actualizar(Long.valueOf(event.getObject().getIdUsuario()),password);
			} catch (ServiciosException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   }

		    public void onRowCancel(RowEditEvent<Usuario> event) {
		    	FacesMessage msg = new FacesMessage("Edición Cancelada", String.valueOf(event.getObject().getUsuario()));
		        FacesContext.getCurrentInstance().addMessage(null, msg);
		    }		
		
		public String consultaUsuario() throws ServiciosException {
			String cadena="";
			
			
			if(usuario.equals("administrador")&&password.equals("admin12345")) {
				password="";
				return "/Usuarios/NuevoUsuario";
				
			}
			
			cadena=usuarioBean.obtenerUsuarios(usuario, password);
			if ( cadena == "" ){
				this.usuario="";
				return "errorLogin";
			}
			
			if(cadena.equals("ENCARGADO"))
			{
				perfil=Perfil.valueOf("ENCARGADO");
				nombre=usuario;
				password="";
				return "/Alimentos/NuevoUsuario";
			}else
		
			{
				perfil=Perfil.valueOf("PERSONAL");
				nombre=usuario;
				password="";
				return "/Alimentos/NuevoUsuario";
			}
			
			
		}
}


