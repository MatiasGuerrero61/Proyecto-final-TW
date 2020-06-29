package ar.edu.unlam.tallerweb1.servicios;

import java.math.BigDecimal;
import java.util.List;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Carrito;
import ar.edu.unlam.tallerweb1.modelo.Descuento;
import ar.edu.unlam.tallerweb1.modelo.EnumEstadoDeCompra;
import ar.edu.unlam.tallerweb1.modelo.Factura;
import ar.edu.unlam.tallerweb1.modelo.Item;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioFactura;

@Service("servicioFactura")
@Transactional
public class ServicioFacturaImpl implements ServicioFactura {
	
	private RepositorioFactura servicioFacturaDao;
	private ServicioDescuento servicioDescuento;
	private ServicioCarrito servicioCarrito;
	private ServicioItem servicioItem;
	
	@Autowired
    public ServicioFacturaImpl(RepositorioFactura servicioFacturaDao,
    						   ServicioItem servicioItem,
    						   ServicioCarrito servicioCarrito,
    						   ServicioDescuento servicioDescuento) {
		this.servicioFacturaDao = servicioFacturaDao;
        this.servicioItem = servicioItem;
        this.servicioCarrito = servicioCarrito;
        this.servicioDescuento = servicioDescuento;
    }
	
	@Override
	public boolean cargarCodigoDeDescuento(Factura factura, String codigo) {
		Descuento descuento = this.servicioDescuento.verificarValidez(codigo);
		if(descuento != null) {
			this.servicioDescuento.invalidarDescuento(descuento);
			factura.setDescuento(descuento);
			factura.setSubtotalDescuentos(this.servicioDescuento.calcularDescuento(descuento, factura.getSubtotalSinDescuentos()));
			factura.setImporteFinal(this.servicioDescuento.calcularImporteConDescuento(descuento, factura.getSubtotalSinDescuentos()));
			this.servicioFacturaDao.actualizarFactura(factura);
			return true;
		}
		return false;
	}

	@Override
	public void anularDescuento(Factura factura) {
		this.servicioFacturaDao.anularDescuento(factura);			
	}

	@Override
	public Factura generarFactura(Carrito carrito) {
		Factura factura = new Factura();
		factura.setCarrito(carrito);
		factura.setEstado(EnumEstadoDeCompra.SIN_CONFIRMAR);
		BigDecimal subtotalSinDescuentos = this.servicioItem.sumarImportesDeItems(carrito);
		factura.setSubtotalSinDescuentos(subtotalSinDescuentos);
		factura.setSubtotalDescuentos(new BigDecimal("0"));
		factura.setImporteFinal(subtotalSinDescuentos); 
		this.servicioItem.fijarPrecioDeCompra(this.servicioItem.listarItems(carrito));
		factura.setFechaYHoraDeCompra( LocalDateTime.now());
		this.servicioFacturaDao.guardarFactura(factura);		
		return factura;
	}
	
	@Override
	public Factura actualizarImportesFactura(Factura factura) {
		BigDecimal subtotalSinDescuentos = this.servicioItem.sumarImportesDeItems(factura.getCarrito());
		factura.setSubtotalSinDescuentos(subtotalSinDescuentos);
		if(factura.getDescuento() != null) {
			factura.setSubtotalDescuentos(this.servicioDescuento.calcularDescuento(factura.getDescuento(), factura.getSubtotalSinDescuentos()));
			factura.setImporteFinal(this.servicioDescuento.calcularImporteConDescuento(factura.getDescuento(), factura.getSubtotalSinDescuentos()));
		}
		else {
			factura.setSubtotalDescuentos(new BigDecimal("0"));
			factura.setImporteFinal(subtotalSinDescuentos); 			
		}
		this.servicioItem.fijarPrecioDeCompra(this.servicioItem.listarItems(factura.getCarrito()));
		factura.setFechaYHoraDeCompra( LocalDateTime.now());
		this.servicioFacturaDao.actualizarFactura(factura);
		return factura;
	}

	@Override
	public Factura sincronizarFactura(Long idCarrito) {
		Carrito carrito = this.servicioCarrito.obtenerCarrito(idCarrito);
		Factura factura = this.servicioFacturaDao.buscarFacturaSinConfirmar(carrito.getId());
		if(factura != null) {
			factura = this.actualizarImportesFactura(factura);
			return factura;
		}
		else {
			return this.generarFactura(carrito);
		}
	}

	@Override
	public List<Item> listarItems(Factura factura) {
		return this.servicioItem.listarItems(factura.getCarrito());
	}

	@Override
	public Factura obtenerFactura(Long idFactura) {
		return this.servicioFacturaDao.obtenerFactura(idFactura);
	}

	@Override
	public String obtenerMailDelComprador(Factura factura) {
		return factura.getCarrito().getUsuario().getEmail();
	}

	@Override
	public String obtenerNombreDeLaTienda(Factura factura) {
		return factura.getCarrito().getTienda().getRazonSocial();
	}

	@Override
	public BigDecimal obtenerImporteFinal(Factura factura) {
		return factura.getImporteFinal();
	}

	@Override
	public Factura obtenerFacturaPorIdMercadoPago(String preferenceId) {
		return this.servicioFacturaDao.obtenerFacturaPorIdMercadoPago(preferenceId);
	}

	@Override
	public void actualizarFactura(Factura factura) {
		this.servicioFacturaDao.actualizarFactura(factura);
		return;
	}
	
	@Override
	public void pagoExitoso(Factura factura) {
		factura.setEstado(EnumEstadoDeCompra.COMPRA_FINALIZADA);
		factura.getCarrito().setEstado(EnumEstadoDeCompra.COMPRA_FINALIZADA);
		this.servicioFacturaDao.actualizarFactura(factura);
	}

	@Override
	public void pagoPendiente(Factura factura) {
		factura.setEstado(EnumEstadoDeCompra.PENDIENTE_DE_PAGO);
		factura.getCarrito().setEstado(EnumEstadoDeCompra.PENDIENTE_DE_PAGO);
		this.servicioFacturaDao.actualizarFactura(factura);		
	}
}
