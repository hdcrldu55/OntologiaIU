package ec.edu.epn.guiaquito.services;

import com.restfb.*;
import com.restfb.types.Page;
import com.restfb.types.Place;
import ec.edu.epn.guiaquito.dao.InterestTypeDAO;
import ec.edu.epn.guiaquito.dao.UserDAO;
import ec.edu.epn.guiaquito.dao.UserInterestTypeDAO;
import ec.edu.epn.guiaquito.entities.InterestType;
import ec.edu.epn.guiaquito.entities.User;
import ec.edu.epn.guiaquito.entities.UserInterestType;
import ec.edu.epn.guiaquito.enums.FacebookInterestTypeEnum;
import fi.foyt.foursquare.api.FoursquareApi;
import fi.foyt.foursquare.api.Result;
import fi.foyt.foursquare.api.entities.CompactVenue;
import fi.foyt.foursquare.api.entities.VenuesSearchResult;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.*;

@Stateless
public class UserInterestService {

	private static final Logger LOGGER = Logger.getLogger(UserInterestService.class);

	@EJB
	private UserDAO userDAO;

	@EJB
	private InterestTypeDAO interestTypeDAO;

	@EJB
	private UserInterestTypeDAO userInterestTypeDAO;

	public List<CompactVenue> searchLikedSites(User user) {
		Map<String, Page> likes = new HashMap<>();
		List<CompactVenue> placeList = new ArrayList<>();
		FacebookClient facebookClient = new DefaultFacebookClient(user.getFacebookToken(), Version.VERSION_2_5);
		Connection<Page> pageConnection = facebookClient.fetchConnection("me/likes", Page.class, Parameter.with("fields", "id, name, category, likes, best_page"));
		for (List<Page> pages : pageConnection) {
			for (Page page : pages) {
				String bestPageId = page.getBestPage() != null ? page.getBestPage().getId() : page.getId();
				likes.put(bestPageId, page);
			}
		}
		Connection<Place> placeConnection = facebookClient.fetchConnection("search", Place.class, Parameter.with("type", "place"), Parameter.with("center", "-0.220379,-78.5120386"), Parameter.with("distance", 410));
		for (List<Place> places : placeConnection) {
			for (Place place : places) {
				Page page = facebookClient.fetchObject(place.getId(), Page.class, Parameter.with("fields", "id, name, category, likes, best_page"));
				String bestPageId = page.getBestPage() != null ? page.getBestPage().getId() : page.getId();
//				System.out.println(place.getName() + "\t\t" + place.getId() + "\t\t" + bestPageId);
				if (likes.containsKey(bestPageId)) {
					CompactVenue[] venues = findOnFoursquare(place);
					if (venues.length > 0) {
						placeList.add(venues[0]);
					}
				}
			}
		}
		System.out.println(placeList.size());
		return placeList;
	}

