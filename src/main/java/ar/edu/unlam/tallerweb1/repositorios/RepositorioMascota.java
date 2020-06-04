package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Mascota;

public interface RepositorioMascota  {
	List<Mascota> getMascotas();
	Mascota getMascotaById(Long id);
	void saveMascota(Mascota mascota);
	void updateMascota(Mascota mascota);
	void deleteMascota(Mascota mascota);
}