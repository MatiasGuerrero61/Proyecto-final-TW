package ar.edu.unlam.tallerweb1.modelo;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Descuento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String codigo;
	private Integer porcentajeDeDescuento;
	private BigDecimal tope;
	private boolean validez;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Integer getPorcentajeDeDescuento() {
		return porcentajeDeDescuento;
	}
	public void setPorcentajeDeDescuento(Integer porcentajeDeDescuento) {
		this.porcentajeDeDescuento = porcentajeDeDescuento;
	}
	public BigDecimal getTope() {
		return tope;
	}
	public void setTope(BigDecimal tope) {
		this.tope = tope;
	}
	public boolean isValidez() {
		return validez;
	}
	public void setValidez(boolean validez) {
		this.validez = validez;
	}

}
