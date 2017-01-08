package ec.edu.epn.guiaquito.enums;

import com.google.maps.model.PlaceType;

public enum GoogleInterestTypeEnum {

	HOTEL(PlaceType.LODGING, "H"),
	CHURCH(PlaceType.CHURCH, "C"),
	RESTAURANT(PlaceType.FOOD, "F"),
	MUSEUM(PlaceType.MUSEUM, "M");

	private PlaceType placeType;
	private String code;

	GoogleInterestTypeEnum(PlaceType placeType, String code) {
		this.placeType = placeType;
		this.code = code;
	}

	public PlaceType getPlaceType() {
		return placeType;
	}

	public String getCode() {
		return code;
	}
}
