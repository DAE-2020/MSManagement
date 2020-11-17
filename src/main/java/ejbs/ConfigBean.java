package ejbs;

import entities.Supplier;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

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

    @PostConstruct
    public void populateDB(){
        //Logger logger = Logger.getLogger("exceptions.CatchAllExceptions");
        //try {
        System.out.println("Started");

        administratorBean.create("admin", "admin", "Administrator", "admin@academic-management.com");
        clientBean.create("client", "client", "Cliente 1", "client1@academic-management.com");
        supplierBean.create("supplier", "supplier", "Fornecedor 1", "supplier1@academic-management.com");
        Supplier supplier = supplierBean.findSupplier("supplier");
        productBean.create("product1", supplier);

        //} catch(Exception e){
        //    logger.warning("Error: " + e.getMessage());
        //}
    }
}
