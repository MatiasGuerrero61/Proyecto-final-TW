package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unlam.tallerweb1.modelo.Carrito;
import ar.edu.unlam.tallerweb1.modelo.Item;
import ar.edu.unlam.tallerweb1.modelo.Tienda;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCarrito;

@Service("servicioCarrito")
@Transactional
public class ServicioCarritoImpl implements ServicioCarrito{
	
	private ServicioItem servicioItem;
	private RepositorioCarrito servicioCarritoDao;
	
	@Autowired
    public ServicioCarritoImpl(ServicioItem servicioItem,
    						   RepositorioCarrito servicioCarritoDao) {
        this.servicioItem = servicioItem;
        this.servicioCarritoDao = servicioCarritoDao;
    }
	
	@Override
	public void vaciarCarrito(Long idCarrito) {
		this.servicioItem.vaciarItems(idCarrito);
	}

	@Override
	public void cargarItem(Long idCarrito, Long idProducto, Integer cantidad) {
		Carrito carrito = this.servicioCarritoDao.obtenerCarrito(idCarrito);
		servicioItem.cargarItem(carrito, idProducto, cantidad);
		return;
	}

	@Override
	public List<Item> listarItems(Carrito carrito) {
		return servicioItem.listarItems(carrito);
	}

	@Override
	public void eliminarProductoDeCarrito(Long idCarrito, Long idProducto) {
		servicioItem.eliminarItem(idCarrito, idProducto);
		return;
	}

	@Override
	public Carrito sincronizarCarrito(Usuario usuario, Tienda tienda) {
		Carrito carrito = servicioCarritoDao.buscarCarritoPendiente(usuario, tienda);
		if(carrito == null) {
			carrito = servicioCarritoDao.crearCarrito(usuario, tienda);
		}
		return carrito;
	}

	@Override
	public Carrito obtenerCarrito(Long idCarrito) {
		return this.servicioCarritoDao.obtenerCarrito(idCarrito);
	}		

}
