package ec.edu.epn.guiaquito.utils;

import com.google.gson.Gson;
import ec.edu.epn.guiaquito.entities.InterestType;
import ec.edu.epn.guiaquito.entities.ObjectOfInterest;
import fi.foyt.foursquare.api.entities.Category;
import fi.foyt.foursquare.api.entities.CompactVenue;
import fi.foyt.foursquare.api.entities.Icon;

/**
 * Description.
 *
 * @author Xavier Ñauñay <xavierxc14@gmail.com>
 * @version 1.0
 */
public class ObjectOfInterestUtil {

	private static Gson gson = new Gson();

	public static ObjectOfInterest convertToObjectOfInterest(CompactVenue place, InterestType interestType) {
		String icon;
//		if (interestType != null) {
//			switch (interestType.getCode()) {
//				case "C":
//					icon = "church.png";
//					break;
//				case "H":
//					icon = "lodging.png";
//					break;
//				case "M":
//					icon = "museum.png";
//					break;
//				case "F":
//					icon = "food.png";
//					break;
//				default:
//					icon = extractIcon(place.getCategories());
//					break;
//			}
//		} else {
		icon = extractIcon(place.getCategories());
//		}
		return new ObjectOfInterest(place.getName(), place.getLocation().getLat(),
				place.getLocation().getLng(), 0F, interestType, icon, place.getId());
	}

	public static ObjectOfInterest convertToObjectOfInterest(CompactVenue place) {
		String icon = extractIcon(place.getCategories());
		System.out.println(icon);
		return new ObjectOfInterest(place.getName(), place.getLocation().getLat(),
				place.getLocation().getLng(), 0F, icon, place.getId());
	}

	private static String extractIcon(Category[] categories) {
		String icon = "";
		for (Category category : categories) {
			if (category.getPrimary()) {
				Icon icon1 = gson.fromJson(category.getIcon(), Icon.class);
				icon = icon1.getPrefix() + "bg_32" + icon1.getSuffix();
				break;
			}
		}
		return icon;
	}
}
