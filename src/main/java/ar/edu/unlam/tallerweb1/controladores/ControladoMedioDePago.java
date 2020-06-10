package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;

@Controller
public class ControladoMedioDePago {

    @RequestMapping(value = "/medios-pago", method = RequestMethod.GET)
    public ModelAndView getMediosDePago(){
        return new ModelAndView("medios-de-pago",
                Collections.singletonMap("medios-de-pago", Collections.emptyList()));
    }
}
