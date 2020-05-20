package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.PostMascotaPerdida;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPostMascotaPerdida;
@Service("ServicioPost")
@Transactional
public class ServicioPostImp implements ServicioPost {

	@Inject
	private SessionFactory sessionFactory;
	
	private RepositorioPostMascotaPerdida servicioListaDao;
	
	@Autowired
	public ServicioPostImp(RepositorioPostMascotaPerdida servicioListaDao) {
		this.servicioListaDao = servicioListaDao;
	}
	
	@Override
	public void guardarPost(PostMascotaPerdida post) {
		Session session = sessionFactory.getCurrentSession();
		session.save(post);
	}

	@Override
	public List<PostMascotaPerdida> obtenerListaPerdida() {
		return servicioListaDao.getListaMascotasPerdidas();
	}

}