package upm.miw.solitaire.models.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import upm.miw.solitaire.models.dao.DAOFactory;
import upm.miw.solitaire.models.dao.UserDAO;

public class JPADAOFactory extends DAOFactory {
    private static final String PERSISTENCE_UNIT = "jpa";

    private EntityManager em;

    public JPADAOFactory() {
        this.em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT).createEntityManager();
    }

    public EntityManager getEm() {
        if (!em.isOpen())
            this.em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT)
                    .createEntityManager();
        return em;
    }

    @Override
    public UserDAO getUserDAO() {
        return new JPAUserDAO();
    }

}
