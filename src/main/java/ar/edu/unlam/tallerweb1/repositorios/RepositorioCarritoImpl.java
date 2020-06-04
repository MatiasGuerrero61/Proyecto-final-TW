package ar.edu.unlam.tallerweb1.repositorios;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("repositorioCarrito")
public class RepositorioCarritoImpl implements RepositorioCarrito{
	
	private SessionFactory sessionFactory;

    @Autowired
	public RepositorioCarritoImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

}
