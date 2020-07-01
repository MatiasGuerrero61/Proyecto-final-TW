package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.excepciones.TiendaNotFoundException;
import ar.edu.unlam.tallerweb1.modelo.Filtro;
import ar.edu.unlam.tallerweb1.modelo.Item;
import ar.edu.unlam.tallerweb1.modelo.Producto;
import ar.edu.unlam.tallerweb1.modelo.Tienda;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioTienda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("servicioTienda")
@Transactional
public class ServicioTiendaImpl implements ServicioTienda {

    private RepositorioTienda servicioTiendaDao;

    @Autowired
    public ServicioTiendaImpl(RepositorioTienda servicioTiendaDao) {
        this.servicioTiendaDao = servicioTiendaDao;
    }

    @Override
    public List<Tienda> listarTiendas() {
        return servicioTiendaDao.listarTiendas();
    }

    @Override
    public Tienda buscarTiendaPorId(Long id) {

        return Optional
                .ofNullable(servicioTiendaDao.buscarTiendaPorId(id))
                .orElseThrow(() -> new TiendaNotFoundException("No se encontró tienda con id: " + id.toString()));

    }

    @Override
    public List<Producto> listarProductosDeTienda(Tienda tienda) {
        return servicioTiendaDao.listarProductosDeTienda(tienda);
    }

    @Override
    public List<Producto> filtrarProductos(Filtro filtros, Tienda tienda) {
        return servicioTiendaDao.filtrarProductos(filtros, tienda);
    }

    @Override
    public Producto obtenerProducto(long id) {
        return servicioTiendaDao.obtenerProducto(id);
    }

    @Override
    public List<Producto> listarCategorias(Tienda tienda) {
        return servicioTiendaDao.listarCategorias(tienda);
    }

	@Override
	public List<Producto> simularActualizacionDeStock(List<Item> items, List<Producto> productos) {
		for(Item item: items) {
			System.out.println("Entro al for de la simulacion...");
			System.out.println("cant productos: " + productos.size());
			Integer index = productos.indexOf(item.getProducto());
			System.out.println("Index: " + index);
			if (index > -1) {
				Producto producto = productos.get(index);
				System.out.println("Stock en producto: "+ producto.getStock());
				System.out.println("Stock en item: "+ item.getCantidad());
				producto.setStock(producto.getStock()-item.getCantidad());
				System.out.println("Stock actualizado individual: "+ producto.getStock());
				productos.set(index, producto);
				System.out.println("Stock actualizado en productos: "+ productos.get(index).getStock());
			}
		}
		return productos;
	}

}
