package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Item;
import ar.edu.unlam.tallerweb1.modelo.Producto;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

public interface ServicioCarrito {
	
	boolean tengoCarritoActivo(Usuario usuario);
	void generarCarritoVacio(Usuario usuario);
	void cargarItem(Producto producto, Integer cantidad);
	void vaciarCarrito();
	List<Item> listarItems();
	void destruirCarrito();
	Long obtenerIdDeTienda();
	boolean tengoItems();
	void eliminarProductoDeCarrito(Long idProducto);
}
