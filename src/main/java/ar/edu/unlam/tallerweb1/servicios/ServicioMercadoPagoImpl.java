package ar.edu.unlam.tallerweb1.servicios;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.Payer;
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
    	
    	preference.save();
		return preference;
	}

}
