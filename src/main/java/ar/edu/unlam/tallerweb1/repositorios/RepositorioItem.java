package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Carrito;
import ar.edu.unlam.tallerweb1.modelo.Item;
import ar.edu.unlam.tallerweb1.modelo.Producto;

public interface RepositorioItem {
	Item buscarItem(Carrito carrito, Producto producto);
	Item crearItem(Carrito carrito, Producto producto);
	void actualizarItem(Item item);
	void vaciarItems(Long idCarrito);
	List<Item> listarItems(Carrito carrito);
	void eliminarItem(Long idCarrito, Long idProducto);
}
