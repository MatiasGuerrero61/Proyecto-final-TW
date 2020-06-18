package ar.edu.unlam.tallerweb1.servicios;

import java.math.BigDecimal;
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
		Item item = this.servicioItemDao.buscarItem(carrito, producto);
		if(item == null) {
			item = this.servicioItemDao.crearItem(carrito, producto);
		}
		item.setCantidad(item.getCantidad() + cantidad);
		this.servicioItemDao.actualizarItem(item);
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

	@Override
	public BigDecimal sumarImportesDeItems(Carrito carrito) {
		List<Item> items = servicioItemDao.listarItems(carrito);
		BigDecimal importe = new BigDecimal("0");
		for(Item item: items) {			
			importe = importe.add(item.getProducto().getImporte().multiply(new BigDecimal(item.getCantidad().toString())));
		}
		return importe;
	}

}
