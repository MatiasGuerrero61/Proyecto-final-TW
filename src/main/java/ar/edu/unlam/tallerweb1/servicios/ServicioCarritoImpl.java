package ar.edu.unlam.tallerweb1.servicios;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
	private List<Item> items;

	@Override
	public boolean tengoCarritoActivo(Usuario usuario) {
		if(this.carrito != null) {
			System.out.println("carrito no es nulo en el servicio:");
			if(this.carrito.getUsuario().getId() == usuario.getId()) {
				System.out.println("usuarios iguales:");
				return true;
			}
			System.out.println("usuarios diferentes:");
			System.out.println(usuario.getId());
			System.out.println(this.carrito.getUsuario().getId());
		}
		return false;
	}

	@Override
	public void generarCarritoVacio(Usuario usuario) {
		this.carrito = new Carrito();
		this.items = new ArrayList<Item>();
		this.carrito.setUsuario(usuario);
	}

	@Override
	public void cargarItem(Producto producto, Integer cantidad) {
		System.out.println("producto en carrito:");
		System.out.println(producto);
		System.out.println("cantidad:");
		System.out.println(cantidad);
		Item item = new Item();
		item.setCantidad(cantidad);
		item.setCarrito(this.carrito);
		item.setProducto(producto);
		System.out.println("item:");
		System.out.println(item);
		//El precio de compra no lo seteo acá por si hay un aumento de precio desde el momento que lo agrega
		//al carrito, hasta que hace la compra efectiva.
		this.items.add(item);		
	}
	
	@Override
	public void vaciarCarrito() {
		this.items.clear();
	}

	@Override
	public List<Item> listarItems() {
		return this.items;
	}

	@Override
	public BigDecimal calcularImporteTotal() {
		BigDecimal importeTotal = new BigDecimal("0");
		for(Item item:this.items) {
			importeTotal.add(item.getProducto().getImporte());
		}
		return importeTotal;
	}

	@Override
	public void destruirCarrito() {
		this.items.clear();
		this.carrito = null;
		this.items = null;
	}

	@Override
	public Long obtenerIdDeTienda() {
		Item item = this.items.get(0);
		return item.getProducto().getTienda().getId();
	}

	@Override
	public boolean tengoItems() {
		if(this.items.isEmpty()) {
			return false;
		}
		return true;
	}

}
