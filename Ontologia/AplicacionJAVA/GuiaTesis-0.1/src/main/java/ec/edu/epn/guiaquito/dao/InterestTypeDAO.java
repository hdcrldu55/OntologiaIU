package ec.edu.epn.guiaquito.dao;

import ec.edu.epn.guiaquito.dao.base.BaseDAO;
import ec.edu.epn.guiaquito.entities.InterestType;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class InterestTypeDAO extends BaseDAO<InterestType, String> {

	public InterestTypeDAO() {
		super(InterestType.class);
	}

	public InterestType findByCode(String code) throws Exception {
		TypedQuery<InterestType> q = getEntityManager().createQuery(SELECT_STATEMENT + "InterestType e WHERE e.code like :code", InterestType.class);
		q.setParameter("code", code);
		List<InterestType> interestTypes = q.getResultList();
		if (interestTypes.size() > 0) {
			return interestTypes.get(0);
		} else {
			return null;
		}
	}
}
