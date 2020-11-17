package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Product {

    @Id
    private String nome;
    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    private List<Variante> variantes;
    /*@ManyToMany(mappedBy = "produto")
    @JoinTable(name = "PRODUCTS_PROJECTS",
            joinColumns = @JoinColumn(name = "PRODUCT_CODE", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "PROJECT_CODE", referencedColumnName =
                    "ID"))
    private List<Project> projects;*/
    @ManyToOne
    @JoinColumn(name = "SUPPLIER", referencedColumnName = "USERNAME")
    @NotNull
    private Supplier supplier;

    public Product() {
        variantes = new LinkedList<>();
        //projects = new LinkedList<>();
    }

    public Product(String nome, Supplier supplier) {
        this.nome = nome;
        this.supplier = supplier;
        this.variantes = new LinkedList<>();
        //this.projects = new LinkedList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String name) {
        this.nome = name;
    }

    public List<Variante> getVariantes() {
        return variantes;
    }

    public void setVariantes(List<Variante> especimen) {
        this.variantes = especimen;
    }

    public void addVariante(Variante s) {
        variantes.add(s);
    }

    public void removeVariante(Variante s) {
        variantes.remove(s);
    }

}