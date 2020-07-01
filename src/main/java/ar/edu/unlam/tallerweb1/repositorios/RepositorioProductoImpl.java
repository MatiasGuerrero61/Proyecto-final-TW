package ar.edu.unlam.tallerweb1.repositorios;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Producto;

@Repository("repositorioProducto")
public class RepositorioProductoImpl implements RepositorioProducto {
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public RepositorioProductoImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void actualizarProducto(Producto producto) {
		final Session session = sessionFactory.getCurrentSession();
		session.update(producto);
	}

}
