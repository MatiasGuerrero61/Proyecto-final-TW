package ar.edu.unlam.tallerweb1.repositorios;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.criterion.Restrictions;

import ar.edu.unlam.tallerweb1.modelo.Album;
import ar.edu.unlam.tallerweb1.modelo.Imagen;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Repository("repositorioAlbum")
public class RepositorioAlbumImpl implements RepositorioAlbum {
	
	
	static final String ALBUM_PRINCIPAL = "Fotos de perfil";
	private SessionFactory sessionFactory;
	
    @Autowired
    public RepositorioAlbumImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
    @Override
	public Album crearAlbum(String nombre, Usuario propietario) {
		
    	if(nombre == ALBUM_PRINCIPAL) {
    		return null;
    	}
    	final Session session = sessionFactory.getCurrentSession();
    	Album albumCreado = new Album();
		albumCreado.setNombre(nombre);
		albumCreado.setPropietario(propietario);
		
		session.save(albumCreado);
		return session.get(Album.class, albumCreado.getId());
	}
    
    @Override
    public Album buscarOCrearAlbumDePerfil(Usuario propietario) {
    	
    	final Session session = sessionFactory.getCurrentSession();
    	Album albumDePerfil = (Album) session.createCriteria(Album.class)
    			.add(Restrictions.eq("propietario", propietario))
    			.add(Restrictions.eq("nombre",ALBUM_PRINCIPAL)).uniqueResult();
    	
    	if (albumDePerfil != null) {
    		return albumDePerfil;
    	}
    	else {
    		albumDePerfil = new Album();
    		albumDePerfil.setNombre(ALBUM_PRINCIPAL);
    		albumDePerfil.setPropietario(propietario);
    		session.save(albumDePerfil);
    		return session.get(Album.class,albumDePerfil.getId());    		
    	}

    }

	@Override
	public Imagen guardarImagen(String nombre, String descripcion, Album album) {
		final Session session = sessionFactory.getCurrentSession();
		Imagen imagen = new Imagen();
		imagen.setAlbum(album);
		imagen.setNombre(nombre);
		imagen.setDescripcion(descripcion);
		session.save(imagen);
		return session.get(Imagen.class, imagen.getId());
	}

}
