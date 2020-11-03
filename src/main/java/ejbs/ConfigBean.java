package ejbs;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.logging.Logger;

@Singleton( name = "ConfigEJB" )
@Startup

public class ConfigBean {

    @EJB
    AdministratorBean administratorBean;

    @PostConstruct
    public void populateDB(){
        Logger logger = Logger.getLogger("exceptions.CatchAllExceptions");
        try {
            System.out.println("Started");

            administratorBean.create("admin", "admin", "Administrator", "admin@academic-management.com");

        } catch(Exception e){
            logger.warning("Error: " + e.getMessage());
        }
    }
}
