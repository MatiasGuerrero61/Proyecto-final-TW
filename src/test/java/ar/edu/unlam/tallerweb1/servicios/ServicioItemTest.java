package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Carrito;
import ar.edu.unlam.tallerweb1.modelo.Item;
import ar.edu.unlam.tallerweb1.modelo.Producto;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioItem;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioProducto;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ServicioItemTest {

    private RepositorioItem repositorioItem = mock(RepositorioItem.class);
    private ServicioTienda servicioTienda = mock(ServicioTienda.class);
    private RepositorioProducto repositorioProducto = mock(RepositorioProducto.class);

    @Test
    public void testQueItemExisteYSeActualiza() {
        Producto producto = new Producto();
        Carrito carrito = new Carrito();
        Item item = new Item();
        item.setCantidad(1);
        when(servicioTienda.obtenerProducto(1L)).thenReturn(producto);
        when(repositorioItem.buscarItem(carrito, producto)).thenReturn(item);

        ServicioItem servicioItem = new ServicioItemImpl(repositorioItem, servicioTienda, repositorioProducto);

        servicioItem.cargarItem(carrito, 1L, 2);
    }

    @Test
    public void testFijarPrecioDeCompra() {
        ServicioItem servicioItem = new ServicioItemImpl(repositorioItem, servicioTienda, repositorioProducto);

        servicioItem.fijarPrecioDeCompra(getMockItems());

        //Se verifica que se haya llamado al repositorioItem por la cantidad de items en la lista
        verify(repositorioItem, times(getMockItems().size())).actualizarItem(any(Item.class));
    }

    @Test
    public void testSumarImportesDeItems() {
        Carrito carrito = new Carrito();
        List<Item> items = getMockItems();

        when(repositorioItem.listarItems(carrito)).thenReturn(items);

        ServicioItem servicioItem = new ServicioItemImpl(repositorioItem, servicioTienda, repositorioProducto);

        BigDecimal result = servicioItem.sumarImportesDeItems(carrito);

        assertThat(result).isEqualTo(BigDecimal.valueOf(30000));
    }

    @Test
    public void testActualizarStockDeProductos() {
        ServicioItem servicioItem = new ServicioItemImpl(repositorioItem, servicioTienda, repositorioProducto);
        List<Item> items = getMockItems();

        servicioItem.actualizarStockDeProductos(items);

        verify(repositorioProducto, times(items.size())).actualizarProducto(any(Producto.class));
    }

    private List<Item> getMockItems() {
        Producto producto = new Producto();
        producto.setImporte(BigDecimal.valueOf(5000));

        Item item1 = new Item();
        item1.setProducto(producto);
        item1.setCantidad(1);
        Item item2 = new Item();
        item2.setProducto(producto);
        item2.setCantidad(2);
        Item item3 = new Item();
        item3.setProducto(producto);
        item3.setCantidad(3);
        return List.of(item1, item2, item3);
    }

}
