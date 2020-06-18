package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Anuncio;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioAnuncio;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;

public class ServicioAnuncioTest {

    private ServicioAnuncioImpl sut;

    @Test
    public void getAnuncios() {
        RepositorioAnuncio repoAnuncioMock = mock(RepositorioAnuncio.class);
        //when(repoAnuncioMock.obtenerAnuncios()).thenReturn();

        sut = new ServicioAnuncioImpl(repoAnuncioMock);
    }
}