package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.repositorios.RepositorioCarrito;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class ServicioCarritoTest {

    @Inject
    private ServicioCarrito sut;

    @Test
    @Transactional
    @Rollback(true)
    public void test(){
        RepositorioCarrito repoCarritoMock = mock(RepositorioCarrito.class);
        
    }

}