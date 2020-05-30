package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Producto;
import ar.edu.unlam.tallerweb1.modelo.Tienda;

@Repository("repositorioTienda")
public class RepositorioTiendaImpl implements RepositorioTienda{
	
	private SessionFactory sessionFactory;
	
    @Autowired
    public RepositorioTiendaImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Tienda> listarTiendas() {
		final Session session = sessionFactory.getCurrentSession();
		List<Tienda> tiendas = session.createCriteria(Tienda.class).list();
		return tiendas;
	}

	@Override
	public List<Producto> listarProductosDeTienda(Tienda tienda) {
		final Session session = sessionFactory.getCurrentSession();
		List<Producto> productos = session.createCriteria(Producto.class)
				.add(Restrictions.eq("tienda",tienda)).list();
		return productos;
	}

	@Override
	public Tienda buscarTiendaPorId(Long id) {
		final Session session = sessionFactory.getCurrentSession();
		return session.get(Tienda.class, id);
	}

}
