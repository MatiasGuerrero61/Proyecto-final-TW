package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.controladores.ControladorTienda;
import ar.edu.unlam.tallerweb1.modelo.Tienda;
import ar.edu.unlam.tallerweb1.servicios.ServicioTienda;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;


public class TestControladorTienda {
    
    @Test
    public void validarIrAListarTiendasConTiendas(){
    	
        ServicioTienda servTiendaMock = mock(ServicioTienda.class);

        Tienda tienda = new Tienda();
        List<Tienda> tiendas = new ArrayList<Tienda>();
        tiendas.add(tienda); 
        
        when(servTiendaMock.listarTiendas()).thenReturn(tiendas);        
        ControladorTienda controlTienda = new ControladorTienda(null,servTiendaMock,null);
        final ModelAndView modelAndView = controlTienda.irAListarTiendas();
        
        assertThat(modelAndView.getViewName()).isEqualTo("tienda/listar-tiendas");
        assertThat(modelAndView.getModel()).doesNotContainKey("msj");
        assertThat(modelAndView.getModel()).containsKey("tiendas");
    }
    
    @Test
    public void validarIrAListarTiendasSinTiendas(){
        ServicioTienda servTiendaMock = mock(ServicioTienda.class);
        ControladorTienda controlTienda = new ControladorTienda(null,servTiendaMock,null);
        
        when(servTiendaMock.listarTiendas()).thenReturn(new ArrayList<Tienda>());
        final ModelAndView modelAndView = controlTienda.irAListarTiendas();
        
        assertThat(modelAndView.getViewName()).isEqualTo("tienda/listar-tiendas");
        assertThat(modelAndView.getModel()).doesNotContainKey("tiendas");
        assertThat(modelAndView.getModel()).containsKey("msj");
        assertThat(modelAndView.getModel().get("msj")).isEqualTo("No hay tiendas disponibles");
    }
    
}
