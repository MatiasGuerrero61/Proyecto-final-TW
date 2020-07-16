package ar.edu.unlam.tallerweb1.modelo;

import java.math.BigDecimal;

public class Facturacion {
	private Long idTienda;
	private BigDecimal importeTotal;
	private String nombreDeTienda;
	private Long cantidadDeVentas;
	public Long getIdTienda() {
		return idTienda;
	}
	public void setIdTienda(Long idTienda) {
		this.idTienda = idTienda;
	}
	public BigDecimal getImporteTotal() {
		return importeTotal;
	}
	public void setImporteTotal(BigDecimal importeTotal) {
		this.importeTotal = importeTotal;
	}
	public String getNombreDeTienda() {
		return nombreDeTienda;
	}
	public void setNombreDeTienda(String nombreDeTienda) {
		this.nombreDeTienda = nombreDeTienda;
	}
	public Long getCantidadDeVentas() {
		return cantidadDeVentas;
	}
	public void setCantidadDeVentas(Long cantidadDeVentas) {
		this.cantidadDeVentas = cantidadDeVentas;
	}

}
