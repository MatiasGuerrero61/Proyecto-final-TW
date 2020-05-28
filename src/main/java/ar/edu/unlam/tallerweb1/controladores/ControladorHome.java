package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorHome {

	// Escucha la URL /home por GET, y redirige a una vista.
		@RequestMapping(path = "/home/index", method = RequestMethod.GET)
		public ModelAndView irAHome() {
			return new ModelAndView("home/index");
		}
		
		@RequestMapping(path = "/mascota-perdida/postear-anuncio", method = RequestMethod.GET)
		public ModelAndView irAPostearAnuncio() {
			return new ModelAndView("postear-anuncio");
		}
		
		@RequestMapping(path = "/postear-mascotas", method = RequestMethod.GET)
		public ModelAndView irAPostearMascotas() {
			return new ModelAndView("postear-mascotas");
		}
}
