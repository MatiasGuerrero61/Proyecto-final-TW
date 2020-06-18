package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.servicios.ServicioMascotaPerdida;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControladorMascotaPerdidaTest {

    @Inject
    ControladorMascotaPerdida control;

    @Test
    public void test(){
        ServicioMascotaPerdida servMPMock = mock(ServicioMascotaPerdida.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(servMPMock.getMascotaDeUsuario(request)).thenReturn(new ArrayList<>());
        control = new ControladorMascotaPerdida(servMPMock);

        final ModelAndView modelAndView = control.irAPostearPerdida(request);

        assertTrue(modelAndView.getViewName().equals("mascotas-perdidas/postear-perdida"));
    }
}