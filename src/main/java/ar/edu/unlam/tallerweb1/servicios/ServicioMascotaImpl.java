package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.excepciones.UsuarioNotFoundException;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Mascota;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioMascota;

@Service("servicioMascota")
@Transactional
public class ServicioMascotaImpl implements ServicioMascota{
	
	private RepositorioMascota servicioMascotaDao;
	private RepositorioUsuario servicioUsuarioDao;
	
	@Autowired
	public ServicioMascotaImpl(RepositorioMascota repoMascota, RepositorioUsuario repositorioUsuario) {
		this.servicioMascotaDao = repoMascota;
		this.servicioUsuarioDao = repositorioUsuario;
	}
	@Override
	public List<Mascota> getListaMascota() {
		return this.servicioMascotaDao.getMascotas();
	}
	@Override
	public Mascota getMascotaById(Long id) {
		return this.servicioMascotaDao.getMascotaById(id);
	}

	@Override
	public List<Mascota> getListaMascotaDeUsuario(Usuario usuario){
			Usuario user = servicioUsuarioDao.consultarUsuarioPorId(usuario.getId());
			if(user == null){
				throw new UsuarioNotFoundException("El usuario no fue encontrado");
			}
		return servicioMascotaDao.getMascotasDeUsuario(user);
	}

}
