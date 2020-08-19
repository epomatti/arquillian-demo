package demo.shared;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class ApplicationResources {
    
	@Produces
    @PersistenceContext
    private EntityManager em;
    
}