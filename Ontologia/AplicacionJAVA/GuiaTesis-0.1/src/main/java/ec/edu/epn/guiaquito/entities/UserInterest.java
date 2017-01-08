package ec.edu.epn.guiaquito.entities;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class UserInterest {

	private Boolean priority = false;

	public Boolean getPriority() {
		return priority;
	}

	public void setPriority(Boolean priority) {
		this.priority = priority;
	}
}
