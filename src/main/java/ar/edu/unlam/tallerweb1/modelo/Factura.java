package ar.edu.unlam.tallerweb1.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Factura {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDateTime fechaYHoraDeCompra;
	
	/* Hasta que no se haga efectiva una compra, muchos carritos pueden probar un mismo codigo de descuento.
	 * Una vez usado, se anula en el resto de carritos */
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Descuento descuento;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Carrito carrito;
	
	@Enumerated(EnumType.ORDINAL)
	private EnumEstadoDeCompra estado;
	
	private BigDecimal SubtotalSinDescuentos;
	private BigDecimal SubtotalDescuentos;
	private BigDecimal importeFinal;
	
	public BigDecimal getSubtotalSinDescuentos() {
		return SubtotalSinDescuentos;
	}

	public void setSubtotalSinDescuentos(BigDecimal subtotalSinDescuentos) {
		SubtotalSinDescuentos = subtotalSinDescuentos;
	}

	public BigDecimal getSubtotalDescuentos() {
		return SubtotalDescuentos;
	}

	public void setSubtotalDescuentos(BigDecimal subtotalDescuentos) {
		SubtotalDescuentos = subtotalDescuentos;
	}

	public BigDecimal getImporteFinal() {
		return importeFinal;
	}

	public void setImporteFinal(BigDecimal importeFinal) {
		this.importeFinal = importeFinal;
	}

	public Descuento getDescuento() {
		return descuento;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Carrito getCarrito() {
		return carrito;
	}

	public void setCarrito(Carrito carrito) {
		this.carrito = carrito;
	}

	public void setDescuento(Descuento descuento) {
		this.descuento = descuento;
	}

	public LocalDateTime getFechaYHoraDeCompra() {
		return fechaYHoraDeCompra;
	}

	public void setFechaYHoraDeCompra(LocalDateTime fechaYHoraDeCompra) {
		this.fechaYHoraDeCompra = fechaYHoraDeCompra;
	}

	public EnumEstadoDeCompra getEstado() {
		return estado;
	}

	public void setEstado(EnumEstadoDeCompra estado) {
		this.estado = estado;
	}


}
