package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.Mascota;


public interface ServicioMascota {

	List<Mascota> getListaMascota();
	Mascota getMascotaById(Long id);
	List<Mascota> getListaMascotaDeUsuario(Usuario usuario);
	
}
