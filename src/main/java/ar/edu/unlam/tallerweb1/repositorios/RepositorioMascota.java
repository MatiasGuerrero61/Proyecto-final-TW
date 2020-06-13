package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Mascota;

public interface RepositorioMascota  {
	List<Mascota> getMascotas();
	List<Mascota> getMascotasDeUsuario(Usuario usuario);
	Mascota getMascotaById(Long id);
	void saveMascota(Mascota mascota);
	void updateMascota(Mascota mascota);
	void deleteMascota(Mascota mascota);
}