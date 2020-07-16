package ar.edu.unlam.tallerweb1.repositorios;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Carrito;
import ar.edu.unlam.tallerweb1.modelo.EnumEstadoDeCompra;
import ar.edu.unlam.tallerweb1.modelo.Factura;
import ar.edu.unlam.tallerweb1.modelo.Facturacion;
import ar.edu.unlam.tallerweb1.modelo.Tienda;


public class RepositorioFacturaTest extends SpringTest {
	@Autowired
	RepositorioFactura repositorioFactura;
	
	@Test @Transactional
	@Rollback
	public void testQueObtieneFacturasDeComprasFinalizadasDentroDeUnPeriodoEspecifico() {
		Tienda tienda = new Tienda();
		Tienda tienda2 = new Tienda();
		Carrito carrito1 = new Carrito();
		carrito1.setTienda(tienda);
		Carrito carrito2 = new Carrito();
		carrito2.setTienda(tienda);
		Carrito carrito3 = new Carrito();
		carrito3.setTienda(tienda);
		Carrito carrito4 = new Carrito();
		carrito4.setTienda(tienda);
		Carrito carrito5 = new Carrito();
		carrito5.setTienda(tienda2);
		
		Factura factura1 = new Factura();		
		factura1.setEstado(EnumEstadoDeCompra.COMPRA_FINALIZADA);
		factura1.setFechaYHoraDeCompra(LocalDateTime.of(2020,Month.JULY, 15, 11, 00));
		factura1.setCarrito(carrito1);
		factura1.setImporteFinal(new BigDecimal("500"));
		
		Factura factura2 = new Factura();
		factura2.setEstado(EnumEstadoDeCompra.COMPRA_FINALIZADA);
		factura2.setFechaYHoraDeCompra(LocalDateTime.of(2020,Month.APRIL, 17, 9, 30));
		factura2.setCarrito(carrito2);
		factura2.setImporteFinal(new BigDecimal("100"));
		
		Factura factura3 = new Factura();
		factura3.setEstado(EnumEstadoDeCompra.SIN_CONFIRMAR);
		factura3.setFechaYHoraDeCompra(LocalDateTime.of(2020,Month.JULY, 05, 19, 40));
		factura3.setCarrito(carrito3);
		factura3.setImporteFinal(new BigDecimal("200"));
		
		Factura factura4 = new Factura();
		factura4.setEstado(EnumEstadoDeCompra.COMPRA_FINALIZADA);
		factura4.setFechaYHoraDeCompra(LocalDateTime.of(2020,Month.JULY, 05, 20, 35));
		factura4.setCarrito(carrito4);
		factura4.setImporteFinal(new BigDecimal("3000"));
		
		Factura factura5 = new Factura();
		factura5.setEstado(EnumEstadoDeCompra.COMPRA_FINALIZADA);
		factura5.setFechaYHoraDeCompra(LocalDateTime.of(2020,Month.JULY, 05, 10, 35));
		factura5.setCarrito(carrito5);
		factura5.setImporteFinal(new BigDecimal("125"));
		
		session().save(factura1);
		session().save(factura2);
		session().save(factura3);
		session().save(factura4);
		session().save(factura5);
		
		List<Facturacion> facturacion = this.repositorioFactura.obtenerFacturacion(LocalDateTime.of(2020,Month.JULY,01,0,0,0),LocalDateTime.of(2020,Month.AUGUST,01,0,0,0));
		for(Facturacion fac: facturacion) {
			System.out.println("tienda id: " + fac.getIdTienda());
			System.out.println("importe: " + fac.getImporteTotal());
			System.out.println("cant ventas: " + fac.getCantidadDeVentas());
		}
		
		assertThat(facturacion).hasSize(2);
		//assertThat(facturas.get(1).getImporteFinal()).isEqualByComparingTo(new BigDecimal("3500"));
	}	

}
