package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.PostMascotaPerdida;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioMascotaPerdida;
@Service("ServicioPost")
@Transactional
public class ServicioPostImp implements ServicioPost {
	
	private RepositorioMascotaPerdida servicioListaDao;
	
	@Autowired
	public ServicioPostImp(RepositorioMascotaPerdida servicioListaDao) {
		this.servicioListaDao = servicioListaDao;
	}
	
	@Override
	public void guardarPost(PostMascotaPerdida post) {
		servicioListaDao.saveMascotasPerdidas(post);
	}

	@Override
	public List<PostMascotaPerdida> obtenerListaPerdida() {
		return servicioListaDao.getListaMascotasPerdidas();
	}

}