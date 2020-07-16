package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Anuncio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.Preference.AutoReturn;
import com.mercadopago.resources.datastructures.preference.Payer;
import com.mercadopago.resources.datastructures.preference.PaymentMethods;
import com.mercadopago.resources.datastructures.preference.BackUrls;
import com.mercadopago.resources.datastructures.preference.Item;
import ar.edu.unlam.tallerweb1.modelo.Factura;


@Service("servicioMercadoPago")
@Transactional
public class ServicioMercadoPagoImpl implements ServicioMercadoPago{
	
	private ServicioFactura servicioFactura;
	
	@Autowired
	public ServicioMercadoPagoImpl(ServicioFactura servicioFactura) {
		this.servicioFactura = servicioFactura;
	}

	@Override
	public Preference generarPreference(Factura factura) throws MPException {
		// Agrega credenciales
    	MercadoPago.SDK.setAccessToken("TEST-7932219125661368-062200-3f30b19236b124ad0e0c25ea4a7b4757-211010911");

    	// Crea un objeto de preferencia
    	Preference preference = new Preference();

    	// Crea un item en la preferencia
    	Item item = new Item();    	
    	String nombreDeTienda = this.servicioFactura.obtenerNombreDeLaTienda(factura);
    	item.setTitle("Carrito de la tienda: "+ nombreDeTienda)
    	    .setQuantity(1)
    	    .setUnitPrice(this.servicioFactura.obtenerImporteFinal(factura).floatValue());
    	preference.appendItem(item);
    	
    	// Crea el pagador
    	Payer payer = new Payer();
    	payer.setEmail( this.servicioFactura.obtenerMailDelComprador(factura) );
    	preference.setPayer(payer);
    	
    	// Urls para las respuestas de MercadoPago
    	String urlBase = "http://localhost:8080/proyecto-limpio-spring/";
    	BackUrls backUrls = new BackUrls(
                urlBase+"pago-exitoso/",
                urlBase+"pago-pendiente/",
                urlBase+"pago-rechazado/");
    	preference.setBackUrls(backUrls);
    	preference.setAutoReturn(AutoReturn.all);
    	// Pagos en una cuota
    	PaymentMethods paymentMethods = new PaymentMethods();
    	paymentMethods.setDefaultInstallments(1);
    	preference.setPaymentMethods(paymentMethods);
    	
    	preference.save();
    	factura.setIdMercadoPago(preference.getId());
    	this.servicioFactura.actualizarFactura(factura);
		return preference;
	}

    @Override
    public Preference generarPreferenceDeAnuncio(Anuncio anuncio, String email) throws MPException {
		// Agrega credenciales
		MercadoPago.SDK.setAccessToken("TEST-7932219125661368-062200-3f30b19236b124ad0e0c25ea4a7b4757-211010911");

		// Crea un objeto de preferencia
		Preference preference = new Preference();

		// Crea un item en la preferencia
		Item item = new Item();
		item.setTitle("Pago de recompensa")
				.setQuantity(1)
				.setUnitPrice(anuncio.getRecompensa().floatValue());
		preference.appendItem(item);

		// Crea el pagador
		Payer payer = new Payer();
		payer.setEmail(email);
		preference.setPayer(payer);

		// Urls para las respuestas de MercadoPago
		String urlBase = "http://localhost:8080/proyecto-limpio-spring/";
		BackUrls backUrls = new BackUrls(
				urlBase+"pago-exitoso/",
				urlBase+"pago-pendiente/",
				urlBase+"pago-rechazado/");
		preference.setBackUrls(backUrls);
		preference.setAutoReturn(AutoReturn.all);

		// Pagos en una cuota
		PaymentMethods paymentMethods = new PaymentMethods();
		paymentMethods.setDefaultInstallments(1);
		preference.setPaymentMethods(paymentMethods);

		preference.save();

		return preference;
    }

}
