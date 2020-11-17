package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "getAllSuppliers",
                query = "SELECT s FROM Supplier s ORDER BY s.name" // JPQL
        )
})

public class Supplier extends User implements Serializable {
    @OneToMany(mappedBy = "supplier", cascade = CascadeType.REMOVE)
    private List<Product> products;

    public Supplier(){
        products = new LinkedList<>();
    }

    public Supplier(String username, String password, String name, String email) {
        super(username, password, name, email, "Supplier");
        this.products = new LinkedList<>();
    }
}
