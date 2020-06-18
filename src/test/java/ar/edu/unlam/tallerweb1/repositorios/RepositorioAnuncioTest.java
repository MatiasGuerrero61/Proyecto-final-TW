package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Anuncio;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.xml.bind.annotation.XmlInlineBinaryData;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class RepositorioAnuncioTest extends SpringTest{

    @Inject
    private RepositorioAnuncio sut;

    @Test
    @Transactional
    @Rollback(true)
    public void guardar() {
        Anuncio anuncio = new Anuncio();
        anuncio.setTitulo("se perdio");
        session().save(anuncio);

        Anuncio a = sut.obtenerAnuncioPorId(anuncio.getId());
        assertThat(a).isNotNull();
    }

}