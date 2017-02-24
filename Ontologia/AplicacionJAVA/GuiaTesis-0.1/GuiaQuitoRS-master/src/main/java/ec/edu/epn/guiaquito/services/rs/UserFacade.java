package ec.edu.epn.guiaquito.services.rs;

import ec.edu.epn.guiaquito.entities.User;
import ec.edu.epn.guiaquito.services.UserService;
import ec.edu.epn.guiaquito.services.rs.base.BaseFacade;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;

@Stateless
@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserFacade extends BaseFacade<User, Integer> {
    @EJB
    private UserService userService;

    @GET
    public Response find() {
        List<User> users = null;
        try {
            users = userService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            Response.serverError().build();
        }
        return Response.ok(users).build();
    }

    @GET
    @Path("{id}")
    public Response findById(@PathParam("id") Integer id) {
        User user = null;
        try {
            user = userService.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            Response.serverError().build();
        }
        return Response.ok(user).build();
    }

    @POST
    public Response create(User user) {
        User created = null;
        try {
            created = userService.create(user);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
        return Response.ok(created).build();
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") Integer id, User user) {
        User updated = null;
        try {
            updated = userService.update(user);
        } catch (Exception e) {
            e.printStackTrace();
            Response.serverError().build();
        }
        return Response.ok(updated).build();
    }
    
    @POST
    public Response update(User user) {
        User update = null;
        try {
        	update = userService.create(user);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
        return Response.ok(update).build();
    }
}
