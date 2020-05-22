package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Anuncio;

@Repository("repositorioAnuncio")
public class RepositorioAnuncioImpl implements RepositorioAnuncio{

	private SessionFactory sessionFactory;

    @Autowired
	public RepositorioAnuncioImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void guardar(Anuncio anuncio) {
		Session session = sessionFactory.getCurrentSession();
		session.save(anuncio);
	}

	@Override
	public Anuncio obtenerAnuncioPorId(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Anuncio> obtenerAnuncios() {
		final Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Anuncio.class);
		List<Anuncio> anuncios = criteria.list();
		return anuncios;
	}

}
