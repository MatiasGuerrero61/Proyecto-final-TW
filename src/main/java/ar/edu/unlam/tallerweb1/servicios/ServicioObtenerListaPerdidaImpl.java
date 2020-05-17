package ar.edu.unlam.tallerweb1.servicios;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.PostMascotaPerdida;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPostMascotaPerdida;

@Service("servicioObtenerListaPerdida")
@Transactional
public class ServicioObtenerListaPerdidaImpl implements ServicioObtenerListaPerdida{
	
	private RepositorioPostMascotaPerdida servicioListaDao;
	
	@Autowired
	public ServicioObtenerListaPerdidaImpl(RepositorioPostMascotaPerdida servicioListaDao) {
		this.servicioListaDao = servicioListaDao;
	}
	
	@Override
	public List<PostMascotaPerdida> obtenerListaPerdida() {
		
		 return servicioListaDao.getListaMascotasPerdidas();
	}
	
	
}
