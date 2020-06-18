package ar.edu.unlam.tallerweb1.repositorios;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Carrito;
import ar.edu.unlam.tallerweb1.modelo.EnumEstadoDeCompra;
import ar.edu.unlam.tallerweb1.modelo.Tienda;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Repository("repositorioCarrito")
public class RepositorioCarritoImpl implements RepositorioCarrito{
	
	private SessionFactory sessionFactory;

    @Autowired
	public RepositorioCarritoImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Carrito buscarCarritoPendiente(Usuario usuario, Tienda tienda) {
		final Session session = sessionFactory.getCurrentSession();
        Carrito carrito = (Carrito) session.createCriteria(Carrito.class)
        		.add(Restrictions.eq("tienda", tienda))
        		.add(Restrictions.eq("usuario", usuario))
        		.add(Restrictions.eq("estado", EnumEstadoDeCompra.PENDIENTE_DE_COMPRA))
        		.uniqueResult();
        		return carrito;
	}

	@Override
	public Carrito crearCarrito(Usuario usuario, Tienda tienda) {
		Carrito carrito = new Carrito();
		carrito.setEstado(EnumEstadoDeCompra.PENDIENTE_DE_COMPRA);
		carrito.setUsuario(usuario);
		carrito.setTienda(tienda);
		final Session session = sessionFactory.getCurrentSession();
		session.save(carrito);
		return carrito;
	}

	@Override
	public Carrito obtenerCarrito(Long idCarrito) {
		final Session session = sessionFactory.getCurrentSession();
		return session.get(Carrito.class, idCarrito);
	}

}
