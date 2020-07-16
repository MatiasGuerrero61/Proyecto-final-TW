package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Carrito;
import ar.edu.unlam.tallerweb1.modelo.Descuento;
import ar.edu.unlam.tallerweb1.modelo.EnumEstadoDeCompra;
import ar.edu.unlam.tallerweb1.modelo.Factura;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioFactura;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServicioFacturaTest {

    private RepositorioFactura repositorioFactura = mock(RepositorioFactura.class);
    private ServicioItem servicioItem = mock(ServicioItem.class);
    private ServicioCarrito servicioCarrito = mock(ServicioCarrito.class);
    private ServicioDescuento servicioDescuento = mock(ServicioDescuento.class);

    @Test
    public void testFactura() {


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

    @Test
    public void testFacturaSinDescuentoTieneComoImporteFinalLaSumaDelImporteDeItemsEnCarrito() {
        when(servicioItem.sumarImportesDeItems(any())).thenReturn(BigDecimal.valueOf(10000));

        Factura factura = new Factura();

        ServicioFactura servicioFactura = new ServicioFacturaImpl(repositorioFactura, servicioItem, servicioCarrito, servicioDescuento);

        Factura result = servicioFactura.actualizarImportesFactura(factura);

        assertThat(result.getImporteFinal()).isEqualTo(BigDecimal.valueOf(10000));
    }

    @Test
    public void testCargarCodigoDescuento() {
        Descuento descuento = new Descuento();
        descuento.setPorcentajeDeDescuento(20);
        descuento.setTope(BigDecimal.valueOf(2000));
        when(servicioDescuento.verificarValidez(any())).thenReturn(descuento);

        Factura factura = new Factura();

        ServicioFactura servicioFactura = new ServicioFacturaImpl(repositorioFactura, servicioItem, servicioCarrito, servicioDescuento);

        Boolean result = servicioFactura.cargarCodigoDeDescuento(factura, "TEST");

        assertThat(result).isTrue();
    }

    private Carrito getMockCarrito() {
        return new Carrito();
    }
}
