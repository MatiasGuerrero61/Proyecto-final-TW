package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Mensaje;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("repositorioMensaje")
public class RepositorioMensajeImpl implements RepositorioMensaje {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioMensajeImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void postMensaje(Mensaje mensaje) {
        final Session session = sessionFactory.getCurrentSession();
        session.save(mensaje);
    }

    @Override
    public Mensaje getMensajeById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Mensaje mensaje = session.get(Mensaje.class,id);
        mensaje.setLeido(true);
        session.update(mensaje);
        return mensaje;
    }

    @Override
    public List<Mensaje> getMensajesEntradaDeUsuario(Usuario usuario) {
        final Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Mensaje.class)
                .add(Restrictions.like("destinatario",usuario))
                .addOrder(Order.asc("leido")).list();
    }

    @Override
    public List<Mensaje> getMensajesSalidaDeUsuario(Usuario usuario) {
        return sessionFactory.getCurrentSession().createCriteria(Mensaje.class)
                .add(Restrictions.like("remitente",usuario))
                .addOrder(Order.asc("leido")).list();
    }

}
