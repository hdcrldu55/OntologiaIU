package ec.edu.epn.guiaquito.services.impl;

import ec.edu.epn.guiaquito.entities.Session;
import ec.edu.epn.guiaquito.services.SessionService;
import ec.edu.epn.guiaquito.services.base.BaseServiceImpl;

import javax.ejb.Stateless;
import javax.persistence.Query;

@Stateless
public class SessionServiceImpl extends BaseServiceImpl<Session, Integer> implements SessionService {

    @Override
    public Session findByFacebookId(Long facebookId) throws Exception {
        Session session;
        Query q = getEntityManager().createQuery("SELECT s FROM Session s WHERE s.user.facebookId =:facebookId");
        q.setParameter("facebookId", facebookId);
        session = (Session) q.getResultList().get(0);
        return session;
    }
}
