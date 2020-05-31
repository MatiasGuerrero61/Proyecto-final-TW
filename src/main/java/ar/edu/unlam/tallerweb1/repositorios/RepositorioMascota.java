package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Mascota;

@Repository
public interface RepositorioMascota  {
	List<Mascota> getMacota();
	void saveMascota(Mascota mascota);
	void updateMascota(Mascota mascota);
	void deleteMascota(Mascota mascota);
}