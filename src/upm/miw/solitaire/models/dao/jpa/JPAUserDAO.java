package upm.miw.solitaire.models.dao.jpa;

import upm.miw.solitaire.models.dao.UserDAO;
import upm.miw.solitaire.models.entities.Gender;
import upm.miw.solitaire.models.entities.User;

public class JPAUserDAO extends JPATransactionGenericDAO<User, String> implements UserDAO {

    public JPAUserDAO() {
        super(User.class);
    }
    
    public static void main(String[] args) {
        JPAUserDAO dao= new JPAUserDAO();
        User admin = new User("admin", "aaaaaaaa", "admin", "", 69, Gender.MALE, "Madrid");
        admin.setAdministrator(true);
        dao.create(admin);
        dao.create(new User("user", "uuuuuuuu", "user", "", 69, Gender.MALE, "Madrid"));
    }


}
