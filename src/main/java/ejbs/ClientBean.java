package ejbs;

import entities.Client;
import entities.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ClientBean {

    @PersistenceContext
    EntityManager em;

    public void create (String username, String password, String name, String email) {
        try {
            User client = new Client(username, password, name, email);
            em.persist(client);
        } catch (Exception e) {
            System.out.println("ERROR! Create Client!");
            System.out.println(e.getMessage());
        }
    }

    public Client findClient(String username) {
        return em.find(Client.class, username);
    }
}
