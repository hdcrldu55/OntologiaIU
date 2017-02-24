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
/*	@Override
	public User create(User user) throws Exception {
		User userSet = new User();
		try {
						
			String consultaCrear =  
				"PREFIX BusquedaInteres:<http://www.owl-ontologies.com/OntologyIU-lite.owl#>\n"
				+"prefix owl:<http://www.w3.org/2002/07/owl#>\n"
				+"prefix rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
				+"prefix rdfs:<http://www.w3.org/2000/01/rdf-schema#>\n"
				+"prefix swrl:<http://www.w3.org/2003/11/swrl#>\n"
				+"prefix swrlb:<http://www.w3.org/2003/11/swrlb#>\n"
				+"prefix xsd:<http://www.w3.org/2001/XMLSchema#>\n"
				+"prefix xsp:<http://www.owl-ontologies.com/2005/08/07/xsp.owl#>"
	            + "INSERT DATA"
	            + "{ "
	            + " <http://www.owl-ontologies.com/OntologyIU-lite.owl#User_"+user.getLastName()+">   a  OntologyInterest:User;"
	            + " <http://www.owl-ontologies.com/OntologyIU-lite.owl#has>    OntologyInterest:UserInterestType_"+user.getLastName()+";"
	            + " <http://www.owl-ontologies.com/OntologyIU-lite.owl#login>   OntologyInterest:Profile_"+user.getLastName()+";"
	            + " <http://www.owl-ontologies.com/OntologyIU-lite.owl#starts>   OntologyInterest:Session_"+user.getLastName()+"."
	       
	           + "<http://www.owl-ontologies.com/OntologyIU-lite.owl#Profile_"+user.getFacebookId()+">   a  OntologyInterest:Profile;"
	            + "   BusquedaInteres:lastName    \""+user.getLastName()+"\" ;"
	            + "   BusquedaInteres:firtName    \""+user.getFirstName()+"\" ;"
	            + "   BusquedaInteres:birthday    \""+user.getBirthday()+"\" ;"
	            + "   BusquedaInteres:email    \""+user.getEmail()+"\" ;"
	            + "   BusquedaInteres:facebookId   \""+user.getFacebookId()+"\";"
	            + "<http://www.owl-ontologies.com/OntologyIU-lite.owl#logged_in_by>     OntologyInterest:User_"+user.getLastName()+"."	            
	            + "}";

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
	}*/
	//---------------------------------------------------------------------------------
	@Override
	public User create(User user) throws Exception {
		User userSet = new User();
		try {
			
			String consultaCrear =  
				"PREFIX OntologyInterest: <http://www.owl-ontologies.com/OntologyIU-lite.owl#>\n"
				+"prefix owl: <http://www.w3.org/2002/07/owl#>\n"
				+"prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
				+"prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
				+"prefix swrl: <http://www.w3.org/2003/11/swrl#>\n"
				+"prefix swrlb: <http://www.w3.org/2003/11/swrlb#>\n"
				+"prefix xsd: <http://www.w3.org/2001/XMLSchema#>\n"
				+"prefix xsp: <http://www.owl-ontologies.com/2005/08/07/xsp.owl#>"
	            + "INSERT DATA"	         
	            + "{" 
	            + " <http://www.owl-ontologies.com/OntologyIU-lite.owl#User_"+user.getId()+">   a  OntologyInterest:User;"
	            + " <http://www.owl-ontologies.com/OntologyIU-lite.owl#has>    OntologyInterest:UserInterestType_"+user.getId()+";"
	            + " <http://www.owl-ontologies.com/OntologyIU-lite.owl#login>   OntologyInterest:Profile_"+user.getId()+";"
	            + " <http://www.owl-ontologies.com/OntologyIU-lite.owl#starts>   OntologyInterest:Session_"+user.getId()+"."
	            
	            +" <http://www.owl-ontologies.com/OntologyIU-lite.owl#Profile_"+user.getId()+"> a  OntologyInterest:Profile;"
	            + "   OntologyInterest:lastName    \""+user.getLastName()+"\" ;"
	            + "   OntologyInterest:firstName    \""+user.getFirstName()+"\" ;"
	            + "   OntologyInterest:birthay    \""+user.getBirthday()+"\" ;"
	            + "   OntologyInterest:email    \""+user.getEmail()+"\" ;"
	            + "   OntologyInterest:facebookId   \""+user.getFacebookId()+"\"." + "}   ";
			
			System.out.println(consultaCrear);
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

	//--------------------------------------------------------------------------------
/*	@Override
	public User create(User user) throws Exception {
		User userSet = new User();
		try {
			
			String consultaCrear =  
				"PREFIX BusquedaInteres: <http://www.owl-ontologies.com/OntologyIU-lite.owl#>\n"
				+"prefix owl: <http://www.w3.org/2002/07/owl#>\n"
				+"prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
				+"prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
				+"prefix swrl: <http://www.w3.org/2003/11/swrl#>\n"
				+"prefix swrlb: <http://www.w3.org/2003/11/swrlb#>\n"
				+"prefix xsd: <http://www.w3.org/2001/XMLSchema#>\n"
				+"prefix xsp: <http://www.owl-ontologies.com/2005/08/07/xsp.owl#>"
	            + "INSERT DATA"
	            + "{ <http://www.owl-ontologies.com/OntologyIU-lite.owl#Perfil_"+user.getFacebookId()+"> "
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
	}*/
	
	
}
