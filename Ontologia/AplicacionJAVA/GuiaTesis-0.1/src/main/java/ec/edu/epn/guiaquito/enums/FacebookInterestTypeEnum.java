package ec.edu.epn.guiaquito.enums;

public enum FacebookInterestTypeEnum {

	HOTEL("Hotel", "H"),
	BAGS("Bags/Luggage", "H"),
	BEAUTIFY("Health/Beauty", "H"),
	SPA("Spas/Beauty/Personal Care", "H"),
	
	CHURCH("Church/Religious Organization", "C"),
	BUILDING("Building Materials", "C"),
	HOMEDECOR("Home Decor", "C"),
	CONSTRUCTION("Engineering/Construction", "C"),
	
	RESTAURANT("Restaurant/Cafe", "F"),
	BEVERAGES("Food/Beverages", "F"),
	BAR("Bar", "F"),
	GROCERY("Food/Grocery", "F"),
	CHEF("Chef", "F"),
	CLUB("Club", "F"),
	KITCHEN("Kitchen/Cooking", "F"),
	WINE("Wine/Spirits", "F"),
	TRAVEL("Travel/Leisure", "F"),
	VITAMINS("Vitamins/Supplements", "F"),
	
	MUSEUM("Museum/Art Gallery", "M"),
	BOOKSTORE("Book Store", "M"),
	LIBRARY("Library", "M"),
	ARTS("Arts/Entertainment/Nightlife", "M"),
	PUBLICPLACES("Public Places", "M"),
	ATTRACTIONS("Attractions/Things to Do", "M");

	private String nameFacebook;
	private String code;

	FacebookInterestTypeEnum(String nameFacebook, String code) {
		this.nameFacebook = nameFacebook;
		this.code = code;
	}

	public String getNameFacebook() {
		return nameFacebook;
	}

	public String getCode() {
		return code;
	}
}
