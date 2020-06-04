package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Anuncio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String titulo;
	@OneToOne
	private Mascota mascota;
	private String informacion;
	@OneToOne
	private Imagen fotoDeAnuncio;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Mascota getMascota() {
		return mascota;
	}
	public void setMascota(Mascota mascota) {
		this.mascota = mascota;
	}
	public String getInformacion() {
		return informacion;
	}
	public void setInformacion(String informacion) {
		this.informacion = informacion;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Imagen getFotoDeAnuncio() {
		return fotoDeAnuncio;
	}
	public void setFotoDeAnuncio(Imagen fotoDeAnuncio) {
		this.fotoDeAnuncio = fotoDeAnuncio;
	}
	
	
	
	
}
