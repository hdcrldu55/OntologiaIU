package ec.edu.epn.guiaquito.services.rs;

import ec.edu.epn.guiaquito.dao.UserDAO;
import ec.edu.epn.guiaquito.entities.User;
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
public class UserFacade extends BaseFacade<User, String> {
	@EJB
	private UserDAO userDAO;

	@GET
	public Response find() {
		List<User> users;
		Response response;
		try {
			users = userDAO.findAll();
			response = Response.ok(users).build();
		} catch (Exception e) {
			e.printStackTrace();
			response = Response.serverError().build();
		}
		return response;
	}

	@GET
	@Path("{id}")
	public Response findById(@PathParam("id") String id) {
		User user;
		Response response;
		try {
			user = userDAO.findById(id);
			response = Response.ok(user).build();
		} catch (Exception e) {
			e.printStackTrace();
			response = Response.serverError().build();
		}
		return response;
	}

	@POST
	public Response create(User user) {
		User created;
		try {
			created = userDAO.create(user);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
		return Response.ok(created).build();
	}

	@PUT
	@Path("{id}")
	public Response update(@PathParam("id") String id, User user) {
		User updated;
		try {
			User found = userDAO.findById(id);
			found.setFacebookId(user.getFacebookId());
			found.setFacebookToken(user.getFacebookToken());
			found.setBirthday(user.getBirthday());
			found.setEmail(user.getEmail());
			found.setFirstName(user.getFirstName());
			found.setLastName(user.getLastName());
			updated = userDAO.update(found);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
		return Response.ok(updated).build();
	}

	@PUT
	public Response update(User user) {
		User updated;
		try {
			User found = userDAO.findByFacebookId(user.getFacebookId());
			found.setFacebookId(user.getFacebookId());
			found.setFacebookToken(user.getFacebookToken());
			found.setBirthday(user.getBirthday());
			found.setEmail(user.getEmail());
			found.setFirstName(user.getFirstName());
			found.setLastName(user.getLastName());
			updated = userDAO.update(found);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
		return Response.ok(updated).build();
	}
}
