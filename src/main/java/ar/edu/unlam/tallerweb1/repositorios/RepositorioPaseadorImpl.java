package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Paseador;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositorioPaseadorImpl implements RepositorioPaseador {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioPaseadorImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Paseador> getPaseadores() {
        final Session session = sessionFactory.getCurrentSession();
        List<Paseador> paseadores = session.createCriteria(Paseador.class).list();
        return paseadores;
    }
}
