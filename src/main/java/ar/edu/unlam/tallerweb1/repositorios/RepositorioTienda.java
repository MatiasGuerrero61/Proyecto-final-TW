package ar.edu.unlam.tallerweb1.repositorios;

import java.util.HashMap;
import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Filtro;
import ar.edu.unlam.tallerweb1.modelo.Producto;
import ar.edu.unlam.tallerweb1.modelo.Tienda;

public interface RepositorioTienda {
	List<Tienda> listarTiendas();
	List<Producto> listarProductosDeTienda(Tienda tienda);
	Tienda buscarTiendaPorId(Long id);
	List<Producto> filtrarProductos(Filtro filtros, Tienda tienda);
	List<String> listarCategorias(Tienda tienda);
	Producto obtenerProducto(long id);
}
