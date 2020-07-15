package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;

@Entity
public class Anuncio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String titulo;
	private String informacion;
	private Double recompensa;
	@ManyToOne
	private Usuario duenio;
	@OneToOne
	private Mascota mascota;
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
	public Double getRecompensa() {return recompensa;}
	public void setRecompensa(Double recompensa) {this.recompensa = recompensa;}
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
	public Usuario getDuenio() {return duenio;}
	public void setDuenio(Usuario duenio) {this.duenio = duenio;}
	
	
	
	
}
