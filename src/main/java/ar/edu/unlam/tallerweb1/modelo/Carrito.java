package ar.edu.unlam.tallerweb1.modelo;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Carrito {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Usuario usuario;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Tienda tienda;
	
	public Tienda getTienda() {
		return tienda;
	}
	public void setTienda(Tienda tienda) {
		this.tienda = tienda;
	}
	private Date fechaYHoraDeCompra;
	
	@Enumerated(EnumType.ORDINAL)
	private EnumEstadoDeCompra estado;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public EnumEstadoDeCompra getEstado() {
		return estado;
	}
	public void setEstado(EnumEstadoDeCompra estado) {
		this.estado = estado;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Date getFechaYHoraDeCompra() {
		return fechaYHoraDeCompra;
	}
	public void setFechaYHoraDeCompra(Date fechaYHoraDeCompra) {
		this.fechaYHoraDeCompra = fechaYHoraDeCompra;
	}

}
