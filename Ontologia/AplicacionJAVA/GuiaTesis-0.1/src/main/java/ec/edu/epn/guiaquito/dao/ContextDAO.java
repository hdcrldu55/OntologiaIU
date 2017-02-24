package ec.edu.epn.guiaquito.dao;

import ec.edu.epn.guiaquito.dao.base.BaseDAO;
import ec.edu.epn.guiaquito.entities.Context;
import ec.edu.epn.guiaquito.entities.Session;

import javax.ejb.Stateless;
import javax.persistence.Query;

import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;

import java.util.List;
import java.util.UUID;

@Stateless
public class ContextDAO extends BaseDAO<Context, String> {

	public ContextDAO() {
		super(Context.class);
	}
	@Override
	public Context create(Context context) throws Exception {
		Context contextSet = new Context();
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
	            +" <http://www.owl-ontologies.com/OntologyIU-lite.owl#Context_"+context.getSessionId()+"> a OntologyInterest:Context;"
	            + " <http://www.owl-ontologies.com/OntologyIU-lite.owl#established_by>    OntologyInterest:Session_"+context.getSessionId()+";"
	            + "  OntologyInterest:latitude        \""+context.getLatitude()+"\" ;"
	            + "  OntologyInterest:longitude       \""+context.getLongitude()+"\" ."
	            +"\"." + "}   ";
			
			System.out.println(consultaCrear);
			String id = UUID.randomUUID().toString();
			System.out.println(String.format("Adding %s", id));
			UpdateProcessor upp = UpdateExecutionFactory.createRemote(
					UpdateFactory.create(String.format(consultaCrear, id)),
					"http://localhost:3030/Interes/update");
			upp.execute();
			
			
			contextSet = context;
		} catch (Exception e) {

		}

		// TODO Auto-generated method stub
		return contextSet;
	
	
		}

}
