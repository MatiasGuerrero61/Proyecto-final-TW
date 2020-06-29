package ar.edu.unlam.tallerweb1.controladores;

import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ar.edu.unlam.tallerweb1.modelo.Factura;
import ar.edu.unlam.tallerweb1.modelo.Item;
import ar.edu.unlam.tallerweb1.servicios.ServicioFactura;
import ar.edu.unlam.tallerweb1.servicios.ServicioMercadoPago;

@Controller
public class ControladorCompra {
	
	private ServicioFactura servicioFactura;
	private ServicioMercadoPago servicioMercadoPago;

    @Autowired
    public ControladorCompra(ServicioFactura servicioFactura, ServicioMercadoPago servicioMercadoPago) {
        this.servicioFactura = servicioFactura;
        this.servicioMercadoPago = servicioMercadoPago;
        }
	
    @RequestMapping(value = "/generar-factura", method = RequestMethod.POST)
    public ModelAndView generarFactura(@RequestParam("idCarrito") Long idCarrito, HttpServletRequest request) throws MPException {
    	ModelMap modelo = new ModelMap();
    	Factura factura = this.servicioFactura.sincronizarFactura(idCarrito);
    	modelo.put("factura", factura);
    	List<Item> items = this.servicioFactura.listarItems(factura);
    	modelo.put("itemsFactura", items);  	
    	Preference preference = this.servicioMercadoPago.generarPreference(factura);
    	modelo.put("preference", preference);
    	return new ModelAndView("compra/generar-factura",modelo);
    }
    
    @RequestMapping(value = "/aplicar-descuento", method = RequestMethod.POST)
    public ModelAndView aplicararDescuento(@RequestParam("idFactura") Long idFactura,
    		 							@RequestParam("codigo") String codigo) throws MPException {
    	
    	ModelMap modelo = new ModelMap();
    	Factura factura = this.servicioFactura.obtenerFactura(idFactura);
    	if(this.servicioFactura.cargarCodigoDeDescuento(factura, codigo)) {
    		modelo.put("tipoDeMsj","success");
    		modelo.put("msj","C&oacute;digo de descuento "+ codigo +" aplicado correctamente!");
    	}
    	else {
    		modelo.put("tipoDeMsj","danger");
    		modelo.put("msj","C&oacute;digo de descuento "+ codigo +" inv&aacute;lido...");
    	}    	
    	List<Item> items = this.servicioFactura.listarItems(factura);    	
    	modelo.put("itemsFactura", items);
    	modelo.put("factura", factura);
    	Preference preference = this.servicioMercadoPago.generarPreference(factura);
    	modelo.put("preference", preference);
    	return new ModelAndView("compra/generar-factura",modelo);
    }    
    
    @RequestMapping(value = "/pago-exitoso", method = RequestMethod.GET)
    public ModelAndView pagoExitoso(@RequestParam("collection_id") String collectionId,
    								@RequestParam("collection_status") String collectionStatus,
    								@RequestParam("external_reference") String externalReference,
    								@RequestParam("payment_type") String paymentType,
    								@RequestParam("merchant_order_id") String merchantOrderId,
    								@RequestParam("preference_id") String preferenceId,
    								@RequestParam("site_id") String siteId,
    								@RequestParam("processing_mode") String processingMode,
    								@RequestParam("merchant_account_id") String merchantAccountId) throws MPException {
    	
    	ModelMap modelo = new ModelMap();
    	modelo.put("tipoDeMsj","success");
		modelo.put("msj","La compra ha sido &eacute;xitosa!");
    	Factura factura = this.servicioFactura.obtenerFacturaPorIdMercadoPago(preferenceId);
    	this.servicioFactura.pagoExitoso(factura);
    	return new ModelAndView("shared/mensaje",modelo);
    }  
    
    @RequestMapping(value = "/pago-pendiente", method = RequestMethod.GET)
    public ModelAndView pagoPendiente(@RequestParam("collection_id") String collectionId,
    								@RequestParam("collection_status") String collectionStatus,
    								@RequestParam("external_reference") String externalReference,
    								@RequestParam("payment_type") String paymentType,
    								@RequestParam("merchant_order_id") String merchantOrderId,
    								@RequestParam("preference_id") String preferenceId,
    								@RequestParam("site_id") String siteId,
    								@RequestParam("processing_mode") String processingMode,
    								@RequestParam("merchant_account_id") String merchantAccountId) throws MPException {
    	
    	ModelMap modelo = new ModelMap();
    	modelo.put("tipoDeMsj","info");
		modelo.put("msj","Compra pendiente de pago...");
    	Factura factura = this.servicioFactura.obtenerFacturaPorIdMercadoPago(preferenceId);
    	this.servicioFactura.pagoPendiente(factura);
    	return new ModelAndView("shared/mensaje",modelo);
    }
    
    @RequestMapping(value = "/pago-rechazado", method = RequestMethod.GET)
    public ModelAndView pagoRechazado(@RequestParam("collection_id") String collectionId,
    								@RequestParam("collection_status") String collectionStatus,
    								@RequestParam("external_reference") String externalReference,
    								@RequestParam("payment_type") String paymentType,
    								@RequestParam("merchant_order_id") String merchantOrderId,
    								@RequestParam("preference_id") String preferenceId,
    								@RequestParam("site_id") String siteId,
    								@RequestParam("processing_mode") String processingMode,
    								@RequestParam("merchant_account_id") String merchantAccountId) throws MPException {
    	
    	Factura factura = this.servicioFactura.obtenerFacturaPorIdMercadoPago(preferenceId);
    	List<Item> items = this.servicioFactura.listarItems(factura);
    	ModelMap modelo = new ModelMap();
    	modelo.put("itemsFactura", items);
    	modelo.put("factura", factura);
    	Preference preference = this.servicioMercadoPago.generarPreference(factura);
    	modelo.put("preference", preference);  	
    	modelo.put("tipoDeMsj","danger");
		modelo.put("msj","Pago rechazado...");
		return new ModelAndView("compra/generar-factura",modelo);
    }

}
