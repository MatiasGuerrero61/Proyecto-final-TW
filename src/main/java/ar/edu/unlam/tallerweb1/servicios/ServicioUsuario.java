package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Imagen;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface ServicioUsuario {
	Imagen actualizarFotoDePerfil(Usuario usuario, Imagen foto);

	Usuario obtenerUsuarioPorId(Long id);

}
