package ar.edu.unlam.tallerweb1.servicios;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import ar.edu.unlam.tallerweb1.modelo.Factura;

public interface ServicioEmail {
	String generarMsjPagoAprobado(Factura factura);
	String generarMsjPagoPendiente(Factura factura);
	void enviarMsj(String destinatario, String asunto, String msj) throws AddressException, MessagingException;

}