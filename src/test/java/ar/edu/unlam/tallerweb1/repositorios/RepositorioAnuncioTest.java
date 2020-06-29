package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.excepciones.AnuncioNotFoundException;
import ar.edu.unlam.tallerweb1.excepciones.UsuarioNotFoundException;
import ar.edu.unlam.tallerweb1.modelo.Anuncio;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.xml.bind.annotation.XmlInlineBinaryData;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class RepositorioAnuncioTest extends SpringTest{

    RepositorioAnuncioImpl sut;

    @Test
    @Transactional
    @Rollback(true)
    public void ObtenerUnAnuncioPorId() {
        sut = new RepositorioAnuncioImpl(getSessionFactory());
        Anuncio anuncio = new Anuncio();
        anuncio.setTitulo("se perdio");
        sut.guardar(anuncio);


        Anuncio a = sut.obtenerAnuncioPorId(2);
        assertThat(a).isNotNull();
    }

    @Test(expected = AnuncioNotFoundException.class)
    @Transactional
    @Rollback(true)
    public void ObtenerUnAnuncioPorIdIncorrectoLanzaExepction() {
        sut = new RepositorioAnuncioImpl(getSessionFactory());
        Anuncio anuncio = new Anuncio();
        anuncio.setTitulo("se perdio");
        sut.guardar(anuncio);

        sut.obtenerAnuncioPorId(2);
    }

}