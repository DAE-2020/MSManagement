package ejbs;

import entities.Designer;
import entities.Product;
import entities.Project;
import entities.Supplier;
import exceptions.MyEntityNotFoundException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ProductBean {

    @PersistenceContext
    EntityManager em;

    public void create(String name, String supplierName){
        try {
            Supplier supplier = em.find(Supplier.class, supplierName);
            if(supplier == null){
                throw new MyEntityNotFoundException("Supplier with username: " + supplierName + " not found.");
            }
            Product p = new Product(name, supplier);
            em.persist(p);
        } catch (Exception e) {
            System.out.println("ERROR! Create Product!");
            System.out.println(e.getMessage());
        }
    }


    public Product findProduct(int id) {
        return em.find(Product.class, id);
    }
}
