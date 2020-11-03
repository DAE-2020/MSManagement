package dtos;

import entities.User;

import java.io.Serializable;

public class AdministratorDTO extends User implements Serializable{

    public AdministratorDTO(){}

    public AdministratorDTO(String username, String password, String name, String email) {
        super(username, password, name, email);
    }
}
