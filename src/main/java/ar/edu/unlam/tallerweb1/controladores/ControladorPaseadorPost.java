package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Anuncio;
import ar.edu.unlam.tallerweb1.modelo.PostMascotaPerdida;
import ar.edu.unlam.tallerweb1.servicios.ServicioAnuncio;

@Controller
@RequestMapping("/paseador")
public class ControladorPaseadorPost {
	private ServicioAnuncio servicioAnuncio;
	@Autowired
	public ControladorPaseadorPost(ServicioAnuncio service) {
		this.servicioAnuncio = service;
	}
	
	@RequestMapping(path = "/postear-anuncio", method = RequestMethod.GET)
	public ModelAndView irAPostearPerdida() {
		return new ModelAndView("paseador/postear-anuncio");
	}
	
	@ModelAttribute("crearAnuncio") 
	public Anuncio getAnuncio(){ 
	    return new Anuncio();
	}
	@RequestMapping(path = "/crear-anuncio", method = RequestMethod.POST)
	public ModelAndView guardarPost(@ModelAttribute("crearAnuncio")Anuncio anuncio,BindingResult result, HttpServletRequest request) {
		
		servicioAnuncio.guardar(anuncio);
		
		return new ModelAndView("redirect:/");
	}
	@RequestMapping("/lista-anuncio")
	public ModelAndView irAListaAnuncio() {
		
		ModelMap model = new ModelMap();
		List<Anuncio> anuncios = servicioAnuncio.getAnuncios();
		if(anuncios != null) {
			model.put("anuncios", anuncios);
			return new ModelAndView("paseador/lista-anuncio", model);
		}
		else {// si el usuario no existe agrega un mensaje de error en el modelo.
			model.put("error", "No hay ningun anuncio");
	}
	return new ModelAndView("paseador/lista-anuncio", model);
		
	}
}
