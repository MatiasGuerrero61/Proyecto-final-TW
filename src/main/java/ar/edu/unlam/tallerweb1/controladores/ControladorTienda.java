package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Producto;
import ar.edu.unlam.tallerweb1.modelo.Tienda;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioTienda;

@Controller
@RequestMapping(value="/tienda")
public class ControladorTienda {
	
	private ServicioLogin servicioLogin;
	private ServicioTienda servicioTienda;	
	
	@Autowired
	public ControladorTienda(ServicioLogin servicioLogin, ServicioTienda servicioTienda){
		this.servicioLogin = servicioLogin;
		this.servicioTienda = servicioTienda;
	}
	
	 
	@RequestMapping(path = "/todas", method = RequestMethod.GET)
	public ModelAndView irAListarTiendas() {
		ModelMap modelo = new ModelMap();
		List<Tienda> tiendas = servicioTienda.listarTiendas();
		if(!tiendas.isEmpty()) {
			modelo.put("tiendas",tiendas);
		}
		else {
			modelo.put("msj","No hay tiendas disponibles");
		}	
		 
		return new ModelAndView("tienda/listar-tiendas",modelo);
	}
	
	@RequestMapping(path = "/ver/{id}", method = RequestMethod.GET)
    public ModelAndView irTiendao(@PathVariable("id") String id) {

        ModelMap modelo = new ModelMap();

        try {
            Tienda tienda = servicioTienda.buscarTiendaPorId(Long.parseLong(id));
            if (tienda != null) {
            	modelo.put("tienda", tienda.getRazonSocial());
            	List<Producto> productos = servicioTienda.listarProductosDeTienda(tienda);
            	if(!productos.isEmpty()) {
            		 modelo.put("productos", productos);
            	}
            	else {
            		modelo.put("msj", "No hay productos disponibles");
            	}
            } else {
                modelo.put("msj", "Tienda no encontrada");
            }
        } catch (Exception e) {
            modelo.put("msj", "Error en la solicitud");
        } 
        
        return new ModelAndView("tienda/tienda", modelo);
    }

}
