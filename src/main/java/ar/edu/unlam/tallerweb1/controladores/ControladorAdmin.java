package ar.edu.unlam.tallerweb1.controladores;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.sun.tools.javac.util.List;
import ar.edu.unlam.tallerweb1.modelo.Facturacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioFactura;

@Controller
public class ControladorAdmin {
	private ServicioFactura servicioFactura;
	
	@Autowired
    public ControladorAdmin(ServicioFactura servicioFactura) {
        this.servicioFactura = servicioFactura;
        }
	
	@RequestMapping(path = "/facturacion", method = RequestMethod.GET)
    public ModelAndView irAFacturacion() {
		ModelMap modelo = new ModelMap();
		String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM"));
		ArrayList<Facturacion> facturaciones = (ArrayList<Facturacion>) this.servicioFactura.obtenerFacturacionMensual(fecha);
		modelo.put("facturaciones", facturaciones);
		modelo.put("periodoDeFacturacion",fecha);
		return new ModelAndView("admin/facturacion",modelo);
    }
	
	@RequestMapping(path = "/facturacion", method = RequestMethod.POST)
    public ModelAndView irAFacturacion(@RequestParam("fecha") String fecha) {
		ArrayList<Facturacion> facturaciones = (ArrayList<Facturacion>) this.servicioFactura.obtenerFacturacionMensual(fecha);
		ModelMap modelo = new ModelMap();
		modelo.put("facturaciones", facturaciones);
		modelo.put("periodoDeFacturacion",fecha);
		return new ModelAndView("admin/facturacion",modelo);
    }

}
