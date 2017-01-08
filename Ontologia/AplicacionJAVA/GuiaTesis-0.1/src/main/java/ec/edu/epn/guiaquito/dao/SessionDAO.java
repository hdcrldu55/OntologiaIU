package ec.edu.epn.guiaquito.dao;

import ec.edu.epn.guiaquito.dao.base.BaseDAO;
import ec.edu.epn.guiaquito.entities.Session;

import javax.ejb.Stateless;
import javax.persistence.Query;

@Stateless
public class SessionDAO extends BaseDAO<Session, String> {

	public SessionDAO() {
		super(Session.class);
	}

	public Session findByFacebookId(Long facebookId) throws Exception {
		Session session;
		Query q = getEntityManager().createQuery(SELECT_STATEMENT + "Session e WHERE e.user.facebookId = :facebookId");
		q.setParameter("facebookId", facebookId);
		session = (Session) q.getResultList().get(0);
		return session;
	}
}
