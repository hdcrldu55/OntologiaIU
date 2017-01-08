import fi.foyt.foursquare.api.FoursquareApi;
import fi.foyt.foursquare.api.FoursquareApiException;
import fi.foyt.foursquare.api.Result;
import fi.foyt.foursquare.api.entities.Category;
import fi.foyt.foursquare.api.entities.CompactVenue;
import fi.foyt.foursquare.api.entities.VenuesSearchResult;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xavier on 01/04/16.
 */
public class FoursquareTest {
	public static void main(String args[]) {
		FoursquareTest foursquareTest = new FoursquareTest();
		try {
			foursquareTest.searchVenues("-0.220379,-78.5120386");
		} catch (FoursquareApiException e) {
			e.printStackTrace();
		}
	}

	public void searchVenues(String ll) throws FoursquareApiException {
		// First we need a initialize FoursquareApi.
		FoursquareApi foursquareApi = new FoursquareApi("0OC52I2T5V3DBUGLL5LMXCCOEH1YAJT3BRDSYNVQGNXKTCVM", "V0CP0FXJARG52XADIQT34ITJPW1DGSRTMFLCZA22LVM3XF3K", null);

		// After client has been initialized we can make queries.
		Map<String, String> params = new HashMap<>();
		params.put("ll", ll);
		params.put("query", "museo");
		Result<VenuesSearchResult> result = foursquareApi.venuesSearch(params);

		if (result.getMeta().getCode() == 200) {
			// if query was ok we can finally we do something with the data
			System.out.println("Name\t\t\t\t\t\t\t\t\t\t\t\tCategory");
			for (CompactVenue venue : result.getResult().getVenues()) {
				if (venue.getCategories().length > 0) {
					Category category = venue.getCategories()[0];

					System.out.println(venue.getName() + "\t\t\t\t\t" + category.getName());
				} else {
					System.out.println("Name sin category" + venue.getName());
				}
			}
		} else {
			System.out.println("Error occured: ");
			System.out.println("  code: " + result.getMeta().getCode());
			System.out.println("  type: " + result.getMeta().getErrorType());
			System.out.println("  detail: " + result.getMeta().getErrorDetail());
		}
	}
}
