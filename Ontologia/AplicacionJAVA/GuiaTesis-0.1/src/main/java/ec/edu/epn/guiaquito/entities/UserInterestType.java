package ec.edu.epn.guiaquito.entities;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Embeddable
public class UserInterestType extends UserInterest {

	@ManyToOne
	@JoinColumn(insertable = false, updatable = false)
	private InterestType interestType;

	@Transient
	private String userId;

	public InterestType getInterestType() {
		return interestType;
	}

	public void setInterestType(InterestType interestType) {
		this.interestType = interestType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
