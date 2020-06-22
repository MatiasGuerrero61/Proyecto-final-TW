package ar.edu.unlam.tallerweb1.controladores;
import com.mercadopago.MercadoPago;
import com.mercadopago.MercadoPago.*;
import com.mercadopago.exceptions.MPConfException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.Payer;

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
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioFactura;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
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
    	Factura factura = this.servicioFactura.generarFactura(idCarrito);
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
    		modelo.put("msjCodigo","C&oacute;digo de descuento "+ codigo +" aplicado correctamente!");
    	}
    	else {
    		modelo.put("msjCodigo","C&oacute;digo de descuento "+ codigo +" inv&aacute;lido...");
    	}    	
    	List<Item> items = this.servicioFactura.listarItems(factura);    	
    	modelo.put("itemsFactura", items);
    	modelo.put("factura", factura);
    	Preference preference = this.servicioMercadoPago.generarPreference(factura);
    	modelo.put("preference", preference);
    	return new ModelAndView("compra/generar-factura",modelo);
    }    
    

}
