package ar.edu.unlam.tallerweb1.repositorios;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.hibernate.transform.DistinctRootEntityResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Filtro;
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

	@Override
	public List<Producto> filtrarProductos(Filtro filtros, Tienda tienda) {
		final Session ssion = sessionFactory.getCurrentSession();
		Criteria criteria = ssion.createCriteria(Producto.class).add(Restrictions.eq("tienda",tienda));
		
		String filtroNombre = filtros.getNombre();
		String filtroImporteMin = filtros.getPrecioMin();
		String filtroImporteMax = filtros.getPrecioMax();
		String filtroOrden = filtros.getOrder();
		String filtroCategoria = filtros.getCategoria();
		
		if(filtroNombre != null) {
			criteria.add(Restrictions.like("nombre","%"+filtroNombre+"%"));
		}if(filtroCategoria != null && !filtroCategoria.equals("todas")) {
			criteria.add(Restrictions.like("categoria", filtroCategoria));
		}if(filtroImporteMax != null) {
			BigDecimal max = new BigDecimal(filtroImporteMax);
			criteria.add(Restrictions.le("importe", max));
		}if(filtroImporteMin != null) {
			BigDecimal min = new BigDecimal(filtroImporteMin);
			criteria.add(Restrictions.ge("importe", min));
		}if(filtroOrden != null && filtroOrden.equals("asc")) {
			criteria.addOrder(Order.asc("importe"));
		}else if(filtroOrden != null && filtroOrden.equals("desc")) {
			criteria.addOrder(Order.desc("importe"));
		}
		List<Producto> productosFiltrados = criteria.list();
		
		return productosFiltrados;
	}

	@Override
	public List<Producto> listarCategorias(Tienda tienda) {
		final Session ssion = sessionFactory.getCurrentSession();
		Criteria criteria = ssion.createCriteria(Producto.class).add(Restrictions.eq("tienda",tienda));
		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.distinct(Projections.property("categoria")));
		criteria.setProjection(projList);
		List<Producto> productos = criteria.list();

		return productos;
	}

	@Override
	public Producto obtenerProducto(long id) {
		final Session session = sessionFactory.getCurrentSession();
		return session.get(Producto.class, id);
	}
	
	
}
