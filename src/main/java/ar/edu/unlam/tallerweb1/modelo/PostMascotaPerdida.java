package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PostMascotaPerdida  {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;
private String titulo;
private String raza;
private String descripcion;
private String masInformacion;

public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getRaza() {
	return raza;
}
public void setRaza(String raza) {
	this.raza = raza;
}
public String getDescripcion() {
	return descripcion;
}
public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
}
public String getMasInformacion() {
	return masInformacion;
}
public void setMasInformacion(String masInformacion) {
	this.masInformacion = masInformacion;
}
public String getTitulo() {
	return titulo;
}
public void setTitulo(String titulo) {
	this.titulo = titulo;
}

}