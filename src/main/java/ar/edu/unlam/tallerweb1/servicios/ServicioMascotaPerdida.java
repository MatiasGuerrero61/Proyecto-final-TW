package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Anuncio;
import ar.edu.unlam.tallerweb1.modelo.Mascota;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ServicioMascotaPerdida {
    List<Mascota> getListaMascotas();

    Mascota getMascotaById(long id);

    void guardarAnuncio(Anuncio anuncio);

    List<Anuncio> getListaAnuncio();
    List<Anuncio> getListaAnuncioByUsuario(HttpServletRequest request);

    List<Mascota> getMascotaDeUsuario(HttpServletRequest request);
}
