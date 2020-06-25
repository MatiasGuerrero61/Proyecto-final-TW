package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.excepciones.TiendaNotFoundException;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioTienda;
import org.junit.Test;

import javax.inject.Inject;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServicioTiendaTest {

    @Inject
    private ServicioTienda sut;

    @Test(expected = TiendaNotFoundException.class)
    public void testBuscaPorIdInvalidoLanzaExeption(){
        RepositorioTienda repoTiendaMock = mock(RepositorioTienda.class);
        when(repoTiendaMock.buscarTiendaPorId(1L)).thenReturn(null);

        sut = new ServicioTiendaImpl(repoTiendaMock);
        sut.buscarTiendaPorId(1L);
    }
}