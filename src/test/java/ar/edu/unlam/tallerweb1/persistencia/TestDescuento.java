package ar.edu.unlam.tallerweb1.persistencia;

import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Descuento;
import ar.edu.unlam.tallerweb1.servicios.ServicioDescuento;

public class TestDescuento extends SpringTest {
	
	@Autowired
	ServicioDescuento servicioDescuento;
	
	@Test
	@Transactional
	@Rollback
	public void TestDescuentoValido() {
		Descuento descuento = new Descuento();
		descuento.setCodigo("JAVA2020");
		descuento.setValidez(true);
		session().save(descuento);
		Descuento descuentoBuscado = servicioDescuento.verificarValidez("JAVA2020");
		assertThat(descuentoBuscado).isNotNull();
	}
	
	@Test
	@Transactional
	@Rollback
	public void TestDescuentoNoValido() {
		Descuento descuento = new Descuento();
		descuento.setCodigo("JAVA2020");
		descuento.setValidez(false);
		session().save(descuento);
		Descuento descuentoBuscado = servicioDescuento.verificarValidez("JAVA2020");
		assertThat(descuentoBuscado).isNull();
	}
	
	@Test
	@Transactional
	@Rollback
	public void TestCodigoNoValido() {
		Descuento descuento = new Descuento();
		descuento.setCodigo("JAVA2020");
		descuento.setValidez(true);
		session().save(descuento);
		Descuento descuentoBuscado = servicioDescuento.verificarValidez("Java2020");		
		assertThat(descuentoBuscado).isNull();
	}
	

}
