package ar.edu.unlam.tallerweb1.servicios;

import java.math.BigDecimal;
import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Carrito;
import ar.edu.unlam.tallerweb1.modelo.Factura;
import ar.edu.unlam.tallerweb1.modelo.Facturacion;
import ar.edu.unlam.tallerweb1.modelo.Item;

public interface ServicioFactura {
	boolean cargarCodigoDeDescuento(Factura factura, String codigo);
	void anularDescuento(Factura factura);
	Factura generarFactura(Carrito carrito);
	Factura actualizarImportesFactura(Factura factura);
	Factura sincronizarFactura(Long idCarrito);
	List<Item> listarItems(Factura factura);
	Factura obtenerFactura(Long idFactura);
	String obtenerMailDelComprador(Factura factura);
	String obtenerNombreDeLaTienda(Factura factura);
	BigDecimal obtenerImporteFinal(Factura factura);
	Factura obtenerFacturaPorIdMercadoPago(String preferenceId);
	void actualizarFactura(Factura factura);
	void pagoExitoso(Factura factura);
	void pagoPendiente(Factura factura);
	boolean checkearStock(Factura factura);
	List<Facturacion> obtenerFacturacionMensual(String inputMonth);
}
