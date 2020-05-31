package ar.edu.unlam.tallerweb1.persistencia;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Producto;
import ar.edu.unlam.tallerweb1.modelo.Tienda;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioTienda;

public class TestTienda extends SpringTest {	
	
	@Test
	@Transactional
	@Rollback
	public void TestBuscarProducto(){
		Tienda tienda = new Tienda();
		
		tienda.setRazonSocial("la juguetona");
		
		Producto producto1 =  new Producto();
		producto1.setTienda(tienda);
		producto1.setNombre("mordedor de goma");
		producto1.setImporte(new BigDecimal("500"));
		
		Producto producto2 =  new Producto();
		producto2.setTienda(tienda);
		producto2.setNombre("soga");
		producto2.setImporte(new BigDecimal("250"));
		
		Producto producto3 =  new Producto();
		producto3.setTienda(tienda);
		producto3.setNombre("mordedor de acero");
		producto3.setImporte(new BigDecimal("700"));
		
		Producto producto4 =  new Producto();
		producto4.setTienda(tienda);
		producto4.setNombre("mordedor");
		producto4.setImporte(new BigDecimal("200"));
		
		Producto producto5 =  new Producto();
		producto5.setTienda(tienda);
		producto5.setNombre("pelota");
		producto5.setImporte(new BigDecimal("620"));
		
		final Session ssion = session();
		ssion.save(tienda);
		ssion.save(producto1);
		ssion.save(producto2);
		ssion.save(producto3);
		ssion.save(producto4);
		ssion.save(producto5);
		
		String nombre = "mordedor";
		
		List<Producto> productos = ssion.createCriteria(Producto.class).
				add(Restrictions.eq("tienda",tienda)).add(Restrictions.like("nombre",nombre+"%")).list();
		
		assertThat(productos).hasSize(3);
		
		List<Producto> aux = new ArrayList<Producto>();
		aux.add(producto3);
		aux.add(producto5);
		aux.add(producto1);
		aux.add(producto2);
		aux.add(producto4);
		
		
		
		List<Producto> productosOrd = ssion.createCriteria(Producto.class).addOrder(Order.desc("importe")).list();
		
		assertEquals(aux,productosOrd);
		
	}
}