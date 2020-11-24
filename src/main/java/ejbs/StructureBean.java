package ejbs;

import entities.Designer;
import entities.Product;
import entities.Project;
import entities.Structure;
import exceptions.MyConstraintViolationException;
import exceptions.MyEntityExistsException;
import exceptions.MyEntityNotFoundException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import java.util.LinkedList;
import java.util.List;

@Stateless
public class StructureBean {

    @PersistenceContext
    EntityManager em;

    public void create (String name, List<Integer> products)
            throws MyEntityNotFoundException, MyEntityExistsException, MyConstraintViolationException {
        try {
            Structure structure = new Structure(name);
            em.persist(structure);

            for (int id : products){
                Product product = em.find(Product.class, id);
                if(product == null){
                    throw new MyEntityNotFoundException("Product with id: " + id + " not found.");
                } else {
                    structure.addProduct(product);
                }
            }

        } catch (Exception e) {
            System.out.println("ERROR! Create Structure");
            System.out.println(e.getMessage());
        }
    }

    public Structure findStructure(int id) {
        return em.find(Structure.class, id);
    }

    public void addProductToStructure(int structureId, int productId)
            throws MyEntityNotFoundException, MyEntityExistsException, MyConstraintViolationException {
        Structure structure = em.find(Structure.class, structureId);
        Product product = em.find(Product.class, productId);
        if(structure == null){
            throw new MyEntityNotFoundException("Product with id: " + structureId + " not found.");
        }
        if(product == null){
            throw new MyEntityNotFoundException("Product with id: " + productId + " not found.");
        }
        product.addStructure(structure);
        structure.addProduct(product);
    }

    public List<Structure> getAllStructures() {
        return em.createNamedQuery("getAllStructures", Structure.class).getResultList();
    }
}
