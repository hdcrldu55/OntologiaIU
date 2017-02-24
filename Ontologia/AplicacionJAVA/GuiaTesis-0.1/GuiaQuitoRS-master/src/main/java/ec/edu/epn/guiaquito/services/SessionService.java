package ec.edu.epn.guiaquito.services;

import ec.edu.epn.guiaquito.entities.Session;
import ec.edu.epn.guiaquito.services.base.BaseService;

public interface SessionService extends BaseService<Session, Integer> {

    Session findByFacebookId(Long facebookId) throws Exception;
}
