package ec.edu.epn.guiaquito.dao;

import ec.edu.epn.guiaquito.dao.base.BaseDAO;
import ec.edu.epn.guiaquito.entities.Interaction;

import javax.ejb.Stateless;

@Stateless
public class InteractionDAO extends BaseDAO<Interaction, String> {

	public InteractionDAO() {
		super(Interaction.class);
	}
}
