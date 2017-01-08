package ec.edu.epn.guiaquito.utils;

import ec.edu.epn.guiaquito.entities.ObjectOfInterest;

public class Haversine {
	private static final int EARTH_RADIUS = 6371; // Approx Earth radius in KM

	public static double distance(double latitude, double longitude,
	                              ObjectOfInterest objectOfInterest) {

		double dLat = Math.toRadians((objectOfInterest.getLatitude() - latitude));
		double dLong = Math.toRadians((objectOfInterest.getLongitude() - longitude));

		double startLat = Math.toRadians(latitude);
		double endLat = Math.toRadians(objectOfInterest.getLatitude());

		double a = haversin(dLat) + Math.cos(startLat) * Math.cos(endLat) * haversin(dLong);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		return EARTH_RADIUS * c; // <-- d
	}

	private static double haversin(double val) {
		return Math.pow(Math.sin(val / 2), 2);
	}
}
