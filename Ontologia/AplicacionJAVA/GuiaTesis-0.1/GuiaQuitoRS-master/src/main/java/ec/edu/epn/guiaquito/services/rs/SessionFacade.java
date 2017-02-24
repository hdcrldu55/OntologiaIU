package ec.edu.epn.guiaquito.services.rs;

import ec.edu.epn.guiaquito.entities.Session;
import ec.edu.epn.guiaquito.services.SessionService;
import ec.edu.epn.guiaquito.services.rs.base.BaseFacade;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
@Path("/session")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class SessionFacade extends BaseFacade<Session, Integer> {
    @EJB
    private SessionService sessionService;

    @GET
    public Response find() {
        List<Session> sessions = null;
        try {
            sessions = sessionService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            Response.serverError();
        }
        return Response.ok(sessions).build();
    }

    @GET
    @Path("{id}")
    public Response findById(@PathParam("id") Integer id) {
        Session session = null;
        try {
            session = sessionService.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            Response.serverError();
        }
        return Response.ok(session).build();
    }

    @GET
    @Path("/facebook/{id}")
    public Response searchPerFacebookId(@PathParam("id") Long facebookId) {
        Session session = null;
        try {
            session = sessionService.findByFacebookId(facebookId);
        } catch (Exception e) {
            e.printStackTrace();
            Response.serverError();
        }
        return Response.ok(session).build();
    }

    @POST
    public Response create(Session session) {
        Session nuevo = null;
        try {
            nuevo = sessionService.create(session);
        } catch (Exception e) {
            e.printStackTrace();
            Response.serverError();
        }
        return Response.ok(nuevo).build();
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") Integer id, Session session) {
        Session actualizar = null;
        try {
            actualizar = sessionService.update(session);
        } catch (Exception e) {
            e.printStackTrace();
            Response.serverError();
        }
        return Response.ok(actualizar).build();
    }
}
