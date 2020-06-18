package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Descuento;

public interface RepositorioDescuento {
	 Descuento verificarValidez(String codigo);
	 void invalidarDescuento(Descuento descuento);
	 void reactivarDescuento(Descuento descuento);
}
