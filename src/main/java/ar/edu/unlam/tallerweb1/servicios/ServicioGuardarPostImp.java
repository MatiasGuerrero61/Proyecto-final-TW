package ar.edu.unlam.tallerweb1.servicios;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.hibernate.SessionFactory;

import ar.edu.unlam.tallerweb1.modelo.PostMascotaPerdida;
@Service("ServicioGuardarPost")
@Transactional
public class ServicioGuardarPostImp implements ServicioGuardarPost {

	@Inject
	private SessionFactory sessionFactory;
	
	@Override
	public void guardarPost(PostMascotaPerdida post) {
		Session session = sessionFactory.getCurrentSession();
		session.save(post);
	}

}