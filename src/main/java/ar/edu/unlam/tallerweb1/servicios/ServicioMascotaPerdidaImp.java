package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Anuncio;
import ar.edu.unlam.tallerweb1.modelo.Mascota;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import javax.management.modelmbean.ModelMBean;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service("ServicioMascotaPerdida")
@Transactional
public class ServicioMascotaPerdidaImp implements ServicioMascotaPerdida {
    private ServicioAnuncio servicioAnuncio;
    private ServicioMascota servicioMascota;
    private ServicioLogin servicioLogin;

    @Autowired
    public ServicioMascotaPerdidaImp(ServicioMascota servicioMascota, ServicioAnuncio servicioAnuncio,ServicioLogin servicioLogin){
        this.servicioAnuncio = servicioAnuncio;
        this.servicioMascota = servicioMascota;
        this.servicioLogin = servicioLogin;
    }


    @Override
    public List<Mascota> getListaMascotas() {
        return servicioMascota.getListaMascota();
    }

    @Override
    public Mascota getMascotaById(long id) {
        return servicioMascota.getMascotaById(id);
    }

    @Override
    public void guardarAnuncio(Anuncio anuncio) {
        servicioAnuncio.guardar(anuncio);
    }

    @Override
    public ModelMap getListaAnuncio() {
        List<Anuncio> anuncios = servicioAnuncio.getAnuncios();
        ModelMap model = new ModelMap();
        if(anuncios != null) {
            model.put("anuncios", anuncios);
        }
        else {// si el usuario no existe agrega un mensaje de error en el modelo.
            model.put("error", "No hay ningun posteo");
        }
        return model;
    }

    @Override
    public List<Mascota> getMascotaDeUsuario(HttpServletRequest request) {
        Usuario usuario = servicioLogin.obtenerUsuarioConectado(request);
        return servicioMascota.getListaMascotaDeUsuario(usuario);
    }
}
