package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "getAllAdministrators",
                query = "SELECT s FROM Administrator s ORDER BY s.name" // JPQL
        )
})

public class Administrator extends User implements Serializable {
    public Administrator(){}

    public Administrator(String username, String password, String name, String email) {
        super(username, password, name, email, "Administrator");
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "username='" + getUsername() + '\'' +
                ", name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                '}';
    }
}
