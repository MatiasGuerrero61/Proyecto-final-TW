package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import ar.edu.unlam.tallerweb1.servicios.ServicioMascotaPerdida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Anuncio;
import ar.edu.unlam.tallerweb1.modelo.Mascota;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioAnuncio;
import ar.edu.unlam.tallerweb1.servicios.ServicioMascota;

@Controller
@RequestMapping("/mascotas-perdidas")
public class ControladorMascotaPerdida{
	
	private ServicioMascotaPerdida servicioMascotaPerdida;
	
	@Autowired
	public ControladorMascotaPerdida(ServicioMascotaPerdida servicioMascotaPerdida){
		this.servicioMascotaPerdida = servicioMascotaPerdida;
	}
	
	@RequestMapping(path = "/postear-perdida", method = RequestMethod.GET)
	public ModelAndView irAPostearPerdida(HttpServletRequest request) {
		List<Mascota> misMascotas = this.servicioMascotaPerdida.getMascotaDeUsuario(request);
		ModelMap model = new ModelMap();
		model.put("mascotas",misMascotas);
		
		return new ModelAndView("mascotas-perdidas/postear-perdida",model);
	}
	
	@ModelAttribute("guardarAnuncio") 
	public Anuncio getAnuncio(){ 
	    return new Anuncio();
	}
	
	@RequestMapping(path = "/guardar-anuncio", method = RequestMethod.POST)
	public ModelAndView guardarPost(@ModelAttribute("guardarAnuncio") Anuncio anuncio,BindingResult result, HttpServletRequest request,
				@RequestParam("mascotaId") Long id
			) {
		Mascota mascota = this.servicioMascotaPerdida.getMascotaById(id);
		anuncio.setMascota(mascota);
		servicioMascotaPerdida.guardarAnuncio(anuncio);
		
		return new ModelAndView("redirect:/home/index");
	}
	
	@RequestMapping("/lista-perdida")
	public ModelAndView irAListaPerdida() {
		
		ModelMap model = servicioMascotaPerdida.getListaAnuncio();

	return new ModelAndView("mascotas-perdidas/lista-perdida", model);
		
	}
}
