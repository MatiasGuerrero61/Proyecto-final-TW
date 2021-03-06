package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Anuncio;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

import java.util.List;

public interface ServicioAnuncio {

    void guardar(Anuncio anuncio);

    void borrarAnuncioById(Long id);

    List<Anuncio> getAnuncios();

    List<Anuncio> getAnunciosByUsuario(Usuario usuario);

    Anuncio getAnuncioById(Long idanuncio);
}
