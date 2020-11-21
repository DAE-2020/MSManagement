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
    private double id;
    @NotNull
    private String name;
    @ManyToOne
    @JoinColumn(name = "DESIGNER_CODE", referencedColumnName = "USERNAME")
    @NotNull
    private Designer designer;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "PROJECT_PRODUCTS",
            joinColumns = @JoinColumn(name = "PROJECT_CODE", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "PRODUCT_CODE", referencedColumnName = "NOME"))
    private List<Product> products;

    public Project() {
        products = new LinkedList<>();
    }

    public Project(String name, Designer designer) {
        this.name = name;
        this.designer = designer;
        this.products = new LinkedList<>();
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }
}
