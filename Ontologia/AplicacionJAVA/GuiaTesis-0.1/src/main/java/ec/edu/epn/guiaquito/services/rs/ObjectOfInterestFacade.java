package ec.edu.epn.guiaquito.services.rs;

import ec.edu.epn.guiaquito.dao.InterestTypeDAO;
import ec.edu.epn.guiaquito.dao.UserDAO;
import ec.edu.epn.guiaquito.dao.UserInterestTypeDAO;
import ec.edu.epn.guiaquito.entities.InterestType;
import ec.edu.epn.guiaquito.entities.ObjectOfInterest;
import ec.edu.epn.guiaquito.entities.User;
import ec.edu.epn.guiaquito.entities.UserInterestType;
import ec.edu.epn.guiaquito.services.ObjectOfInterestService;
import ec.edu.epn.guiaquito.services.UserInterestService;
import fi.foyt.foursquare.api.entities.CompactVenue;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Stateless
@Path("/poi")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ObjectOfInterestFacade {

	private static final Logger LOGGER = Logger.getLogger(ObjectOfInterestFacade.class);

	@EJB
	private ObjectOfInterestService objectOfInterestService;

	@EJB
	private UserInterestService userInterestService;

	@EJB
	private InterestTypeDAO interestTypeDAO;

	@EJB
	private UserDAO userDAO;

	@EJB
	private UserInterestTypeDAO userInterestTypeDAO;

	@GET
	public Response find(@QueryParam("userId") String userId,
	                     @QueryParam("latitude") double latitude,
	                     @QueryParam("longitude") double longitude,
	                     @QueryParam("type") String interestTypeId,
	                     @QueryParam("subType") String subInterestTypeId,
	                     @QueryParam("facebookToken") String facebookToken) {
		List<ObjectOfInterest> objectOfInterests = new ArrayList<>();
		if (userId == null) {
			objectOfInterests.addAll(find(latitude, longitude, interestTypeId, subInterestTypeId));
		} else {
			try {
				User user = userDAO.getEntityManager().getReference(User.class, userId);
				//TODO setFacebookToken and mark as transient
				Boolean interest = false;
				if (user != null) {
					List<CompactVenue> venues = userInterestService.searchLikedSites(user);
					userInterestService.updateInterest(user, interestTypeId, subInterestTypeId);
					InterestType userInterestType = null;
					userDAO.getEntityManager().refresh(user);
					LOGGER.info(user.getUserInterestTypes().size());
					if (user.getUserInterestTypes().size() > 0) {
						interest = true;
						for (UserInterestType u : user.getUserInterestTypes()) {
							if (u.getPriority()) {
								userInterestType = u.getInterestType();
							}
						}
					}
					if (interest) {
						objectOfInterests.addAll(objectOfInterestService.findBest(userInterestType, latitude, longitude, venues));
					}
				}
				if (!interest) {
					LOGGER.info(interest);
					objectOfInterests.addAll(find(latitude, longitude, interestTypeId, subInterestTypeId));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		LOGGER.info("Devuelve la respuesta");
		return Response.ok(objectOfInterests).build();
	}

	public List<ObjectOfInterest> find(double latitude, double longitude, String interestTypeId, String subInterestTypeId) {
		List<ObjectOfInterest> objectOfInterests = new ArrayList<>();
		try {
			if (interestTypeId == null && subInterestTypeId == null) {
				objectOfInterests.addAll(objectOfInterestService.findBest(null, latitude, longitude, Collections.<CompactVenue>emptyList()));
			} else {
				InterestType interestType = interestTypeDAO.findById(interestTypeId);
				objectOfInterests.addAll(objectOfInterestService.findBest(interestType, latitude, longitude, Collections.<CompactVenue>emptyList()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return objectOfInterests;
	}
}
