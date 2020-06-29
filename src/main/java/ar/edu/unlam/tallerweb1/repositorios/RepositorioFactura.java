package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Factura;

public interface RepositorioFactura {
	void actualizarFactura(Factura factura);
	void anularDescuento(Factura factura);
	void guardarFactura(Factura factura);
	Factura buscarFacturaSinConfirmar(Long idCarrito);
	Factura obtenerFactura(Long idFactura);
	Factura obtenerFacturaPorIdMercadoPago(String preferenceId);
}
