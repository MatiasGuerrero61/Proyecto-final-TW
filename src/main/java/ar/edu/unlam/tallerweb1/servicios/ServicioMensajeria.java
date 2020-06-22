package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Mensaje;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

import java.util.List;

public interface ServicioMensajeria {

    public void enviarMensaje(Mensaje mensaje);

    public List<Mensaje> getMensajes(Usuario usuario);
}
