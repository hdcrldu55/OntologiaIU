package ec.edu.epn.guiaquito.services.rs;

import ec.edu.epn.guiaquito.dao.InterestTypeDAO;
import ec.edu.epn.guiaquito.entities.InterestType;
import ec.edu.epn.guiaquito.services.rs.base.BaseFacade;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
@Path("/interest-type")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InterestTypeFacade extends BaseFacade<InterestType, String> {
	@EJB
	private InterestTypeDAO interestTypeDAO;

	@GET
	public Response find() {
		List<InterestType> interestTypes = null;
		try {
			interestTypes = interestTypeDAO.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			Response.serverError().build();
		}
		return Response.ok(interestTypes).build();
	}

	@GET
	@Path("{id}")
	public Response findById(@PathParam("id") String id) {
		InterestType interestType = null;
		try {
			interestType = interestTypeDAO.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			Response.serverError().build();
		}
		return Response.ok(interestType).build();
	}

	@POST
	public Response create(InterestType interestType) {
		InterestType created = null;
		try {
			created = this.interestTypeDAO.create(interestType);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
		return Response.ok(created).build();
	}

	@PUT
	@Path("{id}")
	public Response update(@PathParam("id") String id, InterestType interestType) {
		InterestType updated = null;
		try {
			updated = this.interestTypeDAO.update(interestType);
		} catch (Exception e) {
			e.printStackTrace();
			Response.serverError().build();
		}
		return Response.ok(updated).build();
	}
}
