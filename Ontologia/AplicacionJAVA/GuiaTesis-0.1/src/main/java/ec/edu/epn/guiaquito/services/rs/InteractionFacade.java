package ec.edu.epn.guiaquito.services.rs;

import ec.edu.epn.guiaquito.dao.SessionDAO;
import ec.edu.epn.guiaquito.entities.Context;
import ec.edu.epn.guiaquito.entities.Interaction;
import ec.edu.epn.guiaquito.entities.Session;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("/path")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InteractionFacade {

	@EJB
	private SessionDAO sessionDAO;

	@POST
	public Response create(Interaction interaction) {
		//Eliminado por no existir tabla
		Interaction created;
		try {
			Session session = sessionDAO.findById(interaction.getSessionId());
			Context context = new Context();
			context.setLatitude(interaction.getLatitude());
			context.setLongitude(interaction.getLongitude());
			context.setOrientation(interaction.getOrientation());
			context.setTime(interaction.getTime());

			session.setContext(context);
			session.getInteractions().add(interaction);

			sessionDAO.update(session);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
		return Response.ok(interaction).build();
	}
}
