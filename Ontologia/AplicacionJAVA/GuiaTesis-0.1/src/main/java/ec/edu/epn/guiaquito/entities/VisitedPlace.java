package ec.edu.epn.guiaquito.entities;

import javax.persistence.Embeddable;

/**
 * Description.
 *
 * @author Xavier Ñauñay <xavierxc14@gmail.com>
 * @version 1.0
 */
@Embeddable
public class VisitedPlace {

	private String placeId;

	private Double rating;

	public VisitedPlace() {
		this.rating = 0D;
	}

	public String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}
}
