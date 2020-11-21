package ejbs;

import entities.Designer;
import entities.Project;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ProjectBean {

    @PersistenceContext
    EntityManager em;

    public void create (String name, Designer designer) {
        try {
            Project project = new Project(name, designer);
            em.persist(project);
        } catch (Exception e) {
            System.out.println("ERROR! ERROR! ERROR!");
            System.out.println(e.getMessage());
        }
    }

    public List<Project> getAllProjects() {
        return em.createNamedQuery("getAllProjects", Project.class).getResultList();
    }
}
