package ar.edu.unlam.tallerweb1.repositorios;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Carrito;
import ar.edu.unlam.tallerweb1.modelo.Item;
import ar.edu.unlam.tallerweb1.modelo.Producto;

@Repository("repositorioItem")
public class RepositorioItemImpl implements RepositorioItem {
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public RepositorioItemImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public Item buscarItem(Carrito carrito, Producto producto) {
		final Session session = sessionFactory.getCurrentSession();
		Item item = (Item) session.createCriteria(Item.class)
				.add(Restrictions.eq("carrito", carrito))
				.add(Restrictions.eq("producto", producto))
				.uniqueResult();
		return item;
	}

	@Override
	public Item crearItem(Carrito carrito, Producto producto) {
		Item item = new Item();
		item.setCantidad(0);
		item.setCarrito(carrito);
		item.setProducto(producto);
		final Session session = sessionFactory.getCurrentSession();
		session.save(item);
		return item;
	}

	@Override
	public void cargarItem(Carrito carrito, Producto producto, Integer cantidad) {
		Item item = this.buscarItem(carrito, producto);
		if(item == null) {
			item = this.crearItem(carrito, producto);
		}
		item.setCantidad(item.getCantidad() + cantidad);
		final Session session = sessionFactory.getCurrentSession();
		session.update(item);
		return;
	}

	@Override
	public void vaciarItems(Long idCarrito) {
		final Session session = sessionFactory.getCurrentSession();
		List<Item> items = (ArrayList<Item>) session.createCriteria(Item.class)
				.add(Restrictions.eq("carrito.id", idCarrito))
				.add(Restrictions.gt("cantidad", 0))
				.list();
		for(Item item: items) {
			item.setCantidad(0);
			session.update(item); 
		}
		return;
	}

	@Override
	public List<Item> listarItems(Carrito carrito) {
		final Session session = sessionFactory.getCurrentSession();
		List<Item> items = (ArrayList<Item>) session.createCriteria(Item.class)
				.add(Restrictions.eq("carrito", carrito))
				.add(Restrictions.gt("cantidad", 0))
				.list();
		return items;
	}

	@Override
	public void eliminarItem(Long idCarrito, Long idProducto) {
		final Session session = sessionFactory.getCurrentSession();
		Item item = (Item) session.createCriteria(Item.class)
				.add(Restrictions.eq("carrito.id", idCarrito))
				.add(Restrictions.eq("producto.id", idProducto))
				.uniqueResult();
		item.setCantidad(0);
		return;		
	}	

}
