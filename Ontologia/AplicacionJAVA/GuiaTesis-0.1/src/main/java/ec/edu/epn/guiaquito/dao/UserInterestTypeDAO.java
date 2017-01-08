package ec.edu.epn.guiaquito.dao;

import ec.edu.epn.guiaquito.dao.base.BaseDAO;
import ec.edu.epn.guiaquito.entities.UserInterestType;

import javax.ejb.Stateless;

@Stateless
public class UserInterestTypeDAO extends BaseDAO<UserInterestType, String> {

	public UserInterestTypeDAO() {
		super(UserInterestType.class);
	}
}
