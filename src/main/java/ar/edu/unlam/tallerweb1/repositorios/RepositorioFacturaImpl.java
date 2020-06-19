package ar.edu.unlam.tallerweb1.repositorios;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ar.edu.unlam.tallerweb1.modelo.Factura;

@Repository("repositorioFactura")
public class RepositorioFacturaImpl implements RepositorioFactura {
	private SessionFactory sessionFactory;

    @Autowired
	public RepositorioFacturaImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
    
    @Override
	public void actualizarFactura(Factura factura) {
		final Session session = sessionFactory.getCurrentSession();
		session.update(factura);		
	}

	@Override
	public void anularDescuento(Factura factura) {
		final Session session = sessionFactory.getCurrentSession();
		factura.setDescuento(null);
		session.update(factura);	
	}

	@Override
	public void guardarFactura(Factura factura) {
		final Session session = sessionFactory.getCurrentSession();
		session.save(factura);		
	}

	@Override
	public Factura obtenerFactura(Long idFactura) {
		final Session session = sessionFactory.getCurrentSession();
		return session.get(Factura.class, idFactura);
	}
	
}
