package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorHome {

	// Escucha la URL /home por GET, y redirige a una vista.
		@RequestMapping(path = "/home", method = RequestMethod.GET)
		public ModelAndView irAHome() {
			return new ModelAndView("home");
		}
		
		@RequestMapping(path = "/postear-perdida", method = RequestMethod.GET)
		public ModelAndView irAPostearPerdida() {
			return new ModelAndView("postear-perdida");
		}
		
		@RequestMapping(path = "/postear-anuncio", method = RequestMethod.GET)
		public ModelAndView irAPostearAnuncio() {
			return new ModelAndView("postear-anuncio");
		}
		
		@RequestMapping(path = "/postear-mascotas", method = RequestMethod.GET)
		public ModelAndView irAPostearMascotas() {
			return new ModelAndView("postear-mascotas");
		}
}
