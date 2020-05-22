package ar.edu.unlam.tallerweb1.servicios;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

// Implementacion del Servicio de usuarios, la anotacion @Service indica a Spring que esta clase es un componente que debe
// ser manejado por el framework, debe indicarse en applicationContext que busque en el paquete ar.edu.unlam.tallerweb1.servicios
// para encontrar esta clase.
// La anotacion @Transactional indica que se debe iniciar una transaccion de base de datos ante la invocacion de cada metodo del servicio,
// dicha transaccion esta asociada al transaction manager definido en el archivo spring-servlet.xml y el mismo asociado al session factory definido
// en hibernateCOntext.xml. De esta manera todos los metodos de cualquier dao invocados dentro de un servicio se ejecutan en la misma transaccion
@Service("servicioLogin")
@Transactional
<<<<<<< HEAD
public class ServicioLoginImpl implements ServicioLogin {
	
	static final String ID_USUARIO = "ID_USUARIO";
	static final String ROL = "ROL";
	static final String EMAIL = "EMAIL";
=======
public class ServicioLoginImpl implements ServicioLogin{
>>>>>>> ee00fb924b5cbb48e58bfb66c113dc189cb6e523

	private RepositorioUsuario servicioLoginDao;

	@Autowired
	public ServicioLoginImpl(RepositorioUsuario servicioLoginDao){
		this.servicioLoginDao = servicioLoginDao;
	}

	@Override
	public Usuario consultarUsuario (Usuario usuario) {
		return servicioLoginDao.consultarUsuario(usuario);
	}
	
	@Override
	public Usuario obtenerUsuarioConectado(HttpServletRequest request) {
		Long id = (Long) request.getSession().getAttribute(ID_USUARIO);
		if(id != null) {
			return servicioLoginDao.consultarUsuarioPorId(id);
		}
		return null;
	}

	@Override
	public boolean estaLogueado(HttpServletRequest request) {
		if(request.getSession().getAttribute(ID_USUARIO) != null) {
			return true;
		}
		return false;
	}

	@Override
	public void cerrarSesion(HttpServletRequest request) {
		request.getSession().invalidate();		
	}
	
	@Override
	public void iniciarSesion(HttpServletRequest request, Usuario usuario) {
		request.getSession().setAttribute(ID_USUARIO, usuario.getId());
		request.getSession().setAttribute(EMAIL, usuario.getEmail());
		request.getSession().setAttribute(ROL, usuario.getRol());		
	}

}
