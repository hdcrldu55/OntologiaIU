package ec.edu.epn.guiaquito.dao;

import java.util.UUID;

import ec.edu.epn.guiaquito.dao.base.BaseDAO;
import ec.edu.epn.guiaquito.entities.Session;
import ec.edu.epn.guiaquito.entities.User;

import javax.ejb.Stateless;
import javax.persistence.Query;

import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;

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
	
	@Override
	public Session create(Session session) throws Exception {
		Session sessionSet = new Session();
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
	            + " <http://www.owl-ontologies.com/OntologyIU-lite.owl#Session_"+session.getUserId()+"> a OntologyInterest:Session;"
	            + " <http://www.owl-ontologies.com/OntologyIU-lite.owl#establish>     OntologyInterest:Context_"+session.getUserId()+";"
	            + " <http://www.owl-ontologies.com/OntologyIU-lite.owl#started_by>     OntologyInterest:User_"+session.getUserId()+"."
	            
	            + "}   ";
			
			System.out.println(consultaCrear);
			String id = UUID.randomUUID().toString();
			System.out.println(String.format("Adding %s", id));
			UpdateProcessor upp = UpdateExecutionFactory.createRemote(
					UpdateFactory.create(String.format(consultaCrear, id)),
					"http://localhost:3030/Interes/update");
			upp.execute();
			
			
			sessionSet = session;
		} catch (Exception e) {

		}

		// TODO Auto-generated method stub
		return sessionSet;
	
	
		}

}
