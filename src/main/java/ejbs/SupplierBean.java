package ejbs;

import entities.Supplier;
import entities.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class SupplierBean {

    @PersistenceContext
    EntityManager em;

    public void create (String username, String password, String name, String email) {
        try {
            User supplier = new Supplier(username, password, name, email);
            em.persist(supplier);
        } catch (Exception e) {
            System.out.println("ERROR! Create Supplier!");
            System.out.println(e.getMessage());
        }
    }

    public Supplier findSupplier(String username) {
        return em.find(Supplier.class, username);
    }
}
