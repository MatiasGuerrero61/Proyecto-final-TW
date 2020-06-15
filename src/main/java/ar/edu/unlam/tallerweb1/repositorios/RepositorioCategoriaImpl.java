package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Categoria;
import ar.edu.unlam.tallerweb1.modelo.Tienda;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RepositorioCategoriaImpl implements RepositorioCategoria {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioCategoriaImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Categoria> getCategorias() {
        return (List<Categoria>) sessionFactory
                .getCurrentSession()
                .createCriteria(Categoria.class)
                .list();
    }

}
