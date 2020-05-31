package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Anuncio;

public interface RepositorioAnuncio {
	
	void guardar(Anuncio anuncio);
	Anuncio obtenerAnuncioPorId(long id);
	List<Anuncio> obtenerAnuncios();
	void deleteAnuncio(Anuncio anuncio);
	void deleteAnuncioPorId(long id);
	void updateAnuncio(Anuncio anuncio);
}
