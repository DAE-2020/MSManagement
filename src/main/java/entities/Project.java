package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(
    name="PROJECT",
    uniqueConstraints = @UniqueConstraint(columnNames = {"NAME","DESIGNER_CODE"})
)
@NamedQueries({
        @NamedQuery(
                name = "getAllProjects",
                query = "SELECT s FROM Project s" // JPQL
        ),

})
public class Project implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    private String name;
    @ManyToOne
    @JoinColumn(name = "DESIGNER_CODE", referencedColumnName = "USERNAME")
    @NotNull
    private Designer designer;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "PROJECT_STRUCTURES",
            joinColumns = @JoinColumn(name = "PROJECT_CODE", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "STRUCTURE_CODE", referencedColumnName = "ID"))
    private List<Structure> structures;

    public Project() {
        structures = new LinkedList<>();
    }

    public Project(String name, Designer designer) {
        this.name = name;
        this.designer = designer;
        this.structures = new LinkedList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Designer getDesigner() {
        return designer;
    }

    public void setDesigner(Designer designer) {
        this.designer = designer;
    }

    public List<Structure> getStructures() {
        return structures;
    }

    public void setStructures(List<Structure> structures) {
        this.structures = structures;
    }

    public void addStructure(Structure structure) {
        structures.add(structure);
    }

    public void removeStructure(Structure structure) {
        structures.remove(structure);
    }
}
