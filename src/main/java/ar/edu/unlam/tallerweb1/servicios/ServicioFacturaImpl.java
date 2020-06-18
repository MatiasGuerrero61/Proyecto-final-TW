package ar.edu.unlam.tallerweb1.servicios;

import java.math.BigDecimal;
import java.util.List;

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
	public Factura generarFactura(Long idCarrito) {
		Carrito carrito = this.servicioCarrito.obtenerCarrito(idCarrito);
		Factura factura = new Factura();
		factura.setCarrito(carrito);
		factura.setEstado(EnumEstadoDeCompra.PENDIENTE_DE_COMPRA);
		BigDecimal subtotalSinDescuentos = this.servicioItem.sumarImportesDeItems(carrito);
		factura.setSubtotalSinDescuentos(subtotalSinDescuentos);
		factura.setSubtotalDescuentos(new BigDecimal("0"));
		factura.setImporteFinal(subtotalSinDescuentos); 
		this.servicioFacturaDao.guardarFactura(factura);
		return factura;
	}

	@Override
	public List<Item> listarItems(Factura factura) {
		return this.servicioItem.listarItems(factura.getCarrito());
	}

	@Override
	public Factura obtenerFactura(Long idFactura) {
		return this.servicioFacturaDao.obtenerFactura(idFactura);
	}	

}
