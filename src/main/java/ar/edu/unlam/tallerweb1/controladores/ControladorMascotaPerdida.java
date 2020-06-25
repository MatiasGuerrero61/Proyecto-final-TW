package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.servicios.ServicioArchivos;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioMascotaPerdida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping
public class ControladorMascotaPerdida {

    private ServicioMascotaPerdida servicioMascotaPerdida;
    private ServicioArchivos servicioArchivos;
    private ServicioLogin servicioLogin;

    @Autowired
    public ControladorMascotaPerdida(ServicioMascotaPerdida servicioMascotaPerdida, ServicioLogin servicioLogin, ServicioArchivos servicioArchivos) {
        this.servicioMascotaPerdida = servicioMascotaPerdida;
        this.servicioArchivos = servicioArchivos;
        this.servicioLogin = servicioLogin;
    }

    @RequestMapping(path = "/creacion-anuncio", method = RequestMethod.GET)
    public ModelAndView irAPostearPerdida(HttpServletRequest request) {
        List<Mascota> misMascotas = this.servicioMascotaPerdida.getMascotaDeUsuario(request);
        ModelMap model = new ModelMap();
        model.put("mascotas", misMascotas);

        return new ModelAndView("mascotas-perdidas/postear-perdida", model);
    }

    @ModelAttribute("guardarAnuncio")
    public Anuncio getAnuncio() {
        return new Anuncio();
    }

    @RequestMapping(path = "/anuncios", method = RequestMethod.POST)
    public ModelAndView guardarPost(@ModelAttribute("guardarAnuncio") Anuncio anuncio,
                                    BindingResult result,
                                    @RequestParam("imagen") MultipartFile file,
                                    HttpServletRequest request,
                                    @RequestParam("mascotaId") Long id
    ) {
        Mascota mascota = this.servicioMascotaPerdida.getMascotaById(id);
        anuncio.setMascota(mascota);

        String path = request.getSession().getServletContext().getRealPath("/files/");
        Usuario usuario = servicioLogin.obtenerUsuarioConectado(request);

        if (usuario != null) {
            Album albumDePerfil = servicioArchivos.buscarOCrearAlbumDePerfil(usuario);

            if (albumDePerfil != null) {
                Imagen imagen = servicioArchivos.guardarImagen(file, path, albumDePerfil);
                if(imagen != null){
                    anuncio.setFotoDeAnuncio(imagen);
                }
            }
        }

        servicioMascotaPerdida.guardarAnuncio(anuncio);

        return new ModelAndView("redirect:/home");
    }

    @RequestMapping(value = "/anuncios", method = RequestMethod.GET)
    public ModelAndView irAListaPerdida() {
        List<Anuncio> anuncios = servicioMascotaPerdida.getListaAnuncio();
        ModelMap modelMap = new ModelMap();
        modelMap.put("anuncios", anuncios);

        return new ModelAndView("mascotas-perdidas/lista-perdida", modelMap);
    }
}
