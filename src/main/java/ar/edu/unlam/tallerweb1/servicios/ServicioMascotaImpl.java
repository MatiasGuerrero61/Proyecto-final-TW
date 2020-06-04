package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Mascota;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioMascota;

@Service("servicioMascota")
@Transactional
public class ServicioMascotaImpl implements ServicioMascota{
	
	private RepositorioMascota servicioMascotaDao;
	
	@Autowired
	public ServicioMascotaImpl(RepositorioMascota repoMascota) {
		this.servicioMascotaDao = repoMascota;
	}
	@Override
	public List<Mascota> getListaMascota() {
		return this.servicioMascotaDao.getMascotas();
	}
	@Override
	public Mascota getMascotaById(Long id) {
		return this.servicioMascotaDao.getMascotaById(id);
	}

}
