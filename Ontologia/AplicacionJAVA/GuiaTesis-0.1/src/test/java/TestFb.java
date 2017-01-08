import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.Page;
import ec.edu.epn.guiaquito.entities.User;
import ec.edu.epn.guiaquito.enums.FacebookInterestTypeEnum;
import ec.edu.epn.guiaquito.services.UserInterestService;
import org.junit.Test;

import java.util.List;

/**
 * Description
 *
 * @author Xavier Ñauñay <xavierxc14@gmail.com>
 * @version 1.0
 */
public class TestFb {
	public static void main(String args[]) {
		FacebookClient facebookClient = new DefaultFacebookClient("EAAVwZCCK912EBAPZAJVOqSyicWZCmggoNXZBMDDFDL8OboZBnXtjNZBExLZCNr1y0j1J5TySmSq8db4md6JdBsOx2Fj94jEWTkMg5lJ6AxrZCqem2UsOn8wHrauxZCFUAEMCmUJB6HioaaJD6MnXZAZA3fel9zD5Gym4mM7oH49TUpClQZDZD");
//        Connection<Page> pageConnection = facebookClient.executeFqlQuery("SELECT page_id,type, description FROM page WHERE page_id IN (SELECT uid, page_id, type FROM page_fan WHERE uid=me()) AND type='musician/band'");
		Connection<Page> fetchConnection = facebookClient.fetchConnection("me/likes", Page.class, Parameter.with("fields", "id, name, category, last_used_time"));
//        fetchConnection.getTotalCount();
		int i = 0, j = 0;
		for (List<Page> c : fetchConnection) {
			for (Page page : c) {
				j++;
				if (page.getCategory().contains(FacebookInterestTypeEnum.HOTEL.getNameFacebook()) ||
						page.getCategory().contains(FacebookInterestTypeEnum.CHURCH.getNameFacebook()) ||
						page.getCategory().contains(FacebookInterestTypeEnum.RESTAURANT.getNameFacebook()) ||
						page.getCategory().contains(FacebookInterestTypeEnum.MUSEUM.getNameFacebook())) {
					System.out.println("Page\t\t\t|\t\t\tCategory");
					System.out.println(page.getName() + "\t\t\t|\t\t\t" + page.getCategory());
					System.out.println(page.getLastUsedTime());
					System.out.println("---------------------------------------------------------");

					i++;
				}
			}
		}
		System.out.println(i);
		System.out.println(j);
	}

	@Test
	public void sites() {
		UserInterestService userInterestService=new UserInterestService();
		User user=new User();
//		user.setFacebookToken("EAAVwZCCK912EBAPZAJVOqSyicWZCmggoNXZBMDDFDL8OboZBnXtjNZBExLZCNr1y0j1J5TySmSq8db4md6JdBsOx2Fj94jEWTkMg5lJ6AxrZCqem2UsOn8wHrauxZCFUAEMCmUJB6HioaaJD6MnXZAZA3fel9zD5Gym4mM7oH49TUpClQZDZD");
		user.setFacebookToken("EAACEdEose0cBAM9Xo0qXMZB0t1jeyUy27o6pzcQcuakd6uAvyTqENhdC1fzqLEDjstAXmXtdu5JGdhczrMYWAuuZBlLB4KNCpCB7CL43Yj8PnEZCJTAj7luvS5beJC0feHPbWOiJJ7WChzpd5d1orGzSEIdEUbdXMsatNVqcQZDZD");
		userInterestService.searchLikedSites(user);
//		FacebookClient facebookClient = new DefaultFacebookClient(, Version.VERSION_2_5);
//		Connection<Place> placeConnection = facebookClient.fetchConnection("search", Place.class, Parameter.with("type", "place"), Parameter.with("center", "-0.220379,-78.5120386"), Parameter.with("distance", 410));
//		for (List<Place> places : placeConnection) {
//			for (Place place : places) {
//				System.out.println(place.toString());
//			}
//
//		}
	}
}
