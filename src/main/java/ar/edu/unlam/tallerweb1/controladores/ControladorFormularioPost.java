package ar.edu.unlam.tallerweb1.controladores;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.PostMascotaPerdida;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioGuardarPost;

@Controller
public class ControladorFormularioPost{
	private ServicioGuardarPost guardarPost;
	
	
	@Autowired
	public ControladorFormularioPost(ServicioGuardarPost servicioGuardarPost){
		this.guardarPost = servicioGuardarPost;
	}
	
	@RequestMapping(path = "/postear-perdida", method = RequestMethod.GET)
	public ModelAndView irAPostearPerdida() {
		return new ModelAndView("postear-perdida");
	}
	
	@ModelAttribute("guardarPost") 
	public PostMascotaPerdida getPostMascotaPerdida(){ 
	    return new PostMascotaPerdida();
	}
	@RequestMapping(path = "/guardar-post", method = RequestMethod.POST)
	public ModelAndView guardarPost(@ModelAttribute("guardarPost") PostMascotaPerdida post,BindingResult result, HttpServletRequest request) {
		
		guardarPost.guardarPost(post);
		
		return new ModelAndView("redirect:/");
	}
}