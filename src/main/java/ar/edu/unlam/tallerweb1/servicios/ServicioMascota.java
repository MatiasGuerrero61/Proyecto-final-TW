package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.Mascota;


public interface ServicioMascota {

	List<Mascota> getListaMascota();
	Mascota getMascotaById(Long id);
	
}
