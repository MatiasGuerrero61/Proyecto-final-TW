package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.PostMascotaPerdida;
import ar.edu.unlam.tallerweb1.servicios.ServicioObtenerListaPerdida;

@Controller
public class ControladorListaPerdida {

	private ServicioObtenerListaPerdida servicioLista;
	
	@Autowired
	public ControladorListaPerdida(ServicioObtenerListaPerdida serviciolista) {
		this.servicioLista = serviciolista;
	}
	
	@RequestMapping("/lista-perdida")
	public ModelAndView irAListaPerdida() {
		
		ModelMap model = new ModelMap();
		List<PostMascotaPerdida> posts = servicioLista.obtenerListaPerdida();
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
