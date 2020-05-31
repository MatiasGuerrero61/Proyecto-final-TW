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

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Producto;
import ar.edu.unlam.tallerweb1.modelo.Tienda;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioTienda;

public class TestTienda extends SpringTest {	
	
	Tienda tienda;
	Producto producto1;
	Producto producto2;
	Producto producto3;
	Producto producto4;
	Producto producto5;
	Session ssion;
	
	@Before
	public void set() {
		tienda = new Tienda();
		tienda.setRazonSocial("La Juguetona");
		
		producto1 =  new Producto();
		producto1.setTienda(tienda);
		producto1.setNombre("mordedor de goma");
		producto1.setImporte(new BigDecimal("500"));
		
		producto2 =  new Producto();
		producto2.setTienda(tienda);
		producto2.setNombre("pelota de goma");
		producto2.setImporte(new BigDecimal("250"));
		
		producto3 =  new Producto();
		producto3.setTienda(tienda);
		producto3.setNombre("mordedor de acero");
		producto3.setImporte(new BigDecimal("700"));
		
		producto4 =  new Producto();
		producto4.setTienda(tienda);
		producto4.setNombre("mordedor");
		producto4.setImporte(new BigDecimal("200"));
		
		producto5 =  new Producto();
		producto5.setTienda(tienda);
		producto5.setNombre("pelota");
		producto5.setImporte(new BigDecimal("620"));
		
		ssion = session();
		ssion.save(producto1);
		ssion.save(producto2);
		ssion.save(producto3);
		ssion.save(producto4);
		ssion.save(producto5);
	}
	
	@Test
	@Transactional
	@Rollback
	public void TestSinFiltro(){
		Criteria criteria = ssion.createCriteria(Producto.class).add(Restrictions.eq("tienda",tienda));
		
		String filtroNombre = null;
		String filtroImporteMin = null;
		String filtroImporteMax = null;
		String filtroOrden = null;
		
		if(filtroNombre != null) {
			criteria.add(Restrictions.like("nombre",filtroNombre+"%"));
		}if(filtroImporteMax != null) {
			BigDecimal max = new BigDecimal(filtroImporteMax);
			criteria.add(Restrictions.le("importe", max));
		}if(filtroImporteMin != null) {
			BigDecimal min = new BigDecimal(filtroImporteMin);
			criteria.add(Restrictions.ge("importe", min));
		}if(filtroOrden == "asc") {
			criteria.addOrder(Order.asc("importe"));
		}else if(filtroOrden == "desc") {
			criteria.addOrder(Order.desc("importe"));
		}
		List<Producto> productosFiltrados = criteria.list();
		
		
		assertThat(productosFiltrados).hasSize(5);
	}
	
	@Test
	@Transactional
	@Rollback
	public void TestFiltrarProductoNombre(){
		Criteria criteria = ssion.createCriteria(Producto.class).add(Restrictions.eq("tienda",tienda));
		
		String filtroNombre = "pelota";
		String filtroImporteMin = null;
		String filtroImporteMax = null;
		String filtroOrden = null;
		
		if(filtroNombre != null) {
			criteria.add(Restrictions.like("nombre",filtroNombre+"%"));
		}if(filtroImporteMax != null) {
			BigDecimal max = new BigDecimal(filtroImporteMax);
			criteria.add(Restrictions.le("importe", max));
		}if(filtroImporteMin != null) {
			BigDecimal min = new BigDecimal(filtroImporteMin);
			criteria.add(Restrictions.ge("importe", min));
		}if(filtroOrden == "asc") {
			criteria.addOrder(Order.asc("importe"));
		}else if(filtroOrden == "desc") {
			criteria.addOrder(Order.desc("importe"));
		}
		List<Producto> productosFiltrados = criteria.list();
		
		assertThat(productosFiltrados).hasSize(2);
	}
	
	@Test
	@Transactional
	@Rollback
	public void TestFiltrarProductoPrecioMin(){
		Criteria criteria = ssion.createCriteria(Producto.class).add(Restrictions.eq("tienda",tienda));
		
		String filtroNombre = null;
		String filtroImporteMin = "400";
		String filtroImporteMax = null;
		String filtroOrden = null;
		
		if(filtroNombre != null) {
			criteria.add(Restrictions.like("nombre",filtroNombre+"%"));
		}if(filtroImporteMax != null) {
			BigDecimal max = new BigDecimal(filtroImporteMax);
			criteria.add(Restrictions.le("importe", max));
		}if(filtroImporteMin != null) {
			BigDecimal min = new BigDecimal(filtroImporteMin);
			criteria.add(Restrictions.ge("importe", min));
		}if(filtroOrden == "asc") {
			criteria.addOrder(Order.asc("importe"));
		}else if(filtroOrden == "desc") {
			criteria.addOrder(Order.desc("importe"));
		}
		List<Producto> productosFiltrados = criteria.list();
		
		assertThat(productosFiltrados).hasSize(3);
	}
	
