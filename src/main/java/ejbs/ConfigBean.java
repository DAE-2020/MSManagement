package ejbs;

import entities.Designer;
import entities.Product;
import entities.Structure;
import entities.Supplier;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

@Singleton( name = "ConfigEJB" )
@Startup

public class ConfigBean {

    @EJB
    AdministratorBean administratorBean;
    @EJB
    ClientBean clientBean;
    @EJB
    SupplierBean supplierBean;
    @EJB
    ProductBean productBean;
    @EJB
    DesignerBean designerBean;
    @EJB
    ProjectBean projectBean;
    @EJB
    StructureBean structureBean;

    @PostConstruct
    public void populateDB(){
        Logger logger = Logger.getLogger("exceptions.CatchAllExceptions");
        try {
            System.out.println("Started");

            administratorBean.create("admin", "admin", "Administrator", "admin@academic-management.com");
            clientBean.create("client", "client", "Cliente 1", "client1@academic-management.com");
            supplierBean.create("supplier", "supplier", "Fornecedor 1", "supplier1@academic-management.com");

            System.out.println("Inserting products");
            productBean.create("product1", "supplier");
            productBean.create("product2", "supplier");
            productBean.create("product3", "supplier");

            System.out.println("Insert designer");
            designerBean.create("designer", "designer", "Projectista 1", "designer1@academic-management.com");

            System.out.println("creating project ");
            projectBean.create("project1", "designer");
            List<Integer> products = new LinkedList<>();
            products.add(1);
            products.add(2);

            System.out.println("creating structure ");
            structureBean.create("structure1", products);
            System.out.println("add product in structure");
            structureBean.addProductToStructure(5,3);
        } catch(Exception e){
            logger.warning("Error: " + e.getMessage());
        }
    }
}
