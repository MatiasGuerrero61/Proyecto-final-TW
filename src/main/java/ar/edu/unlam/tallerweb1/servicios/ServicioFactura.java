package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;
import ar.edu.unlam.tallerweb1.modelo.Factura;
import ar.edu.unlam.tallerweb1.modelo.Item;

public interface ServicioFactura {
	boolean cargarCodigoDeDescuento(Factura factura, String codigo);
	void anularDescuento(Factura factura);
	Factura generarFactura(Long idCarrito);
	List<Item> listarItems(Factura factura);
	Factura obtenerFactura(Long idFactura);
}
