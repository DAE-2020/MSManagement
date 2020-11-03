package ws;

import dtos.AdministratorDTO;
import ejbs.AdministratorBean;
import entities.Administrator;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/administrators") // relative url web path for this service
@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”


public class AdministratorService {
    @EJB
    private AdministratorBean administratorBean;

    // Converts an entity Student to a DTO Student class
    private AdministratorDTO administratorToDTO(Administrator administrator) {
        return new AdministratorDTO(
                administrator.getUsername(),
                administrator.getPassword(),
                administrator.getName(),
                administrator.getEmail()
        );
    }

    // converts an entire list of entities into a list of DTOs
    private List<AdministratorDTO> administratorToDTOs(List<Administrator> administrators) {
        return administrators.stream().map(this::administratorToDTO).collect(Collectors.toList());
    }

    @POST
    @Path("/")
    public Response createNewAdministrator (AdministratorDTO administratorDTO) {
        System.out.println("Creating a new administrator");
        // Verify if Teacher already exists
        Administrator administrator = administratorBean.findAdministrator(administratorDTO.getUsername());
        System.out.println(administrator);
        if(administrator != null) {
            return Response.status(Response.Status.CONFLICT).build();
        }
        administratorBean.create(administratorDTO.getUsername(),
                administratorDTO.getPassword(),
                administratorDTO.getName(),
                administratorDTO.getEmail());
        // Verifies if Teacher has been created
        Administrator newAdministrator = administratorBean.findAdministrator(administratorDTO.getUsername());
        if(newAdministrator == null)
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        return Response.status(Response.Status.CREATED)
                .entity(administratorToDTO(newAdministrator))
                .build();
    }

    @GET
    @Path("{username}")
    public Response getAdministratorDetails(@PathParam("username") String username) {
        Administrator administrator = administratorBean.findAdministrator(username);
        if (administrator != null) {
            return Response.status(Response.Status.OK)
                    .entity(administratorToDTO(administrator))
                    .build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("ERROR_FINDING_ADMINISTRATOR")
                .build();
    }
}
