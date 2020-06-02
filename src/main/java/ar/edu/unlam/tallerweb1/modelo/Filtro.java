package ar.edu.unlam.tallerweb1.modelo;

import java.math.BigDecimal;

public class Filtro {

	private String nombre;
	private String precioMax;
	private String precioMin;
	private String categoria;
	private String order;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPrecioMax() {
		return precioMax;
	}
	public void setPrecioMax(String precioMax) {
		if(precioMax != "") {
			this.precioMin = precioMax;
		}
		else {
			this.precioMax = "9999";
		}
	}
	public String getPrecioMin() {
		return precioMin;
	}
	public void setPrecioMin(String precioMin) {
		if(precioMin != "") {
			this.precioMin = precioMin;
		}
		else {
			this.precioMin = "0";
		}
		
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	
	
}
