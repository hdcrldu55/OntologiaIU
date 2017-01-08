package ec.edu.epn.guiaquito.dao;

import ec.edu.epn.guiaquito.dao.base.BaseDAO;
import ec.edu.epn.guiaquito.entities.User;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

@Stateless
public class UserDAO extends BaseDAO<User, String> {

	public UserDAO() {
		super(User.class);
	}

	public User findByFacebookId(Long facebookId) {
		TypedQuery<User> q = getEntityManager().createQuery(SELECT_STATEMENT + "User e WHERE e.facebookId = :facebookId", User.class);
		q.setParameter("facebookId", facebookId);
		return q.getResultList().get(0);
	}
}
