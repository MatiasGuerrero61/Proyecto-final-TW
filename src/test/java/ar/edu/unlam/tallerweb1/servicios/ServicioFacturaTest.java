package ar.edu.unlam.tallerweb1.servicio;

import ar.edu.unlam.tallerweb1.modelo.Carrito;
import ar.edu.unlam.tallerweb1.modelo.EnumEstadoDeCompra;
import ar.edu.unlam.tallerweb1.modelo.Factura;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioFactura;
import ar.edu.unlam.tallerweb1.servicios.ServicioCarrito;
import ar.edu.unlam.tallerweb1.servicios.ServicioDescuento;
import ar.edu.unlam.tallerweb1.servicios.ServicioFactura;
import ar.edu.unlam.tallerweb1.servicios.ServicioFacturaImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServicioFacturaTest {

    @Test
    public void testFactura() {

        RepositorioFactura repositorioFactura = mock(RepositorioFactura.class);
        ServicioItem servicioItem = mock(ServicioItem.class);
        ServicioCarrito servicioCarrito = mock(ServicioCarrito.class);
        ServicioDescuento servicioDescuento = mock(ServicioDescuento.class);

        Carrito mockCarrito = getMockCarrito();


        when(servicioCarrito.obtenerCarrito(1L)).thenReturn(mockCarrito);
        when(servicioItem.sumarImportesDeItems(mockCarrito)).thenReturn(BigDecimal.valueOf(12000L));

        ServicioFactura servicioFactura = new ServicioFacturaImpl(repositorioFactura, servicioItem, servicioCarrito, servicioDescuento);

        Factura resultado = servicioFactura.generarFactura(mockCarrito);

        assertThat(resultado).isInstanceOf(Factura.class);
        assertThat(resultado.getCarrito()).isEqualTo(mockCarrito);
        assertThat(resultado.getEstado()).isEqualTo(EnumEstadoDeCompra.SIN_CONFIRMAR);
        assertThat(resultado.getSubtotalSinDescuentos()).isEqualTo(BigDecimal.valueOf(12000L));

    }

    private Carrito getMockCarrito() {
        return new Carrito();
    }
}
