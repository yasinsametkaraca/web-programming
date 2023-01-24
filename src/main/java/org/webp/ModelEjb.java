package org.webp;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ModelEjb {

    @PersistenceContext
    private EntityManager em;

    public Model getModel(long id) {

        return em.find(Model.class, id);
    }

}
