package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Filtro;
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
    public ModelAndView irTiendao(@PathVariable("id") String id,
    		@RequestParam(value="buscador", required=false) String buscador,
    		@RequestParam(value="categoria", required=false) String categoria, 
    		@RequestParam(value="min", required=false) String min, 
    		@RequestParam(value="max", required=false) String max,
    		@RequestParam(value="orden", required=false) String orden) {
		
		
		System.out.println("orden: " + orden);
        ModelMap modelo = new ModelMap();

        try {
            Tienda tienda = servicioTienda.buscarTiendaPorId(Long.parseLong(id));
            if (tienda != null) {
            	modelo.put("tienda", tienda.getRazonSocial());
            	Filtro filtros = new Filtro();
            	if(buscador != null) {filtros.setNombre(buscador);}
            	if(categoria != null) {filtros.setCategoria(categoria);}
            	if(min != null) {filtros.setPrecioMin(min);}
            	if(max != null) {filtros.setPrecioMax(max);}
            	if(orden != null) {filtros.setOrder(orden);}
            	List<Producto> productos = servicioTienda.filtrarProductos(filtros, tienda);
            	if(!productos.isEmpty()) {
            		 modelo.put("productos", productos);
            		 modelo.put("filtros", filtros);
            	}
            	else {
            		modelo.put("msj", "No hay productos disponibles");
            		modelo.put("filtros", filtros);
            	}
            } else {
                modelo.put("msj", "Tienda no encontrada");
            }
        } catch (Exception e) {
            modelo.put("msj", e.getClass());
        } 
        
        return new ModelAndView("tienda/tienda", modelo);
    }

}
