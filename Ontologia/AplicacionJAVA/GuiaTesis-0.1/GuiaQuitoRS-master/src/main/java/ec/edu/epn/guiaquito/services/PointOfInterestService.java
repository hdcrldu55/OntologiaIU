package ec.edu.epn.guiaquito.services;

import ec.edu.epn.guiaquito.entities.PointOfInterest;
import ec.edu.epn.guiaquito.services.base.BaseService;
import net.sf.sprockets.google.Place;

import java.util.List;

public interface PointOfInterestService extends BaseService<PointOfInterest, Integer> {

    List<PointOfInterest> queryFromGoogle(double latitude, double longitude, String[] campos);

    List<PointOfInterest> addDescriptionFromWikipedia(List<Place> places, String[] interests);
}
