package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.PostMascotaPerdida;

public interface ServicioPost  {
	void guardarPost(PostMascotaPerdida post);
	List<PostMascotaPerdida> obtenerListaPerdida();
}