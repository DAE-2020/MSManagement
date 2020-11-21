package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "getAllDesigners",
                query = "SELECT s FROM Designer s ORDER BY s.name" // JPQL
        )
})

public class Designer extends User implements Serializable {
    @OneToMany(mappedBy = "designer", cascade = CascadeType.REMOVE)
    private List<Project> projects;

    public Designer(){
        projects = new LinkedList<>();
    }

    public Designer(String username, String password, String name, String email) {
        super(username, password, name, email, "Designer");
        this.projects = new LinkedList<>();
    }

    public void addProject(Project project) {
        projects.add(project);
    }

    public void removeProject(Project project) {
        projects.remove(project);
    }

}
