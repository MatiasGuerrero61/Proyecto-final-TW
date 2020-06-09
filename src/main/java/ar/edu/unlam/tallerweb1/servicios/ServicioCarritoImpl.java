package ar.edu.unlam.tallerweb1.servicios;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unlam.tallerweb1.modelo.Carrito;
import ar.edu.unlam.tallerweb1.modelo.Item;
import ar.edu.unlam.tallerweb1.modelo.Producto;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCarrito;

@Service("servicioCarrito")
@Transactional
public class ServicioCarritoImpl implements ServicioCarrito{
	
	private RepositorioCarrito repositorioCarrito;
	private Carrito carrito;
	private HashMap<Long,Item> items;

	@Override
	public boolean tengoCarritoActivo(Usuario usuario) {
		if(this.carrito != null) {
			if(this.carrito.getUsuario().getId() == usuario.getId()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void generarCarritoVacio(Usuario usuario) {
		this.carrito = new Carrito();
		this.items = new HashMap<Long,Item>();
		this.carrito.setUsuario(usuario);
	}

	@Override
	public void cargarItem(Producto producto, Integer cantidad) {
		Item existente = this.items.get(producto.getId());
		if(existente != null) {
			existente.setCantidad(existente.getCantidad()+cantidad);
			this.items.replace(producto.getId(), existente);
		}
		else {
			//El precio de compra no lo seteo acá por si hay un aumento de precio desde el momento que lo agrega
			//al carrito, hasta que hace la compra efectiva.
			Item itemNuevo = new Item();
			itemNuevo.setCantidad(cantidad);
			itemNuevo.setCarrito(this.carrito);
			itemNuevo.setProducto(producto);
			this.items.put(producto.getId(), itemNuevo);			
		}						
	}
	
	@Override
	public void vaciarCarrito() {
		this.items.clear();
	}

	@Override
	public List<Item> listarItems() {
		return new ArrayList<Item>(this.items.values());
	}

	@Override
	public void destruirCarrito() {
		this.carrito = null;
		this.items = null;
	}

	@Override
	public Long obtenerIdDeTienda() {
		Item item = this.items.entrySet().iterator().next().getValue();
		return item.getProducto().getTienda().getId();
	}

	@Override
	public boolean tengoItems() {
		if(this.items.isEmpty()) {
			return false;
		}
		return true;
	}

	@Override
	public void eliminarProductoDeCarrito(Long idProducto) {
		this.items.remove(idProducto);
		if(this.items.isEmpty()) {
			this.carrito = null;
			this.items = null;
		}
	}

}
