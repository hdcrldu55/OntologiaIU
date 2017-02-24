package ec.edu.epn.guiaquito.services.rs;

import ec.edu.epn.guiaquito.entities.PointOfInterest;
import ec.edu.epn.guiaquito.entities.User;
import ec.edu.epn.guiaquito.services.PointOfInterestService;
import ec.edu.epn.guiaquito.services.UserService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

@Stateless
@Path("/poi")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PointsOfInterestFacade {

    @EJB
    private PointOfInterestService pointOfInterestService;
    @EJB
    private UserService userService;

    @GET
    @Path("{userId}")
    public Response search(@PathParam("userId") Integer userId,
                           @QueryParam("latitude") double latitude,
                           @QueryParam("longitude") double longitude,
                           @QueryParam("types") String types) {
        String[] interests;
        if (types.contains("undefined")) {
            interests = new String[]{"museum", "church", "lodging", "food"};
        } else {
            interests = types.split("\\|");
        }
        User user = null;
        try {
            user = userService.findById(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //TODO find interests by facebookId
        //interests debe llenarse con lo obtenido de facebook y se sigue la misma logica
        //busqueda de puntos con intereses del usuario o sin intereses
        System.out.println(Arrays.toString(interests));
        List<PointOfInterest> pointOfInterests = pointOfInterestService.queryFromGoogle(latitude, longitude, interests);
        return Response.ok(pointOfInterests).build();
    }

}
