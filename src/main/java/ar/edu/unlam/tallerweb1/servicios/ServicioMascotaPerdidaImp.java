package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Anuncio;
import ar.edu.unlam.tallerweb1.modelo.Mascota;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service("ServicioMascotaPerdida")
@Transactional
public class ServicioMascotaPerdidaImp implements ServicioMascotaPerdida {
    private ServicioAnuncio servicioAnuncio;
    private ServicioMascota servicioMascota;
    private ServicioLogin servicioLogin;

    @Autowired
    public ServicioMascotaPerdidaImp(ServicioMascota servicioMascota, ServicioAnuncio servicioAnuncio, ServicioLogin servicioLogin) {
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
    public void borrarAnuncioById(Long id) {
        servicioAnuncio.borrarAnuncioById(id);
    }

    @Override
    public List<Anuncio> getListaAnuncio() {
        return servicioAnuncio.getAnuncios();
    }

    @Override
    public List<Anuncio> getListaAnuncioByUsuario(HttpServletRequest request) {
        Usuario usuario = servicioLogin.obtenerUsuarioConectado(request);
        return servicioAnuncio.getAnunciosByUsuario(usuario);
    }

    @Override
    public List<Mascota> getMascotaDeUsuario(HttpServletRequest request) {
        Usuario usuario = servicioLogin.obtenerUsuarioConectado(request);
        return servicioMascota.getListaMascotaDeUsuario(usuario);
    }

    @Override
    public Anuncio getAnuncioById(Long idanuncio) {
        return servicioAnuncio.getAnuncioById(idanuncio);
    }
}
