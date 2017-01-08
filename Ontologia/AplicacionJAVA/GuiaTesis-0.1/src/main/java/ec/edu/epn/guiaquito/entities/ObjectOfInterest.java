package ec.edu.epn.guiaquito.entities;

import org.hibernate.annotations.Type;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.HashMap;
import java.util.Map;

public class ObjectOfInterest {

	@Id
	@Type(type = "objectid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	private String placeId;

	private String name;

	private Double latitude;

	private Double longitude;

	private Float rating;

	private InterestType interestType;

	private String icon;

	private Map<String, String> content;

	public ObjectOfInterest() {
		content = new HashMap<>();
	}

	public ObjectOfInterest(String name, Double latitude, Double longitude, Float rating,
	                        InterestType interestType, String icon, String placeId) {
		this();
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.rating = rating;
		this.interestType = interestType;
		this.icon = icon;
		this.placeId = placeId;
	}

	public ObjectOfInterest(String name, Double latitude, Double longitude, Float rating,
	                        String icon, String placeId) {
		this();
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.rating = rating;
		this.icon = icon;
		this.placeId = placeId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}

	public InterestType getInterestType() {
		return interestType;
	}

	public void setInterestType(InterestType interestType) {
		this.interestType = interestType;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Map<String, String> getContent() {
		return content;
	}

	public void setContent(Map<String, String> content) {
		this.content = content;
	}
}
