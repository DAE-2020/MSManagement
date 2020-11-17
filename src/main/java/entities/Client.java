package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "getAllClients",
                query = "SELECT s FROM Client s ORDER BY s.name" // JPQL
        )
})

public class Client extends User implements Serializable {
    public Client(){}

    public Client(String username, String password, String name, String email) {
        super(username, password, name, email, "Client");
    }
}
