package ec.edu.epn.guiaquito.dao;

import java.util.UUID;

import ec.edu.epn.guiaquito.dao.base.BaseDAO;
import ec.edu.epn.guiaquito.entities.User;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;


@Stateless
public class UserDAO extends BaseDAO<User, String> {
	public UserDAO() {
		super(User.class);
	}

	public User findByFacebookId(Long facebookId) {
		TypedQuery<User> q = getEntityManager().createQuery(
				SELECT_STATEMENT + "User e WHERE e.facebookId = :facebookId",
				User.class);
		q.setParameter("facebookId", facebookId);
		return q.getResultList().get(0);
	}
	
	@Override
	public User create(User user) throws Exception {
		User userSet = new User();
		try {
			/*String consultaCrear = "prefix BusquedaInteres: <http://www.owl-ontologies.com/OntologiaIU-lite.owl#>"
					+ "INSERT DATA"
					+ "{ <http://www.owl-ontologies.com/OntologiaIU-lite.owl#Profile_"+ user.getFacebookId()+ "> "
					+ "   BusquedaInteres:LastName  "+ user.getLastName()+ ";"
					+ "   BusquedaInteres:FirstName "+ user.getFirstName()+ ";"
					+ "   BusquedaInteres:Birthay   "+ user.getBirthday()+ " ;"
					+ "   BusquedaInteres:Email     "+ user.getEmail()+ " ;"
					+ "   BusquedaInteres:FacebookId"+ user.getFacebookId() + "." + "}";*/
			
			String consultaCrear =  "PREFIX BusquedaInteres: <http://www.owl-ontologies.com/OntologiaIU-lite.owl#>"
	            + "INSERT DATA"
	            + "{ <http://www.owl-ontologies.com/OntologiaIU-lite.owl#Perfil_"+user.getFacebookId()+"> "
	            + "   BusquedaInteres:LastName    \""+user.getLastName()+"\" ;"
	            + "   BusquedaInteres:FirstName    \""+user.getFirstName()+"\" ;"
	            + "   BusquedaInteres:Birthay    \""+user.getBirthday()+"\" ;"
	            + "   BusquedaInteres:email    \""+user.getEmail()+"\" ;"
	            + "   BusquedaInteres:FacebookId   \""+user.getFacebookId()+"\"." + "}   ";

			String id = UUID.randomUUID().toString();
			System.out.println(String.format("Adding %s", id));
			UpdateProcessor upp = UpdateExecutionFactory.createRemote(
					UpdateFactory.create(String.format(consultaCrear, id)),
					"http://localhost:3030/Interes/update");
			upp.execute();
			userSet = user;
		} catch (Exception e) {

		}

		// TODO Auto-generated method stub
		return userSet;
	}
	
}
