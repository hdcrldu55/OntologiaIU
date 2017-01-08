package ec.edu.epn.guiaquito.entities;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.util.Date;

@MappedSuperclass
public abstract class UserTracking {

	private Double longitude;

	private Double latitude;

	private Double orientation;

	private Date time;

	@Transient
	private String sessionId;

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getOrientation() {
		return orientation;
	}

	public void setOrientation(Double orientation) {
		this.orientation = orientation;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date date) {
		this.time = date;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
}
