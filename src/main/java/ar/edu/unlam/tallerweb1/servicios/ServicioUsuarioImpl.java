package ar.edu.unlam.tallerweb1.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Imagen;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;

@Service("servicioUsuario")
@Transactional
public class ServicioUsuarioImpl implements ServicioUsuario {

	private RepositorioUsuario servicioUsuarioDao;

	@Autowired
	public ServicioUsuarioImpl(RepositorioUsuario servicioUsuarioDao){
		this.servicioUsuarioDao = servicioUsuarioDao;
	}

	@Override
	public Imagen actualizarFotoDePerfil(Usuario usuario, Imagen foto) {
		return this.servicioUsuarioDao.actualizarFotoDePerfil(usuario, foto);
	}
}
