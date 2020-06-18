package ar.edu.unlam.tallerweb1.servicios;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Descuento;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDescuento;

@Service("servicioDescuento")
@Transactional
public class ServicioDescuentoImpl implements ServicioDescuento {
	
	private RepositorioDescuento servicioDescuentoDao;
	
	@Autowired
    public ServicioDescuentoImpl(RepositorioDescuento servicioDescuentoDao) {
		this.servicioDescuentoDao =  servicioDescuentoDao;
	}

	@Override
	public Descuento verificarValidez(String codigo) {
		return this.servicioDescuentoDao.verificarValidez(codigo);
	}

	@Override
	public void invalidarDescuento(Descuento descuento) {
		this.servicioDescuentoDao.invalidarDescuento(descuento);		
	}

	@Override
	public void reactivarDescuento(Descuento descuento) {
		this.servicioDescuentoDao.reactivarDescuento(descuento);
	}

	@Override
	public BigDecimal calcularDescuento(Descuento descuento, BigDecimal importe) {
		BigDecimal porcentaje = new BigDecimal(descuento.getPorcentajeDeDescuento().toString());
		BigDecimal descuentoCalculado = importe.multiply(porcentaje.divide(new BigDecimal("100")));
		if(0 < descuentoCalculado.compareTo(descuento.getTope())) {
			return descuento.getTope();
		}
		return descuentoCalculado;
	}

	@Override
	public BigDecimal calcularImporteConDescuento(Descuento descuento, BigDecimal importe) {
		BigDecimal descuentoCalculado = this.calcularDescuento(descuento, importe);
		BigDecimal importeConDescuento = importe.subtract(descuentoCalculado);
		return importeConDescuento;
	}
}