	public CompactVenue[] findOnFoursquare(Place place) {
		FoursquareApi foursquareApi = new FoursquareApi("0OC52I2T5V3DBUGLL5LMXCCOEH1YAJT3BRDSYNVQGNXKTCVM", "V0CP0FXJARG52XADIQT34ITJPW1DGSRTMFLCZA22LVM3XF3K", null);
		Map<String, String> params = new HashMap<>();
		String ll = place.getLocation().getLatitude() + "," + place.getLocation().getLongitude();
		params.put("ll", ll);
		params.put("radius", String.valueOf(5));
		params.put("limit", "1");
		try {
			Result<VenuesSearchResult> result = foursquareApi.venuesSearch(params);
			if (result.getMeta().getCode() == 200) {
//				LOGGER.info(Arrays.deepToString(result.getResult().getVenues()));
				return result.getResult().getVenues();
			}
		} catch (Exception e) {
			LOGGER.error("Error al consultar puntos de interes desde Foursquare", e);
		}
		return new CompactVenue[0];
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void updateInterest(User user, String interestTypeId, String subInterestTypeId) throws Exception {
		if (interestTypeId != null) {
			updateInterest(user, interestTypeId);
			if (subInterestTypeId != null) {
				updateSubInterest(user, subInterestTypeId);
			}
		} else {
			if (user.getUserInterestTypes().size() == 0) {
				InterestType interestType = findOnFacebook(user);
				if (interestType != null) {
					updateInterest(user, interestType.getId());
				}
			}
		}
	}

	private void updateInterest(User user, String interestTypeId) {
		Boolean exist = false;
		try {
			for (UserInterestType userInterestType : user.getUserInterestTypes()) {
				userInterestType.setPriority(false);
				if (userInterestType.getInterestType().getId().equals(interestTypeId)) {
					userInterestType.setPriority(true);
					exist = true;
				}
				userDAO.update(user);
			}
			if (!exist) {
				UserInterestType userInterestType = new UserInterestType();
				InterestType interestType = interestTypeDAO.findById(interestTypeId);
				userInterestType.setInterestType(interestType);
				userInterestType.setPriority(true);
				user.getUserInterestTypes().add(userInterestType);
				userDAO.update(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void updateSubInterest(User user, String subInterestTypeId) {
		//TODO pendiente revizar
//		Boolean exist = false;
//		try {
//			for (UserSubInterestType userSubInterestType : user.getUserSubInterestTypes()) {
//				userSubInterestType.setPriority(false);
//				if (userSubInterestType.getSubInterestType().getId().equals(subInterestTypeId)) {
//					userSubInterestType.setPriority(true);
//				}
//				userDAO.update(user);
//			}
//			if (!exist) {
//				UserSubInterestType userSubInterestType = new UserSubInterestType();

//				userSubInterestType.setUserId(user.getId());
//				SubInterestType subInterestType = subInterestTypeDAO.findById(subInterestTypeId);
//				userSubInterestType.setSubInterestTypeId(subInterestType.getId());
//				userSubInterestType.setPriority(true);
//				user.getUserSubInterestTypes().add(userSubInterestType);
//				userDAO.update(user);
//				userSubInterestType = userSubInterestTypeDAO.create(userSubInterestType);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	public InterestType findOnFacebook(User user) throws Exception {
		FacebookClient facebookClient = new DefaultFacebookClient(user.getFacebookToken(), Version.VERSION_2_5);
		Connection<Page> fetchConnection = facebookClient.fetchConnection("me/likes", Page.class, Parameter.with("fields", "id, name, category, likes"));
		List<Page> hotels = new ArrayList<>();
		List<Page> churches = new ArrayList<>();
		List<Page> restaurants = new ArrayList<>();
		List<Page> museums = new ArrayList<>();

		for (List<Page> connection : fetchConnection) {
			for (Page page : connection) {
				if (page.getCategory().contains(FacebookInterestTypeEnum.HOTEL.getNameFacebook())) {
					hotels.add(page);
				} else if (page.getCategory().contains(FacebookInterestTypeEnum.CHURCH.getNameFacebook())) {
					churches.add(page);
				} else if (page.getCategory().contains(FacebookInterestTypeEnum.RESTAURANT.getNameFacebook())
						|| page.getCategory().contains(FacebookInterestTypeEnum.BEVERAGES.getNameFacebook())
						|| page.getCategory().contains(FacebookInterestTypeEnum.BAR.getNameFacebook())
						|| page.getCategory().contains(FacebookInterestTypeEnum.GROCERY.getNameFacebook())) {
					restaurants.add(page);
				} else if (page.getCategory().contains(FacebookInterestTypeEnum.MUSEUM.getNameFacebook())) {
					museums.add(page);
				}
			}
		}
		return filterInterest(hotels, churches, restaurants, museums);
	}

	private InterestType filterInterest(List<Page> hotels, List<Page> churches, List<Page> restaurants, List<Page> museums) {
		//TODO: Sacar mas de un interes si tienen igual cantidad
		InterestType interestType = null;
		LOGGER.info("Hoteles: " + hotels.size());
		LOGGER.info("Iglesias: " + churches.size());
		LOGGER.info("Restaurants: " + restaurants.size());
		LOGGER.info("Museos: " + museums.size());
		if (hotels.size() == churches.size() &&
				churches.size() == restaurants.size() &&
				restaurants.size() == museums.size()) {
			if (hotels.size() == 0) {
				System.out.println("Tiene intereses en cero");
			} else {
				List<Page> pages = new ArrayList<>();
				pages.addAll(hotels);
				pages.addAll(churches);
				pages.addAll(restaurants);
				pages.addAll(museums);
				interestType = sortForLikes(pages);
			}
		} else {
			interestType = sortForPages(hotels, churches, restaurants, museums);
		}
		return interestType;
	}

	private InterestType sortForLikes(List<Page> pages) {
		InterestType interestType = null;
		String code;
		Comparator<Page> comparator = new Comparator<Page>() {
			@Override
			public int compare(Page o1, Page o2) {
				return o2.getLikes().compareTo(o1.getLikes());
			}
		};
		Collections.sort(pages, comparator);
		Page page = pages.get(0);
		if (page.getCategory().contains(
				FacebookInterestTypeEnum.HOTEL.getNameFacebook())) {
			code = FacebookInterestTypeEnum.HOTEL.getCode();
		} else if (page.getCategory().contains(
				FacebookInterestTypeEnum.CHURCH.getNameFacebook())) {
			code = FacebookInterestTypeEnum.CHURCH.getCode();
		} else if (page.getCategory().contains(
				FacebookInterestTypeEnum.RESTAURANT.getNameFacebook()) ||
				page.getCategory().contains(
						FacebookInterestTypeEnum.BEVERAGES.getNameFacebook()) ||
				page.getCategory().contains(
						FacebookInterestTypeEnum.BAR.getNameFacebook()) ||
				page.getCategory().contains(
						FacebookInterestTypeEnum.GROCERY.getNameFacebook())) {
			code = FacebookInterestTypeEnum.RESTAURANT.getCode();
		} else {
			code = FacebookInterestTypeEnum.MUSEUM.getCode();
		}
		try {
			interestType = interestTypeDAO.findByCode(code);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return interestType;
	}

	private InterestType sortForPages(List<Page> hotels, List<Page> churches,
	                                  List<Page> restaurants, List<Page> museums) {
		InterestType interestType = null;
		Map<List<Page>, Integer> numberOfPages = new HashMap<>();
		numberOfPages.put(hotels, hotels.size());
		numberOfPages.put(churches, churches.size());
		numberOfPages.put(restaurants, restaurants.size());
		numberOfPages.put(museums, museums.size());
		Map<List<Page>, Integer> numberOfPagesSorted = sortMap(numberOfPages);
		Map.Entry<List<Page>, Integer> firstElement = numberOfPagesSorted.entrySet().iterator().next();
		List<Page> page = firstElement.getKey();
		LOGGER.info(page.get(0).getName() + " " + page.get(0).getCategory());
		for (FacebookInterestTypeEnum i : FacebookInterestTypeEnum.values()) {
			if (i.getNameFacebook().contains(page.get(0).getCategory())) {
				LOGGER.info(i.getCode());
				try {
					interestType = interestTypeDAO.findByCode(i.getCode());
					LOGGER.info(interestType);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return interestType;
	}

	private Map<List<Page>, Integer> sortMap(Map<List<Page>, Integer> numberOfPages) {
		List<Map.Entry<List<Page>, Integer>> list = new LinkedList<>(numberOfPages.entrySet());
		Comparator<Map.Entry<List<Page>, Integer>> comparator = new Comparator<Map.Entry<List<Page>, Integer>>() {
			public int compare(Map.Entry<List<Page>, Integer> o1,
			                   Map.Entry<List<Page>, Integer> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		};
		Collections.sort(list, comparator);
		Map<List<Page>, Integer> sortedMap = new LinkedHashMap<>();
		for (Map.Entry<List<Page>, Integer> entry : list) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}
}
