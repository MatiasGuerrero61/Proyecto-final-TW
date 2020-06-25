package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Anuncio;
import ar.edu.unlam.tallerweb1.modelo.Mensaje;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioMensajeria;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ControladorMensajeria {

    private ServicioMensajeria servMensajeria;
    private ServicioUsuario servicioUsuario;
    private ServicioLogin servicioLogin;

    @Autowired
    public ControladorMensajeria(ServicioMensajeria servMensajeria,
                                 ServicioUsuario servicioUsuario,
                                 ServicioLogin servicioLogin) {
        this.servMensajeria = servMensajeria;
        this.servicioUsuario = servicioUsuario;
        this.servicioLogin = servicioLogin;
    }

    @RequestMapping(path = "/crear-mensaje", method = RequestMethod.GET)
    public ModelAndView createmensaje(@RequestParam("usuario") Long id,
                                      @RequestParam("asunto") String asunto){
        Usuario destinatario = servicioUsuario.obtenerUsuarioPorId(id);
        ModelMap modelo = new ModelMap();
        modelo.put("usuario", destinatario);
        if(!asunto.isEmpty()){ modelo.put("asunto",asunto);}
        return new ModelAndView("mensajeria/crear-mensaje", modelo);
    }

    @ModelAttribute("mensaje")
    public Mensaje getMensaje() {
        return new Mensaje();
    }

    @RequestMapping(path = "/crear-mensaje", method = RequestMethod.POST)
    public ModelAndView enviarMensaje(@ModelAttribute("mensaje") Mensaje mensaje,
                                      @RequestParam("dest") Long id,
                                      BindingResult result,
                                      HttpServletRequest request){
        Usuario destinatario = servicioUsuario.obtenerUsuarioPorId(id);
        mensaje.setDestinatario(destinatario);
        Usuario remitente = servicioLogin.obtenerUsuarioConectado(request);
        mensaje.setRemitente(remitente);
        servMensajeria.enviarMensaje(mensaje);
        return new ModelAndView("redirect:/home");
    }

    @RequestMapping(path = "/mis-mensajes", method = RequestMethod.GET)
    public ModelAndView verListaMensajes(HttpServletRequest request,
                                        @RequestParam("bandeja") String bandeja){
        Usuario actual = servicioLogin.obtenerUsuarioConectado(request);

        List<Mensaje> mismensajes = servMensajeria.getMensajes(actual,bandeja);

        ModelMap modelo = new ModelMap();
        if(mismensajes != null){
            modelo.put("mensajes", mismensajes);
        }
        else {
            modelo.put("error","No tienes mensajes!");
        }
        return new ModelAndView("mensajeria/mis-mensajes", modelo);
    }

    @RequestMapping(path = "/mensaje", method = RequestMethod.GET)
    public ModelAndView verMensaje(@RequestParam("cod") Long id){
         Mensaje mensaje = servMensajeria.getMensajeById(id);
         ModelMap modelo = new ModelMap();
         modelo.put("mensaje", mensaje);
         return new ModelAndView("mensajeria/vista-mensaje", modelo);
    }
}
