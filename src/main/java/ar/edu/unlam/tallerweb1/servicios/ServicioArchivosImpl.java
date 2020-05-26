package ar.edu.unlam.tallerweb1.servicios;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Arrays;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import ar.edu.unlam.tallerweb1.modelo.Album;
import ar.edu.unlam.tallerweb1.modelo.Imagen;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioAlbum;



@Service("servicioArchivos")
@Transactional
public class ServicioArchivosImpl implements ServicioArchivos {
	
	private RepositorioAlbum servicioArchivosAlbumDao;

	@Autowired
	public ServicioArchivosImpl(RepositorioAlbum servicioArchivosAlbumDao){
		this.servicioArchivosAlbumDao = servicioArchivosAlbumDao;

	}
	
	private boolean esImagenValida(MultipartFile file) {
		
		List<String> tiposDeImagenesValidas = Arrays.asList("image/png", "image/jpeg");
		List<String> extensionesValidasDeImagen = Arrays.asList("jpg", "png");
		String extensionDeArchivo = FilenameUtils.getExtension( file.getOriginalFilename() ).toString();
		String tipoDeArchivo = file.getContentType().toString();

			if (  tiposDeImagenesValidas.contains(tipoDeArchivo) && extensionesValidasDeImagen.contains(extensionDeArchivo)) {
			return true;
		}
		return false;
	}
	
	@Override
	public Imagen guardarImagen(MultipartFile file, String path, Album album){
		
	    try {
	    	
	    	if(!file.isEmpty() && esImagenValida(file) ){ 
				
	    		String nombreRandom = UUID.randomUUID().toString() + '.' + FilenameUtils.getExtension(file.getOriginalFilename());
	            Path pathcompleto = Paths.get( path + nombreRandom ); 
	            Files.write(pathcompleto,file.getBytes());

	            return servicioArchivosAlbumDao.guardarImagen(nombreRandom, " ", album);
	    	}
	    	
	    	return null;
	    	
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public Album buscarOCrearAlbumDePerfil(Usuario propietario) {
		return servicioArchivosAlbumDao.buscarOCrearAlbumDePerfil(propietario);
	}
	
	
}
