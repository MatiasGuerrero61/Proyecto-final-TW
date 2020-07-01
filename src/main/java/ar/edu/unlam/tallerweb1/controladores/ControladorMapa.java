package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Tienda;
import ar.edu.unlam.tallerweb1.servicios.ServicioTienda;

@Controller
public class ControladorMapa {
	
    private ServicioTienda servicioTienda;

    @Autowired
    public ControladorMapa(ServicioTienda servicioTienda) {
        this.servicioTienda = servicioTienda;
    }
	
    @RequestMapping(path = "/mapa/{id}", method = RequestMethod.GET)
    public ModelAndView irAMapa(@PathVariable("id") String id) {
        ModelMap modelo = new ModelMap();
        Tienda tienda = servicioTienda.buscarTiendaPorId(Long.parseLong(id));
        modelo.put("tienda", tienda);
        return new ModelAndView("mapa/mapa-de-tienda", modelo);
    }
    
    @RequestMapping(path = "/mapa", method = RequestMethod.GET)
    public ModelAndView irAMapa() {
        ModelMap modelo = new ModelMap();
        List<Tienda> tiendas = servicioTienda.listarTiendas();
        modelo.put("tiendas", tiendas);
        return new ModelAndView("mapa/mapa-general", modelo);
    }
}
