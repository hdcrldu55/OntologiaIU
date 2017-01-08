package ec.edu.epn.guiaquito.services.rs;

import ec.edu.epn.guiaquito.dao.UserDAO;
import ec.edu.epn.guiaquito.entities.User;
import ec.edu.epn.guiaquito.entities.UserInterestType;
import ec.edu.epn.guiaquito.services.UserInterestService;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Path("/user-interest-type")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserInterestTypeFacade {

	private static final Logger LOGGER = Logger.getLogger(UserInterestTypeFacade.class);

	@EJB
	private UserInterestService userInterestService;

	@EJB
	private UserDAO userDAO;

	@POST
	public Response save(UserInterestType userInterestType) {
		List<UserInterestType> userInterestTypes = new ArrayList<>();
		try {
			LOGGER.info(userInterestType.getUserId());
			LOGGER.info(userInterestType.getInterestType().getId());
			User user = userDAO.getEntityManager().getReference(User.class, userInterestType.getUserId());
			if (user != null && userInterestType.getInterestType() != null) {
				userInterestService.updateInterest(user, userInterestType.getInterestType().getId(), null);
			}
			userDAO.getEntityManager().refresh(user);
			userInterestTypes = user.getUserInterestTypes().size() > 0 ? user.getUserInterestTypes() : userInterestTypes;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.ok(userInterestTypes).build();
	}
}
