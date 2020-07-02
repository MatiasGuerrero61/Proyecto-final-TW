package ar.edu.unlam.tallerweb1.servicios;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import ar.edu.unlam.tallerweb1.modelo.Factura;

@Service("servicioEmail")
public class ServicioEmailImpl implements ServicioEmail {    

	@Override
	public String generarMsjPagoAprobado(Factura factura) {
		String msj = "<p><b>Su pago ha sido &eacute;xitoso<b></p> <br>"
				+ "<p>Hemos recibido un pago de $ " + factura.getImporteFinal()
				+ "<p>En concepto de su compra realizada en la tienda \"<b>"
				+ factura.getCarrito().getTienda().getRazonSocial() + "</b>\"</p>"
				+ "<p>Gracias por elegirnos!</p>"
				+ "<p>DevTeam</p>";
		return msj;
	}

	@Override
	public String generarMsjPagoPendiente(Factura factura) {
		String msj = "<p><b>Su pago se encuentra pendiente de pago<b></p> <br>"
				+ "<p>Su pago de $ " + factura.getImporteFinal()
				+ "<p>En concepto de su compra realizada en la tienda \"<b>"
				+ factura.getCarrito().getTienda().getRazonSocial() + "</b>\" "
				+ "se encuentra pendiente de pago.</p>"
				+ "<p>Gracias por elegirnos!</p>"
				+ "<p>DevTeam</p>";
		return msj;
	}

	@Override
	public void enviarMsj(String destinatario, String asunto, String msj) throws AddressException, MessagingException {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("demounlam");
        mailSender.setPassword("Nomerobes2020");
        Properties prop = new Properties();
        prop.setProperty("mail.smtp.auth", "true");
        prop.setProperty("mail.smtp.starttls.enable", "true");
        prop.setProperty("mail.transport.protocol", "smtp");
        mailSender.setJavaMailProperties(prop);
		System.out.println("MAIL: " + mailSender.getJavaMailProperties());
		System.out.println("MAIL: " + mailSender.getPassword() + mailSender.getUsername() + mailSender.getHost());
		MimeMessage mensaje = mailSender.createMimeMessage();
		mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
		mensaje.setSubject(asunto);
		mensaje.setContent(msj,"text/html; charset=utf-8");
		mailSender.send(mensaje);		
	}
}
