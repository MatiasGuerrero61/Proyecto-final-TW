package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Album;
import ar.edu.unlam.tallerweb1.modelo.Imagen;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface RepositorioAlbum {
	
	Album crearAlbum(String nombre, Usuario propietario);
	Album buscarOCrearAlbumDePerfil(Usuario propietario);
	Imagen guardarImagen(String nombre, String descripcion, Album album);
}
