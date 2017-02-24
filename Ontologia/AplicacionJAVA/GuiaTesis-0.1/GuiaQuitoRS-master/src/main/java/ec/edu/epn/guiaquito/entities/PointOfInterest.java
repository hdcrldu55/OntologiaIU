package ec.edu.epn.guiaquito.entities;

import java.util.List;

public class PointOfInterest {
	private Double latitude;
	private Double longitude;
	private String name;
	private Float rating;
	private String description;
	private List<String> types;

	public PointOfInterest(Double latitude, Double longitude, String name,
	                       Float rating, String description, List<String> types) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.name = name;
		this.rating = rating;
		this.description = description;
		this.types = types;
	}

	public Double getLatitude() {
		return latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public String getName() {
		return name;
	}

	public Float getRating() {
		return rating;
	}

	public String getDescription() {
		return description;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getTypes() {
		return types;
	}

	public void setTypes(List<String> types) {
		this.types = types;
	}
}
