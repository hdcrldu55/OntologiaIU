package ec.edu.epn.guiaquito.services;

import com.google.maps.GeoApiContext;
import com.google.maps.NearbySearchRequest;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlaceType;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.PlacesSearchResult;

/**
 * Description.
 *
 * @author Xavier Ñauñay <xavierxc14@gmail.com>
 * @version 1.0
 */
public class PlacesTest {

	public static void main(String[] args) {
		GeoApiContext geoApiContext = new GeoApiContext().setApiKey("AIzaSyBZzx_zsBJQEn5ufg9HqtZl7nDRAbjfApI");
		NearbySearchRequest nearbySearchRequest = new NearbySearchRequest(geoApiContext);
		nearbySearchRequest.radius(5000);
		nearbySearchRequest.type(PlaceType.CHURCH);
		nearbySearchRequest.location(new LatLng(-0.2203834, -78.517477));
		nearbySearchRequest.name("concepc");
		try {
			System.out.println("PlaceId\t\t\tName");
			PlacesSearchResponse response = nearbySearchRequest.await();
//			String token = response.nextPageToken;
//			while (token != null) {
			for (PlacesSearchResult result : response.results) {
				System.out.println(result.placeId + "\t\t" + result.name);
			}
//				NearbySearchRequest nearbySearchRequest1 = new NearbySearchRequest(geoApiContext);
//				nearbySearchRequest1.pageToken(token);
//				response = nearbySearchRequest1.await();
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
