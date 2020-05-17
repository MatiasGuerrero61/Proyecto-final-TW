package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.unlam.tallerweb1.modelo.PostMascotaPerdida;

public class RepositorioPostMascotaPerdidaImpl implements RepositorioPostMascotaPerdida{
	
	private SessionFactory sessionFactory;

    @Autowired
	public RepositorioPostMascotaPerdidaImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public List<PostMascotaPerdida> getListaMascotasPerdidas() {
		final Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(PostMascotaPerdida.class);
		List<PostMascotaPerdida> posts = criteria.list();
		return posts;
	}
	
	
}
