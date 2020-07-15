package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.excepciones.AnuncioNotFoundException;
import ar.edu.unlam.tallerweb1.modelo.Anuncio;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("repositorioAnuncio")
public class RepositorioAnuncioImpl implements RepositorioAnuncio {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioAnuncioImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void guardar(Anuncio anuncio) {
        Session session = sessionFactory.getCurrentSession();
        session.save(anuncio);
    }

    @Override
    public Anuncio obtenerAnuncioPorId(long id) {
        final Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Anuncio.class);

        Anuncio encontrado = (Anuncio) criteria.add(Restrictions.eq("id",id)).uniqueResult();
        if(encontrado != null){
            return encontrado;
        }
        else{
            throw new AnuncioNotFoundException("no se encontro anuncio");
        }
    }

    @Override
    public List<Anuncio> obtenerAnuncios() {
        final Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Anuncio.class);
        List<Anuncio> anuncios = criteria.list();
        return anuncios;
    }

    @Override
    public List<Anuncio> obtenerAnunciosByUsuario(Usuario usuario) {
        final Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Anuncio.class)
                .add(Restrictions.eq("duenio",usuario))
                .list();
    }

    @Override
    public void deleteAnuncio(Anuncio anuncio) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteAnuncioPorId(long id) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateAnuncio(Anuncio anuncio) {
        // TODO Auto-generated method stub

    }

}
