package ar.edu.unlam.tallerweb1.servicios;

import java.math.BigDecimal;

import ar.edu.unlam.tallerweb1.modelo.Descuento;

public interface ServicioDescuento {
	Descuento verificarValidez(String codigo);
	void invalidarDescuento(Descuento descuento);
	void reactivarDescuento(Descuento descuento);
	BigDecimal calcularDescuento(Descuento descuento, BigDecimal importe);
	BigDecimal calcularImporteConDescuento(Descuento descuento, BigDecimal importe);
}