	@Test
	@Transactional
	@Rollback
	public void TestFiltrarProductoPrecioMax(){
		Criteria criteria = ssion.createCriteria(Producto.class).add(Restrictions.eq("tienda",tienda));
		
		String filtroNombre = null;
		String filtroImporteMin = null;
		String filtroImporteMax = "400";
		String filtroOrden = null;
		
		if(filtroNombre != null) {
			criteria.add(Restrictions.like("nombre",filtroNombre+"%"));
		}if(filtroImporteMax != null) {
			BigDecimal max = new BigDecimal(filtroImporteMax);
			criteria.add(Restrictions.le("importe", max));
		}if(filtroImporteMin != null) {
			BigDecimal min = new BigDecimal(filtroImporteMin);
			criteria.add(Restrictions.ge("importe", min));
		}if(filtroOrden == "asc") {
			criteria.addOrder(Order.asc("importe"));
		}else if(filtroOrden == "desc") {
			criteria.addOrder(Order.desc("importe"));
		}
		List<Producto> productosFiltrados = criteria.list();
		
		assertThat(productosFiltrados).hasSize(2);
	}
	
	@Test
	@Transactional
	@Rollback
	public void TestFiltrarProductoOrdenAsc(){
		Criteria criteria = ssion.createCriteria(Producto.class).add(Restrictions.eq("tienda",tienda));
		
		String filtroNombre = null;
		String filtroImporteMin = null;
		String filtroImporteMax = null;
		String filtroOrden = "asc";
		
		if(filtroNombre != null) {
			criteria.add(Restrictions.like("nombre",filtroNombre+"%"));
		}if(filtroImporteMax != null) {
			BigDecimal max = new BigDecimal(filtroImporteMax);
			criteria.add(Restrictions.le("importe", max));
		}if(filtroImporteMin != null) {
			BigDecimal min = new BigDecimal(filtroImporteMin);
			criteria.add(Restrictions.ge("importe", min));
		}if(filtroOrden == "asc") {
			criteria.addOrder(Order.asc("importe"));
		}else if(filtroOrden == "desc") {
			criteria.addOrder(Order.desc("importe"));
		}
		List<Producto> productosFiltrados = criteria.list();
		List<Producto> productosOrdenados = new ArrayList<>();
		productosOrdenados.add(producto4);
		productosOrdenados.add(producto2);
		productosOrdenados.add(producto1);
		productosOrdenados.add(producto5);
		productosOrdenados.add(producto3);
		
		assertEquals(productosFiltrados,productosOrdenados);
	}
	
	@Test
	@Transactional
	@Rollback
	public void TestFiltrarProductoOrdenDesc(){
		Criteria criteria = ssion.createCriteria(Producto.class).add(Restrictions.eq("tienda",tienda));
		
		String filtroNombre = null;
		String filtroImporteMin = null;
		String filtroImporteMax = null;
		String filtroOrden = "desc";
		
		if(filtroNombre != null) {
			criteria.add(Restrictions.like("nombre",filtroNombre+"%"));
		}if(filtroImporteMax != null) {
			BigDecimal max = new BigDecimal(filtroImporteMax);
			criteria.add(Restrictions.le("importe", max));
		}if(filtroImporteMin != null) {
			BigDecimal min = new BigDecimal(filtroImporteMin);
			criteria.add(Restrictions.ge("importe", min));
		}if(filtroOrden == "asc") {
			criteria.addOrder(Order.asc("importe"));
		}else if(filtroOrden == "desc") {
			criteria.addOrder(Order.desc("importe"));
		}
		List<Producto> productosFiltrados = criteria.list();
		List<Producto> productosOrdenados = new ArrayList<>();
		productosOrdenados.add(producto3);
		productosOrdenados.add(producto5);
		productosOrdenados.add(producto1);
		productosOrdenados.add(producto2);
		productosOrdenados.add(producto4);
		
		assertEquals(productosFiltrados,productosOrdenados);
	}
	
	@Test
	@Transactional
	@Rollback
	public void TestFiltrarRuletaDeFiltros(){
		Criteria criteria = ssion.createCriteria(Producto.class).add(Restrictions.eq("tienda",tienda));
		
		String filtroNombre = "mordedor";
		String filtroImporteMin = "100";
		String filtroImporteMax = "600";
		String filtroOrden = "asc";
		
		if(filtroNombre != null) {
			criteria.add(Restrictions.like("nombre",filtroNombre+"%"));
		}if(filtroImporteMax != null) {
			BigDecimal max = new BigDecimal(filtroImporteMax);
			criteria.add(Restrictions.le("importe", max));
		}if(filtroImporteMin != null) {
			BigDecimal min = new BigDecimal(filtroImporteMin);
			criteria.add(Restrictions.ge("importe", min));
		}if(filtroOrden == "asc") {
			criteria.addOrder(Order.asc("importe"));
		}else if(filtroOrden == "desc") {
			criteria.addOrder(Order.desc("importe"));
		}
		List<Producto> productosFiltrados = criteria.list();
		
		
		for(Producto prod: productosFiltrados) {
			System.out.println(prod.getNombre() + " - " + prod.getImporte());
		}
		
		assertThat(productosFiltrados).hasSize(2);
	}
}