package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Carrito;
import ar.edu.unlam.tallerweb1.modelo.EnumEstadoDeCompra;
import ar.edu.unlam.tallerweb1.modelo.Tienda;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface RepositorioCarrito {	
	Carrito buscarCarritoPendiente(Usuario usuario, Tienda tienda);
	Carrito crearCarrito(Usuario usuario, Tienda tienda);	
	Carrito obtenerCarrito(Long idCarrito);
}
