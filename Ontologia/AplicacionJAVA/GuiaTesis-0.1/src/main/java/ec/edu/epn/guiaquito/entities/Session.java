package ec.edu.epn.guiaquito.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Session {

	@Id
	@Type(type = "objectid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	private Date time;

	@ManyToOne
	@JoinColumn(insertable = false, updatable = false)
	private User user;

	@Transient
	private String userId;

	@Embedded
	private Context context;

	@ElementCollection
	@JsonIgnore
	private List<Interaction> interactions;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date date) {
		this.time = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public List<Interaction> getInteractions() {
		return interactions;
	}

	public void setInteractions(List<Interaction> interactions) {
		this.interactions = interactions;
	}
}
