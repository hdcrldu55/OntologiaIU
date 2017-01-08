package ec.edu.epn.guiaquito.services.rs;

import com.google.gson.Gson;
import ec.edu.epn.guiaquito.dao.UserDAO;
import ec.edu.epn.guiaquito.dao.UserInterestTypeDAO;
import ec.edu.epn.guiaquito.entities.*;
import ec.edu.epn.guiaquito.services.rs.params.PlaceParam;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("/visited")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class VisitedPlaceFacade {

	private static final Logger LOGGER = Logger.getLogger(VisitedPlaceFacade.class);

	@EJB
	private UserDAO userDAO;

	@EJB
	private UserInterestTypeDAO userInterestTypeDAO;

	@POST
	public Response add(PlaceParam placeParam) {
		User user = null;
		boolean exist = Boolean.FALSE;
		VisitedPlace visitedPlace;
		try {
			user = userDAO.findById(placeParam.getUserId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (user != null) {
			for (int i = 0; i < user.getVisitedPlaces().size(); i++) {
				VisitedPlace place = user.getVisitedPlaces().get(i);
				if (place.getPlaceId().equals(placeParam.getPlaceId())) {
					exist = true;
					if (placeParam.getRating() != null) {
						place.setRating(placeParam.getRating());
						user.getVisitedPlaces().set(i, place);
					}
					break;
				}
			}
			if (!exist) {
				visitedPlace = new VisitedPlace();
				visitedPlace.setPlaceId(placeParam.getPlaceId());
				if (placeParam.getRating() != null) {
					visitedPlace.setRating(placeParam.getRating());
				}

				user.getVisitedPlaces().add(visitedPlace);
			}
			try {
				userDAO.update(user);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return Response.ok().build();
	}
}
