package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "getAllClients",
                query = "SELECT s FROM Client s ORDER BY s.name" // JPQL
        )
})

public class Client extends User implements Serializable {
    @OneToMany(mappedBy = "client", cascade = CascadeType.REMOVE)
    private List<Project> projects;

    public Client(){
        projects = new LinkedList<>();
    }

    public Client(String username, String password, String name, String email) {
        super(username, password, name, email, "Client");
    }
}
