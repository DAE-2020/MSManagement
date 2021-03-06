package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    private String nome;
    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    private List<Variante> variantes;
    @ManyToMany(mappedBy = "products", cascade = CascadeType.REMOVE)
    private List<Structure> structures;
    @ManyToOne
    @JoinColumn(name = "SUPPLIER", referencedColumnName = "USERNAME")
    @NotNull
    private Supplier supplier;

    public Product() {
        variantes = new LinkedList<>();
        structures = new LinkedList<>();
    }

    public Product(String nome, Supplier supplier) {
        this.nome = nome;
        this.supplier = supplier;
        this.variantes = new LinkedList<>();
        this.structures = new LinkedList<>();
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

    public void addStructure(Structure structure) {
        structures.add(structure);
    }

    public void removeStructure(Structure structure) {
        structures.remove(structure);
    }

}