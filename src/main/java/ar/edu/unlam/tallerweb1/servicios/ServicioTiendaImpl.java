package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Filtro;
import ar.edu.unlam.tallerweb1.modelo.Producto;
import ar.edu.unlam.tallerweb1.modelo.Tienda;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioTienda;

@Service("servicioTienda")
@Transactional
public class ServicioTiendaImpl implements ServicioTienda{
	
	private RepositorioTienda servicioTiendaDao;

	@Autowired
	public ServicioTiendaImpl(RepositorioTienda servicioTiendaDao){
		this.servicioTiendaDao = servicioTiendaDao;
	}

	@Override
	public List<Tienda> listarTiendas() {
		return servicioTiendaDao.listarTiendas();
	}

	@Override
	public Tienda buscarTiendaPorId(Long id) {
		return servicioTiendaDao.buscarTiendaPorId(id);
	}

	@Override
	public List<Producto> listarProductosDeTienda(Tienda tienda) {
		return servicioTiendaDao.listarProductosDeTienda(tienda);
	}

	@Override
	public List<Producto> filtrarProductos(Filtro filtros, Tienda tienda) {
		return servicioTiendaDao.filtrarProductos(filtros, tienda);
	}
	


}
