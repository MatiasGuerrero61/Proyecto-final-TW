package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Carrito;
import ar.edu.unlam.tallerweb1.modelo.Item;
import ar.edu.unlam.tallerweb1.modelo.Tienda;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import java.util.List;

public interface ServicioCarrito {
	void vaciarCarrito(Long idCarrito);
	void cargarItem(Long idCarrito, Long idProducto, Integer cantidad);
	List<Item> listarItems(Carrito carrito);
	void eliminarProductoDeCarrito(Long Carrito, Long idProducto);
	Carrito sincronizarCarrito(Usuario usuario, Tienda tienda);
	Carrito obtenerCarrito(Long idCarrito);
}
