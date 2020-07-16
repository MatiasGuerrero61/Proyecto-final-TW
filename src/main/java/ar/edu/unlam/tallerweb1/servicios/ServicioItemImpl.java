package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Carrito;
import ar.edu.unlam.tallerweb1.modelo.Item;
import ar.edu.unlam.tallerweb1.modelo.Producto;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioItem;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service("servicioItem")
@Transactional
public class ServicioItemImpl implements ServicioItem {

    private RepositorioItem servicioItemDao;
    private ServicioTienda servicioTienda;
    private RepositorioProducto repositorioProducto;

    @Autowired
    public ServicioItemImpl(RepositorioItem servicioItemDao,
                            ServicioTienda servicioTienda,
                            RepositorioProducto repositorioProducto) {
        this.servicioItemDao = servicioItemDao;
        this.servicioTienda = servicioTienda;
        this.repositorioProducto = repositorioProducto;
    }

    @Override
    public void cargarItem(Carrito carrito, Long idProducto, Integer cantidad) {
        Producto producto = this.servicioTienda.obtenerProducto(idProducto);
        Item item = this.servicioItemDao.buscarItem(carrito, producto);
        if (item == null) {
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
        for (Item item : items) {
            importe = importe.add(item.getProducto().getImporte().multiply(new BigDecimal(item.getCantidad().toString())));
        }
        return importe;
    }

    @Override
    public void fijarPrecioDeCompra(List<Item> items) {
        for (Item item : items) {
            item.setPrecioDeCompra(item.getProducto().getImporte());
            this.servicioItemDao.actualizarItem(item);
        }
        return;
    }

    @Override
    public void actualizarStockDeProductos(List<Item> items) {
        for (Item item : items) {
            Producto producto = item.getProducto();
            producto.setStock(producto.getStock() - item.getCantidad());
            this.repositorioProducto.actualizarProducto(producto);
        }
        return;
    }

}
