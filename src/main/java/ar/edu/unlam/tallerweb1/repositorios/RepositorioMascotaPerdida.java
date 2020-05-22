package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.PostMascotaPerdida;

public interface RepositorioMascotaPerdida  {

	List<PostMascotaPerdida> getListaMascotasPerdidas();
	void saveMascotasPerdidas(PostMascotaPerdida post);
}