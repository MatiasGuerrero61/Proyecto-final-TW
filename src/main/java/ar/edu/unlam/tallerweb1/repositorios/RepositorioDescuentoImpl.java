package ar.edu.unlam.tallerweb1.repositorios;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Descuento;

@Repository("repositorioDescuento")
public class RepositorioDescuentoImpl implements RepositorioDescuento {
	
	private SessionFactory sessionFactory;
    @Autowired
	public RepositorioDescuentoImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
    
	@Override
	public Descuento verificarValidez(String codigo) {
		final Session session = sessionFactory.getCurrentSession();
		Descuento descuento = (Descuento) session.createCriteria(Descuento.class)
				.add(Restrictions.like("codigo", codigo))
				.add(Restrictions.eq("validez", true))
				.uniqueResult();
		return descuento;
	}

	@Override
	public void invalidarDescuento(Descuento descuento) {
		final Session session = sessionFactory.getCurrentSession();
		descuento.setValidez(false);
		session.update(descuento);		
	}

	@Override
	public void reactivarDescuento(Descuento descuento) {
		final Session session = sessionFactory.getCurrentSession();
		descuento.setValidez(true);
		session.update(descuento);			
	}
		
}
