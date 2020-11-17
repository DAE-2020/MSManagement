package ejbs;

import entities.Product;
import entities.Supplier;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ProductBean {

    @PersistenceContext
    EntityManager em;

    public void create(String name, Supplier supplier){
        Product p = new Product(name, supplier);
        em.persist(p);
    }

}
