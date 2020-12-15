package ejbs;

import entities.*;
import exceptions.MyConstraintViolationException;
import exceptions.MyEntityExistsException;
import exceptions.MyEntityNotFoundException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AttachmentBean {

    @PersistenceContext
    EntityManager em;

    public void create (String url, String username, int projectId, int productId, int structureId)
            throws MyEntityNotFoundException, MyEntityExistsException, MyConstraintViolationException {
        try {
            User user = em.find(User.class, username);
            if(user == null){
                throw new MyEntityNotFoundException("User with username: " + username + " not found.");
            }

            Attachment attachment = new Attachment(url,user);
            em.persist(attachment);

            Project project = em.find(Project.class, projectId);
            if(project != null){
                attachment.addProject(project);
            }
            Product product = em.find(Product.class, productId);
            if(product != null){
                attachment.addProduct(product);
            }
            Structure structure = em.find(Structure.class, structureId);
            if(structure != null){
                attachment.addStructure(structure);
            }

        } catch (Exception e) {
            System.out.println("ERROR! Create attachment!");
            System.out.println(e.getMessage());
        }
    }

    public Administrator findAdministrator(String username) {
        return em.find(Administrator.class, username);
    }
}
