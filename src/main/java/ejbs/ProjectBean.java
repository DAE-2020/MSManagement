package ejbs;

import entities.Designer;
import entities.Product;
import entities.Project;
import entities.Supplier;
import exceptions.MyEntityNotFoundException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ProjectBean {

    @PersistenceContext
    EntityManager em;

    public void create (String name, String designerName) {
        try {
            Designer designer = em.find(Designer.class, designerName);
            if(designer == null){
                throw new MyEntityNotFoundException("Designer with username: " + designerName + " not found.");
            }
            Project project = new Project(name, designer);
            em.persist(project);
        } catch (Exception e) {
            System.out.println("ERROR! Create Project!");
            System.out.println(e.getMessage());
        }
    }

    public Project findProject(int id) {
        return em.find(Project.class, id);
    }

    public List<Project> getAllProjects() {
        return em.createNamedQuery("getAllProjects", Project.class).getResultList();
    }
}
