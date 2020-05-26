package ar.edu.unlam.tallerweb1.servicios;

import javax.servlet.http.HttpServletRequest;

import ar.edu.unlam.tallerweb1.modelo.Imagen;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

import javax.servlet.http.HttpServletRequest;

//Interface que define los metodos del Servicio de Usuarios.
public interface ServicioLogin {

	Usuario consultarUsuario(Usuario usuario);
	Usuario obtenerUsuarioConectado(HttpServletRequest request);
	boolean estaLogueado(HttpServletRequest request);
	void cerrarSesion(HttpServletRequest request);
	void iniciarSesion(HttpServletRequest request, Usuario usuario);
	void actualizarFotoDeUsuarioConectado(HttpServletRequest request,Imagen foto);

}

