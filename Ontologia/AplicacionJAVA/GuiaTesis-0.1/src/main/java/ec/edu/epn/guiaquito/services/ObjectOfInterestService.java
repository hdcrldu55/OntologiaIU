package ec.edu.epn.guiaquito.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.maps.GeoApiContext;
import com.google.maps.NearbySearchRequest;
import com.google.maps.PlaceDetailsRequest;
import com.google.maps.model.*;
import ec.edu.epn.guiaquito.dao.InterestTypeDAO;
import ec.edu.epn.guiaquito.entities.InterestType;
import ec.edu.epn.guiaquito.entities.ObjectOfInterest;
import ec.edu.epn.guiaquito.enums.GoogleInterestTypeEnum;
import ec.edu.epn.guiaquito.utils.ObjectOfInterestUtil;
import fi.foyt.foursquare.api.FoursquareApi;
import fi.foyt.foursquare.api.Result;
import fi.foyt.foursquare.api.entities.CompactVenue;
import fi.foyt.foursquare.api.entities.VenuesSearchResult;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.IOException;
import java.net.URL;
import java.util.*;

@Stateless
public class ObjectOfInterestService {

	private static final Logger LOGGER = Logger.getLogger(ObjectOfInterestService.class);

	private static final int DISTANCE = 410;

	private static final GeoApiContext GEO_API_CONTEXT = new GeoApiContext().setApiKey(ConfigurationService.GEO_API_CONTEXT);

	@EJB
	private InterestTypeDAO interestTypeDAO;

	private InterestType church;
	private InterestType hotel;
	private InterestType museum;
	private InterestType food;

