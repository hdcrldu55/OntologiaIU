package ec.edu.epn.guiaquito.services.rs.params;

/**
 * Description.
 *
 * @author Xavier Ñauñay <xavierxc14@gmail.com>
 * @version 1.0
 */
public class PlaceParam {

	private String userId;

	private String placeId;

	private Double rating;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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
