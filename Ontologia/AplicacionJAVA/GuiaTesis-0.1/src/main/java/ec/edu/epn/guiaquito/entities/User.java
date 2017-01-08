package ec.edu.epn.guiaquito.entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class User {

	@Id
	@Type(type = "objectid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	private Long facebookId;

	private String firstName;

	private String lastName;

	private Date birthday;

	private String email;

	private String facebookToken;

	@ElementCollection(fetch = FetchType.EAGER)
	private List<UserInterestType> userInterestTypes;

	@ElementCollection(fetch = FetchType.EAGER)
	private List<VisitedPlace> visitedPlaces;

	public User() {
		userInterestTypes = new ArrayList<>();
		visitedPlaces = new ArrayList<>();
	}

	@Override
	public String toString() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(this);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(Long facebookId) {
		this.facebookId = facebookId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFacebookToken() {
		return facebookToken;
	}

	public void setFacebookToken(String facebookToken) {
		this.facebookToken = facebookToken;
	}

	public List<UserInterestType> getUserInterestTypes() {
		return userInterestTypes;
	}

	public void setUserInterestTypes(List<UserInterestType> userInterestTypes) {
		this.userInterestTypes = userInterestTypes;
	}

	public List<VisitedPlace> getVisitedPlaces() {
		return visitedPlaces;
	}

	public void setVisitedPlaces(List<VisitedPlace> visitedPlace) {
		this.visitedPlaces = visitedPlace;
	}
}
