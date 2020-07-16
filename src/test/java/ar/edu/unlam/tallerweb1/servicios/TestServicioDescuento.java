package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Descuento;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class TestServicioDescuento extends SpringTest {

    @Autowired
    ServicioDescuento servicioDescuento;

    @Test
    @Transactional
    @Rollback
    public void TestDescuentoValido() {
        Descuento descuento = new Descuento();
        descuento.setCodigo("JAVA2020");
        descuento.setValidez(true);
        session().save(descuento);
        Descuento descuentoBuscado = servicioDescuento.verificarValidez("JAVA2020");
        assertThat(descuentoBuscado).isNotNull();
    }

    @Test
    @Transactional
    @Rollback
    public void TestDescuentoNoValido() {
        Descuento descuento = new Descuento();
        descuento.setCodigo("JAVA2020");
        descuento.setValidez(false);
        session().save(descuento);
        Descuento descuentoBuscado = servicioDescuento.verificarValidez("JAVA2020");
        assertThat(descuentoBuscado).isNull();
    }

    @Test
    @Transactional
    @Rollback
    public void TestCodigoNoValido() {
        Descuento descuento = new Descuento();
        descuento.setCodigo("JAVA2020");
        descuento.setValidez(true);
        session().save(descuento);
        Descuento descuentoBuscado = servicioDescuento.verificarValidez("Java2020");
        assertThat(descuentoBuscado).isNull();
    }

    @Test
    public void calcularDescuentoDondeResultadoSeaElTope() {
        Descuento descuento = new Descuento();
        descuento.setPorcentajeDeDescuento(20);
        descuento.setTope(new BigDecimal(2000));
        BigDecimal importe = new BigDecimal(12000);

        BigDecimal result = servicioDescuento.calcularDescuento(descuento, importe);

        assertThat(result).isEqualTo(new BigDecimal(2000));
    }

    @Test
    public void testQueResuelvaElImportTotalConElDescuento() {
        Descuento descuento = new Descuento();
        descuento.setPorcentajeDeDescuento(20);
        descuento.setTope(new BigDecimal(2000));
        BigDecimal importe = new BigDecimal(12000);

        BigDecimal result = servicioDescuento.calcularImporteConDescuento(descuento, importe);

        assertThat(result).isEqualTo(new BigDecimal(10000));
    }


}
