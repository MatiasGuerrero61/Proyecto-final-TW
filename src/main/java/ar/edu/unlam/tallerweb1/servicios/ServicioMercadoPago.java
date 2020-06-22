package ar.edu.unlam.tallerweb1.servicios;

import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;

import ar.edu.unlam.tallerweb1.modelo.Factura;

public interface ServicioMercadoPago {
	Preference generarPreference( Factura factura) throws MPException;

}
