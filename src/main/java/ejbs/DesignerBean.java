package ejbs;

import entities.Designer;
import entities.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class DesignerBean {

    @PersistenceContext
    EntityManager em;

    public void create (String username, String password, String name, String email) {
        try {
            User designer = new Designer(username, password, name, email);
            em.persist(designer);
        } catch (Exception e) {
            System.out.println("ERROR! ERROR! ERROR!");
            System.out.println(e.getMessage());
        }
    }

    public Designer findDesigner(String username) {
        return em.find(Designer.class, username);
    }
}
