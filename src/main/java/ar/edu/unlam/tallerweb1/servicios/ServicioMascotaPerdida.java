package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Anuncio;
import ar.edu.unlam.tallerweb1.modelo.Mascota;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ServicioMascotaPerdida {
    List<Mascota> getListaMascotas();
    Mascota getMascotaById(long id);
    void guardarAnuncio(Anuncio anuncio);
    ModelMap getListaAnuncio();
    List<Mascota> getMascotaDeUsuario(HttpServletRequest request);
}
