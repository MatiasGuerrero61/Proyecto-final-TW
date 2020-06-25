package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Mensaje;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

import java.util.List;

public interface ServicioMensajeria {

    void enviarMensaje(Mensaje mensaje);

    List<Mensaje> getMensajes(Usuario usuario, String bandeja);

    Mensaje getMensajeById(Long id);
}
