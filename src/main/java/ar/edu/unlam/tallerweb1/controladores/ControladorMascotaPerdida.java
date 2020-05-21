package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

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
import ar.edu.unlam.tallerweb1.servicios.ServicioPost;

@Controller
@RequestMapping("/mascota-perdida")
public class ControladorMascotaPerdida{
	private ServicioPost servicioPost;
	
	@Autowired
	public ControladorMascotaPerdida(ServicioPost servicioPost){
		this.servicioPost = servicioPost;
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
		
		servicioPost.guardarPost(post);
		
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping("/lista-perdida")
	public ModelAndView irAListaPerdida() {
		
		ModelMap model = new ModelMap();
		List<PostMascotaPerdida> posts = servicioPost.obtenerListaPerdida();
		if(posts != null) {
			model.put("posts", posts);
			return new ModelAndView("lista-perdida", model);
		}
		else {// si el usuario no existe agrega un mensaje de error en el modelo.
			model.put("error", "No hay ningun posteo");
	}
	return new ModelAndView("lista-perdida", model);
		
	}
}
