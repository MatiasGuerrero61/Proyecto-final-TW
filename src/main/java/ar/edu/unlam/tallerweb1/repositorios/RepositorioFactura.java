package ar.edu.unlam.tallerweb1.repositorios;

import java.time.LocalDateTime;
import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Factura;
import ar.edu.unlam.tallerweb1.modelo.Facturacion;

public interface RepositorioFactura {
	void actualizarFactura(Factura factura);
	void anularDescuento(Factura factura);
	void guardarFactura(Factura factura);
	Factura buscarFacturaSinConfirmar(Long idCarrito);
	Factura obtenerFactura(Long idFactura);
	Factura obtenerFacturaPorIdMercadoPago(String preferenceId);
	List<Facturacion> obtenerFacturacion(LocalDateTime fechaDesde, LocalDateTime fechaHasta);
}
