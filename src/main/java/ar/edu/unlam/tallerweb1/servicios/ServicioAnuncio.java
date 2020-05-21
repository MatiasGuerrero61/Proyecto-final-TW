package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Anuncio;

public interface ServicioAnuncio {
	
	void guardar(Anuncio anuncio);
	List<Anuncio> getAnuncios();
}
