package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Mensaje;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

import java.util.List;

public interface RepositorioMensaje {

    void postMensaje(Mensaje mensaje);

    Mensaje getMensajeById(Long id);

    List<Mensaje> getMensajesDeUsuario(Usuario usuario);
}
