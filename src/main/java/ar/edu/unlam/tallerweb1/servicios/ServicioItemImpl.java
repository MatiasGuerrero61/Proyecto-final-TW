package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Carrito;
import ar.edu.unlam.tallerweb1.modelo.Item;
import ar.edu.unlam.tallerweb1.modelo.Producto;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioItem;

@Service("servicioItem")
@Transactional
public class ServicioItemImpl implements ServicioItem {
	
	private RepositorioItem servicioItemDao;
	private ServicioTienda servicioTienda;

    @Autowired
    public ServicioItemImpl(RepositorioItem servicioItemDao, 
    						ServicioTienda servicioTienda) {
        this.servicioItemDao = servicioItemDao;
        this.servicioTienda = servicioTienda;
    }

	@Override
	public void cargarItem(Carrito carrito, Long idProducto, Integer cantidad) {
		Producto producto = this.servicioTienda.obtenerProducto(idProducto);
		servicioItemDao.cargarItem(carrito, producto, cantidad);
		return;
	}

	@Override
	public void vaciarItems(Long idCarrito) {
		servicioItemDao.vaciarItems(idCarrito);
		return;
	}

	@Override
	public List<Item> listarItems(Carrito carrito) {
		return servicioItemDao.listarItems(carrito);
	}

	@Override
	public void eliminarItem(Long idCarrito, Long idProducto) {
		servicioItemDao.eliminarItem(idCarrito, idProducto);
		return;		
	}

}
