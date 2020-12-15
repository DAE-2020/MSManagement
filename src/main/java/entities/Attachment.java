package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(
    name="ATTACHMENT",
    uniqueConstraints = @UniqueConstraint(columnNames = {"URL"})
)
@NamedQueries({
        @NamedQuery(
                name = "getAllAttachments",
                query = "SELECT s FROM Attachment s" // JPQL
        ),

})
public class Attachment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    private String url;
    @ManyToOne
    @JoinColumn(name = "USERNAME_CODE", referencedColumnName = "USERNAME")
    private User uploader;
    @ManyToOne
    @JoinColumn(name = "PROJECT_CODE", referencedColumnName = "ID")
    private Project project;
    @ManyToOne
    @JoinColumn(name = "PRODUCT_CODE", referencedColumnName = "ID")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "STRUCTURE_CODE", referencedColumnName = "ID")
    private Structure structure;

    public Attachment() {}

    public Attachment(String url, User user) {
        this.url = url;
        this.uploader = user;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public User getUser() {
        return uploader;
    }

    public void setUser(User user) {
        this.uploader = user;
    }

    public Structure getStructure() {
        return structure;
    }

    public void addStructure(Structure structure) {
        this.structure = structure;
    }

    public Project getProject() {
        return project;
    }

    public void addProject(Project project) {
        this.project = project;
    }

    public Product getProduct() {
        return product;
    }

    public void addProduct(Product product) {
        this.product = product;
    }
}
