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

        return sessionFactory.getCurrentSession().get(Mensaje.class,id);
    }

    @Override
    public List<Mensaje> getMensajesDeUsuario(Usuario usuario) {
        final Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Mensaje.class)
                .add(Restrictions.like("destinatario",usuario)).addOrder(Order.asc("leido")).list();
    }

}
