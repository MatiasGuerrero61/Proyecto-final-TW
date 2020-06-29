package ar.edu.unlam.tallerweb1.persistencia;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Tienda;
import ar.edu.unlam.tallerweb1.servicios.ServicioTienda;

public class TestServicioTienda extends SpringTest  {
	@Autowired
	ServicioTienda servicioTienda;
	
	@Test @Transactional
	@Rollback
	public void testQueNoRetornaNullCuandoNoHayTiendas() {
		List<Tienda> tiendas = this.servicioTienda.listarTiendas();
		assertThat(tiendas).isNotNull();
	}	
	
	@Test @Transactional
	@Rollback
	public void testQueRetornaEmptyCuandoNoHayTiendas() {
		List<Tienda> tiendas = this.servicioTienda.listarTiendas();
		assertThat(tiendas).isEmpty();
	}	
	
	@Test @Transactional
	@Rollback
	public void testQueListaTiendas() {
		Tienda tienda1 = new Tienda();
		tienda1.setRazonSocial("Cachorros felices");
		Tienda tienda2 = new Tienda();
		tienda2.setRazonSocial("Felinos felices");
		session().save(tienda1);
		session().save(tienda2);
		List<Tienda> tiendas = this.servicioTienda.listarTiendas();
		assertThat(tiendas).hasSize(2);
	}

}
