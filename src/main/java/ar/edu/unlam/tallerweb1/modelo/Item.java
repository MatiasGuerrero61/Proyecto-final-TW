package ar.edu.unlam.tallerweb1.modelo;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Producto producto;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Carrito carrito;
	
	private Integer cantidad;
	//un producto puede cambiar su precio con el pasar del tiempo, por lo cual, cuando haga efectiva una compra
	//vamoa a guardar el precio al que se adquirió el producto
	private BigDecimal precioDeCompra;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Carrito getCarrito() {
		return carrito;
	}

	public void setCarrito(Carrito carrito) {
		this.carrito = carrito;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getPrecioDeCompra() {
		return precioDeCompra;
	}

	public void setPrecioDeCompra(BigDecimal precioDeCompra) {
		this.precioDeCompra = precioDeCompra;
	}

}
