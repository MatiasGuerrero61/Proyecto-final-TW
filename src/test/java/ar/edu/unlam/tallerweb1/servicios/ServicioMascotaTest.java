package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.excepciones.UsuarioNotFoundException;
import ar.edu.unlam.tallerweb1.modelo.Mascota;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioMascota;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ServicioMascotaTest {

    private ServicioMascotaImpl sut;

    @Test(expected = UsuarioNotFoundException.class)
    public void testObtenerUsuarioLanzaException(){
        RepositorioUsuario repoUsuarioMock = mock(RepositorioUsuario.class);
        when(repoUsuarioMock.consultarUsuarioPorId(1L)).thenReturn(null);

        sut = new ServicioMascotaImpl(null,repoUsuarioMock);

        Usuario usuario = new Usuario();
        usuario.setId(1L);
        sut.getListaMascotaDeUsuario(usuario);
    }


}