	@PostConstruct
	private void init() {
		try {
			church = interestTypeDAO.findByCode("C");
			hotel = interestTypeDAO.findByCode("H");
			museum = interestTypeDAO.findByCode("M");
			food = interestTypeDAO.findByCode("F");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private List<ObjectOfInterest> queryFoursquareByInterestType(double latitude, double longitude,
	                                                             InterestType interestType, List<CompactVenue> compactVenues) {
		if (interestType.getCode().equals(GoogleInterestTypeEnum.HOTEL.getCode())) {
			return queryFoursquareByInterestType(latitude, longitude, hotel,
					ConfigurationService.FS_HOTEL_ID, true, compactVenues);
		} else if (interestType.getCode().equals(GoogleInterestTypeEnum.CHURCH.getCode())) {
			return queryFoursquareByInterestType(latitude, longitude, church,
					ConfigurationService.FS_CHURCH_ID, true, compactVenues);
		} else if (interestType.getCode().equals(GoogleInterestTypeEnum.MUSEUM.getCode())) {
			return queryFoursquareByInterestType(latitude, longitude, museum,
					ConfigurationService.FS_ENTERTAINMENT_ID, true, compactVenues);
		} else if (interestType.getCode().equals(GoogleInterestTypeEnum.RESTAURANT.getCode())) {
			return queryFoursquareByInterestType(latitude, longitude, food,
					ConfigurationService.FS_FOOD_ID, true, compactVenues);
		}
		return queryAllCategories(latitude, longitude, compactVenues);
	}

	private List<ObjectOfInterest> queryAllCategories(double latitude, double longitude, List<CompactVenue> compactVenues) {
		List<ObjectOfInterest> objectOfInterests = new ArrayList<>();
		objectOfInterests.addAll(queryFoursquareByInterestType(latitude, longitude, food,
				ConfigurationService.FS_FOOD_ID, false, compactVenues));
		objectOfInterests.addAll(queryFoursquareByInterestType(latitude, longitude, hotel,
				ConfigurationService.FS_HOTEL_ID, false, compactVenues));
		objectOfInterests.addAll(queryFoursquareByInterestType(latitude, longitude, church,
				ConfigurationService.FS_CHURCH_ID, false, compactVenues));
		objectOfInterests.addAll(queryFoursquareByInterestType(latitude, longitude, museum,
				ConfigurationService.FS_ENTERTAINMENT_ID, false, compactVenues));
		return objectOfInterests;
	}

	private List<ObjectOfInterest> queryFoursquareByInterestType(double latitude, double longitude, InterestType interestType,
	                                                             String categoryId, boolean single, List<CompactVenue> compactVenues) {
		List<ObjectOfInterest> objectOfInterests = new ArrayList<>();
		FoursquareApi foursquareApi = new FoursquareApi("0OC52I2T5V3DBUGLL5LMXCCOEH1YAJT3BRDSYNVQGNXKTCVM", "V0CP0FXJARG52XADIQT34ITJPW1DGSRTMFLCZA22LVM3XF3K", null);
		Map<String, String> params = new HashMap<>();
		String ll = latitude + "," + longitude;
		params.put("ll", ll);
		params.put("locale", "es");
		params.put("categoryId", categoryId);
		params.put("radius", String.valueOf(DISTANCE));
		if (compactVenues.size() > 0) {
			params.put("limit", "10");
		} else {
			if (single) {
				params.put("limit", "5");
			} else {
				params.put("limit", "1");
			}
		}
		//Se almacena los likes en un mapa para una busqueda sencilla por el id
		Map<String, CompactVenue> compactVenueMap = new HashMap<>();
		Map<String, CompactVenue> queried = new HashMap<>();
		for (CompactVenue compactVenue : compactVenues) {
			compactVenueMap.put(compactVenue.getId(), compactVenue);
		}
		try {
			Result<VenuesSearchResult> result = foursquareApi.venuesSearch(params);
			if (result.getMeta().getCode() == 200) {
				//Se transforma el arreglo de venues en una cola para ir agregando al mapa hasta que se tengan 5 lugares
				Queue<CompactVenue> compactVenueQueue = new LinkedList<>(Arrays.asList(result.getResult().getVenues()));
				LOGGER.info(compactVenueQueue);
				while (compactVenueMap.size() + queried.size() < 5 && compactVenueQueue.size() > 0) {
					CompactVenue p = compactVenueQueue.poll();
					queried.put(p.getId(), p);
					}
				List<CompactVenue> places = new ArrayList<>(compactVenueMap.values());
				objectOfInterests = convertToObjectOfInterest(places, null);
				places = new ArrayList<>(queried.values());
				objectOfInterests.addAll(convertToObjectOfInterest(places, interestType));
			}
		} catch (Exception e) {
			LOGGER.error("Error al consultar puntos de interes desde Foursquare", e);
		}
		return objectOfInterests;
	}

	private List<ObjectOfInterest> convertToObjectOfInterest(List<CompactVenue> places, InterestType interestType) {
		List<ObjectOfInterest> objectOfInterests = new ArrayList<>();
		for (CompactVenue place : places) {
			ObjectOfInterest objectOfInterest = ObjectOfInterestUtil.convertToObjectOfInterest(place, interestType);
			String description = getDescriptionFromWikipedia(place.getLocation().getLat(), place.getLocation().getLng());
			objectOfInterest.getContent().put("description", description);
			objectOfInterests.add(objectOfInterest);
		}
		return objectOfInterests;
	}

	public List<ObjectOfInterest> queryGoogleByInterestType(double latitude, double longitude, PlaceType placeType) {
		List<PlacesSearchResult> places = new ArrayList<>();

		List<ObjectOfInterest> objectOfInterests = new ArrayList<>();

		NearbySearchRequest nearbySearchRequest = new NearbySearchRequest(GEO_API_CONTEXT);

		try {
			nearbySearchRequest.location(new LatLng(latitude, longitude)).radius(1000);
			nearbySearchRequest.type(placeType);
			PlacesSearchResponse placesSearchResponse = nearbySearchRequest.await();
			Collections.addAll(places, placesSearchResponse.results);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<PlacesSearchResult> sortedPoints = filterPlaces(places);
		try {
			for (PlacesSearchResult result : sortedPoints) {
				PlaceDetails placeDetails = getDetails(result.placeId);
				objectOfInterests.add(convert(placeDetails));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return objectOfInterests;
	}

	public PlaceDetails getDetails(String placeId) throws Exception {
		PlaceDetailsRequest placeDetailsRequest = new PlaceDetailsRequest(GEO_API_CONTEXT);
		placeDetailsRequest.placeId(placeId).language("es");
		return placeDetailsRequest.await();
	}

	public ObjectOfInterest convert(PlaceDetails details) {
		String icon = "default.png";
		InterestType interestType = null;
		for (String type : details.types) {
			if (type.equalsIgnoreCase(GoogleInterestTypeEnum.CHURCH.getPlaceType().toString())) {
				icon = "church.png";
				interestType = church;
			} else if (type.equalsIgnoreCase(GoogleInterestTypeEnum.HOTEL.getPlaceType().toString())) {
				icon = "lodging.png";
				interestType = hotel;
			} else if (type.equalsIgnoreCase(GoogleInterestTypeEnum.MUSEUM.getPlaceType().toString())) {
				icon = "museum.png";
				interestType = museum;
			} else if (type.equalsIgnoreCase(GoogleInterestTypeEnum.RESTAURANT.getPlaceType().toString())) {
				icon = "food.png";
				interestType = food;
			}
		}
		ObjectOfInterest objectOfInterest = new ObjectOfInterest(details.name, details.geometry.location.lat, details.geometry.location.lng, details.rating, icon, details.placeId);
		objectOfInterest.setInterestType(interestType);
		String description = getDescriptionFromWikipedia(details.geometry.location.lat, details.geometry.location.lng);
		objectOfInterest.getContent().put("description", description);
		objectOfInterest.getContent().put("address", details.formattedAddress);
		objectOfInterest.getContent().put("phoneNumber", details.internationalPhoneNumber);
		String url = details.url != null ? details.url.toString() : null;
		objectOfInterest.getContent().put("url", url);
		String website = details.url != null ? details.url.toString() : null;
		objectOfInterest.getContent().put("website", website);
		return objectOfInterest;
	}

	private List<PlacesSearchResult> filterPlaces(List<PlacesSearchResult> places) {
		List<PlacesSearchResult> filteredPlaces = sortPlaces(places);
		if (filteredPlaces.size() >= 3) {
			filteredPlaces = filteredPlaces.subList(0, 3);
		}
		return filteredPlaces;
	}

	private List<PlacesSearchResult> sortPlaces(List<PlacesSearchResult> places) {
		List<PlacesSearchResult> pois = new ArrayList<>(places);
		Comparator<PlacesSearchResult> comparator = new Comparator<PlacesSearchResult>() {
			@Override
			public int compare(PlacesSearchResult place1, PlacesSearchResult place2) {
				return new Float(place2.rating).compareTo(place1.rating);
			}
		};
		Collections.sort(pois, comparator);
		return pois;
	}

	public String getDescriptionFromWikipedia(Double latitude, Double longitude) {
		String geoSearchUrl = "https://es.wikipedia.org/w/api.php?action=query&format=json&formatversion=2&rawcontinue=true&list=geosearch&gsradius=20&gscoord="
				+ latitude + "|" + longitude;
		String pageId = "";
		try {
			JsonNode root = new ObjectMapper().readTree(new URL(geoSearchUrl));
			JsonNode query = root.path("query");
			JsonNode geoSearch = query.path("geosearch");
			if (geoSearch.has(0)) {
				JsonNode geoSearchJsonNode = geoSearch.get(0);
				pageId = geoSearchJsonNode.path("pageid").asText();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		String description = "";
		if (!pageId.isEmpty()) {
			String pageSearchUrl = "https://es.wikipedia.org/w/api.php?action=query&format=json&formatversion=2&rawcontinue=true&pageids="
					+ pageId + "&prop=extracts|info&exsentences=2";
			try {
				JsonNode root = new ObjectMapper().readTree(new URL(pageSearchUrl));
				JsonNode query = root.path("query");
				JsonNode pages = query.path("pages");
				if (pages.isArray()) {
					JsonNode geoSearchJsonNode = pages.get(0);
					description = geoSearchJsonNode.path("extract").asText();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return description;
	}

	public List<ObjectOfInterest> findBest(InterestType interestType, double latitude, double longitude, List<CompactVenue> compactVenues) {
		List<ObjectOfInterest> objectOfInterests;
		if (interestType == null) {
			objectOfInterests = queryAllCategories(latitude, longitude, compactVenues);
		} else {
			objectOfInterests = queryFoursquareByInterestType(latitude, longitude, interestType, compactVenues);
		}
		return objectOfInterests;
	}
}
