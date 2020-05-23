package ar.edu.unlam.tallerweb1.servicios;

import org.springframework.web.multipart.MultipartFile;

import ar.edu.unlam.tallerweb1.modelo.Album;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface ServicioArchivos {
	boolean guardarImagen(MultipartFile file, String path, Album album);
	Album buscarOCrearAlbumDePerfil(Usuario propietario);
}
