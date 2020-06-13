package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Mascota;

@Repository
public class RepositorioMascotaImpl implements RepositorioMascota {

	private SessionFactory sessionFactory;
	
    @Autowired
    public RepositorioMascotaImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
    
	@Override
	public void saveMascota(Mascota mascota) {
		
		
	}

	@Override
	public void updateMascota(Mascota mascota) {
		
		
	}

	@Override
	public void deleteMascota(Mascota mascota) {
		
		
	}

	@Override
	public List<Mascota> getMascotas() {
		final Session session = sessionFactory.getCurrentSession();
		List<Mascota> mascotas = session.createCriteria(Mascota.class).list();
		return mascotas;
	}

	@Override
	public List<Mascota> getMascotasDeUsuario(Usuario usuario) {
    	final Session session = sessionFactory.getCurrentSession();
    	List<Mascota> mascotas = session.createCriteria(Mascota.class).add(Restrictions.eq("duenio", usuario)).list();
		return mascotas;
	}

	@Override
	public Mascota getMascotaById(Long id) {
		final Session session = sessionFactory.getCurrentSession();
		Mascota mascota = session.get(Mascota.class, id);
		return mascota;
	}

}