package ar.edu.unlam.tallerweb1.servicios;

import java.util.HashMap;
import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Producto;
import ar.edu.unlam.tallerweb1.modelo.Tienda;

public interface ServicioTienda {
	List<Tienda> listarTiendas();
	Tienda buscarTiendaPorId(Long id);
	List<Producto> listarProductosDeTienda(Tienda tienda);
	List<Producto> filtrarProductos(HashMap<String, String> filtros, Tienda tienda);
}
