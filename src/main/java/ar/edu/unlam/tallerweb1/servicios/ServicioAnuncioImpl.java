package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Anuncio;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioAnuncio;

@Service("ServicioAnuncio")
@Transactional
public class ServicioAnuncioImpl implements ServicioAnuncio{

	private RepositorioAnuncio repoAnuncio;
	
	@Autowired
	public ServicioAnuncioImpl(RepositorioAnuncio repositorioAnuncio) {
		this.repoAnuncio = repositorioAnuncio;
	}

	@Override
	public void guardar(Anuncio anuncio) {
		repoAnuncio.guardar(anuncio);
		
	}

	@Override
	public List<Anuncio> getAnuncios() {
		return repoAnuncio.obtenerAnuncios();
	}

	@Override
	public List<Anuncio> getAnunciosByUsuario(Usuario usuario) {
		return repoAnuncio.obtenerAnunciosByUsuario(usuario);
	}
}