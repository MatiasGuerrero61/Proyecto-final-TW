package ar.edu.unlam.tallerweb1.repositorios;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
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

	@Override
	public List<Producto> filtrarProductos(HashMap<String, String> filtros, Tienda tienda) {
		final Session ssion = sessionFactory.getCurrentSession();
		Criteria criteria = ssion.createCriteria(Producto.class).add(Restrictions.eq("tienda",tienda));
		
		String filtroNombre = filtros.get("nombre");
		String filtroImporteMin = filtros.get("importeMin");
		String filtroImporteMax = filtros.get("importeMax");
		String filtroOrden = filtros.get("orden");
		
		if(filtroNombre != null) {
			criteria.add(Restrictions.like("nombre",filtroNombre+"%"));
		}if(filtroImporteMax != null) {
			BigDecimal max = new BigDecimal(filtroImporteMax);
			criteria.add(Restrictions.ge("importe", max));
		}if(filtroImporteMin != null) {
			BigDecimal min = new BigDecimal(filtroImporteMin);
			criteria.add(Restrictions.ge("importe", min));
		}if(filtroOrden == "asc") {
			criteria.addOrder(Order.asc("importe"));
		}else if(filtroOrden == "desc") {
			criteria.addOrder(Order.desc("importe"));
		}
		List<Producto> productosFiltrados = criteria.list();
		
		return productosFiltrados;
	}
	
	
}
