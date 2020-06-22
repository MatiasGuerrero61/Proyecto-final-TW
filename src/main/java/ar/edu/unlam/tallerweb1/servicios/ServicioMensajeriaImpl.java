package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Mensaje;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioMensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("ServicioMailImpl")
@Transactional
public class ServicioMensajeriaImpl implements ServicioMensajeria {

    private RepositorioMensaje repoMensajeDao;

    @Autowired
    public ServicioMensajeriaImpl(RepositorioMensaje repoMensajeDao) {
        this.repoMensajeDao = repoMensajeDao;
    }


    @Override
    public void enviarMensaje(Mensaje mensaje) {
        mensaje.setLeido(false);
        repoMensajeDao.postMensaje(mensaje);
    }

    @Override
    public List<Mensaje> getMensajes(Usuario usuario) {
        return repoMensajeDao.getMensajesDeUsuario(usuario);
    }
}
