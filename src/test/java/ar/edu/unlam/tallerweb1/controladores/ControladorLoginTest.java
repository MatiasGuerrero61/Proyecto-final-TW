package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControladorLoginTest {

    @Test
    public void validarLoginDeUsuarioExistente(){
        Usuario usuario = new Usuario();
        ServicioLogin servLoginMock = mock(ServicioLogin.class);
        when(servLoginMock.consultarUsuario(usuario)).thenReturn(new Usuario());
        HttpServletRequest request = mock(HttpServletRequest.class);

        ControladorLogin control = new ControladorLogin(servLoginMock);

        ModelAndView modelAndView = control.validarLogin(usuario,request);
        assertTrue(modelAndView.getViewName().equals("redirect:/home"));
    }

    @Test
    public void validarLoginDeUsuarioNoExistente(){
        Usuario usuario = new Usuario();
        ServicioLogin servLoginMock = mock(ServicioLogin.class);
        when(servLoginMock.consultarUsuario(new Usuario())).thenReturn(null);

        HttpServletRequest request = mock(HttpServletRequest.class);

        ControladorLogin control = new ControladorLogin(servLoginMock);

        ModelAndView modelAndView = control.validarLogin(usuario,request);
        assertTrue(modelAndView.getViewName().equals("login"));

    }
}