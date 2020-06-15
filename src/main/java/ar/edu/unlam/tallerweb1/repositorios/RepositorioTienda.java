package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Filtro;
import ar.edu.unlam.tallerweb1.modelo.Producto;
import ar.edu.unlam.tallerweb1.modelo.Tienda;

import java.util.List;

public interface RepositorioTienda {
    List<Tienda> listarTiendas();

    List<Producto> listarProductosDeTienda(Tienda tienda);

    Tienda buscarTiendaPorId(Long id);

    List<Producto> filtrarProductos(Filtro filtros, Tienda tienda);

    List<Producto> listarCategorias(Tienda tienda);

    Producto obtenerProducto(long id);
}
