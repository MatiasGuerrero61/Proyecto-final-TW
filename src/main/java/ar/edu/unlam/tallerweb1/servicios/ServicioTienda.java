package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Filtro;
import ar.edu.unlam.tallerweb1.modelo.Producto;
import ar.edu.unlam.tallerweb1.modelo.Tienda;

import java.util.List;

public interface ServicioTienda {
    List<Tienda> listarTiendas();

    Tienda buscarTiendaPorId(Long id);

    List<Producto> listarProductosDeTienda(Tienda tienda);

    List<Producto> filtrarProductos(Filtro filtros, Tienda tienda);

    Producto obtenerProducto(long id);

    List<Producto> listarCategorias(Tienda tienda);
}
