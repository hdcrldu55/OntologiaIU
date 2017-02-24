package ec.edu.epn.guiaquito.services.rs;

import ec.edu.epn.guiaquito.entities.Path;
import ec.edu.epn.guiaquito.services.PathService;
import ec.edu.epn.guiaquito.services.rs.base.BaseFacade;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
@javax.ws.rs.Path("/path")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PathFacade extends BaseFacade<Path, Integer> {
    @EJB
    private PathService pathService;

    @GET
    public Response find() {
        List<Path> paths = null;
        try {
            paths = pathService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            Response.serverError();
        }
        return Response.ok(paths).build();
    }

    @GET
    @javax.ws.rs.Path("{id}")
    public Response findById(@PathParam("id") Integer id) {
        Path path = null;
        try {
            path = pathService.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            Response.serverError();
        }
        return Response.ok(path).build();
    }

    @POST
    public Response create(Path path) {
        Path nuevo = null;
        try {
            nuevo = pathService.create(path);
        } catch (Exception e) {
            e.printStackTrace();
            Response.serverError();
        }
        return Response.ok(nuevo).build();
    }

    @PUT
    @javax.ws.rs.Path("{id}")
    public Response update(@PathParam("id") Integer id, Path path) {
        Path actualizar = null;
        try {
            actualizar = pathService.update(path);
        } catch (Exception e) {
            e.printStackTrace();
            Response.serverError();
        }
        return Response.ok(actualizar).build();
    }
}
