package ec.edu.epn.guiaquito.services.rs;

import ec.edu.epn.guiaquito.dao.SessionDAO;
import ec.edu.epn.guiaquito.dao.UserDAO;
import ec.edu.epn.guiaquito.entities.Session;
import ec.edu.epn.guiaquito.entities.User;
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

public class SessionFacade extends BaseFacade<Session, String> {
	@EJB
	private SessionDAO sessionDAO;

	@EJB
	private UserDAO userDAO;

	@GET
	public Response find() {
		List<Session> sessions = null;
		try {
			sessions = sessionDAO.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			Response.serverError();
		}
		return Response.ok(sessions).build();
	}

	@GET
	@Path("{id}")
	public Response findById(@PathParam("id") String id) {
		Session session = null;
		try {
			session = sessionDAO.findById(id);
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
			session = sessionDAO.findByFacebookId(facebookId);
		} catch (Exception e) {
			e.printStackTrace();
			Response.serverError();
		}
		return Response.ok(session).build();
	}

	@POST
	public Response create(Session session) {
		Session created = null;
		try {
			User user = userDAO.findById(session.getUserId());
			session.setUser(user);
			created = sessionDAO.create(session);
		} catch (Exception e) {
			e.printStackTrace();
			Response.serverError();
		}
		return Response.ok(created).build();
	}

	@PUT
	@Path("{id}")
	public Response update(@PathParam("id") String id, Session session) {
		Session updated = null;
		try {
			updated = sessionDAO.update(session);
		} catch (Exception e) {
			e.printStackTrace();
			Response.serverError();
		}
		return Response.ok(updated).build();
	}
}
