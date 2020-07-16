package ar.edu.unlam.tallerweb1.repositorios;

import java.time.LocalDateTime;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ar.edu.unlam.tallerweb1.modelo.EnumEstadoDeCompra;
import ar.edu.unlam.tallerweb1.modelo.Factura;
import ar.edu.unlam.tallerweb1.modelo.Facturacion;

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
	public Factura buscarFacturaSinConfirmar(Long idCarrito) {
		final Session session = sessionFactory.getCurrentSession();
		Factura factura = (Factura) session.createCriteria(Factura.class)
				.add(Restrictions.eq("carrito.id",idCarrito))
				.add(Restrictions.eq("estado",EnumEstadoDeCompra.SIN_CONFIRMAR))
				.uniqueResult();
		return factura;
	}
	
	@Override
	public Factura obtenerFactura(Long idFactura) {
		final Session session = sessionFactory.getCurrentSession();
		return session.get(Factura.class, idFactura);
	}

	@Override
	public Factura obtenerFacturaPorIdMercadoPago(String preferenceId) {
		final Session session = sessionFactory.getCurrentSession();
		Factura factura = (Factura) session.createCriteria(Factura.class)
				.add(Restrictions.eq("idMercadoPago",preferenceId))
				.uniqueResult();
		return factura;
	}

	@Override
	public List<Facturacion> obtenerFacturacion(LocalDateTime fechaDesde, LocalDateTime fechaHasta) {
		final Session session = sessionFactory.getCurrentSession();
		List<Facturacion> facturacion = session.createCriteria(Factura.class)
				.createAlias("carrito", "carrito")
				.createAlias("carrito.tienda", "tienda")
				.add(Restrictions.ge("fechaYHoraDeCompra",fechaDesde))
				.add(Restrictions.le("fechaYHoraDeCompra",fechaHasta))
				.add(Restrictions.eq("estado",EnumEstadoDeCompra.COMPRA_FINALIZADA))
				.setProjection(Projections.projectionList()
						.add(Projections.rowCount(), "cantidadDeVentas")
						.add(Projections.sum("importeFinal"),"importeTotal")
						.add(Projections.property("tienda.razonSocial"),"nombreDeTienda")
						.add(Projections.groupProperty("tienda.id"),"idTienda"))
				.setResultTransformer(Transformers.aliasToBean(Facturacion.class))
				.list();
		return facturacion;
	}	
}
