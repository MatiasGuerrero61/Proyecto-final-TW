package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Carrito;
import ar.edu.unlam.tallerweb1.modelo.Item;
import ar.edu.unlam.tallerweb1.modelo.Producto;

public interface ServicioItem {
	void cargarItem(Carrito carrito, Long idProducto, Integer cantidad);
	void vaciarItems(Long idCarrito);
	List<Item> listarItems(Carrito carrito);
	void eliminarItem(Long idCarrito, Long idProducto);

}
