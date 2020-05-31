package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Mascota  {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;
private String animal;
private String caracteristica;
private String nombre;
@ManyToOne
private Usuario duenio;
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getAnimal() {
	return animal;
}
public void setAnimal(String animal) {
	this.animal = animal;
}
public String getCaracteristica() {
	return caracteristica;
}
public void setCaracteristica(String caracteristica) {
	this.caracteristica = caracteristica;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public Usuario getDuenio() {
	return duenio;
}
public void setDuenio(Usuario duenio) {
	this.duenio = duenio;
}



}