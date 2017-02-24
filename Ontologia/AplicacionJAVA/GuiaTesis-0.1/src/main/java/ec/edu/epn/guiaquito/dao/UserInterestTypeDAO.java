package ec.edu.epn.guiaquito.dao;

import java.util.UUID;

import ec.edu.epn.guiaquito.dao.base.BaseDAO;
import ec.edu.epn.guiaquito.entities.InterestType;
import ec.edu.epn.guiaquito.entities.UserInterestType;

import javax.ejb.Stateless;

import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;

@Stateless
public class UserInterestTypeDAO extends BaseDAO<UserInterestType, String> {

	public UserInterestTypeDAO() {
		super(UserInterestType.class);
	}
	
	@Override
	public UserInterestType create(UserInterestType userinteresttype) throws Exception {
		UserInterestType userinteresttypeSet = new UserInterestType();
		//User userSet = new User();
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
	            + " <http://www.owl-ontologies.com/OntologyIU-lite.owl#UserInterestType_"+userinteresttype.getUserId()+"> a  OntologyInterest:UserInterestType;"
	            + " <http://www.owl-ontologies.com/OntologyIU-lite.owl#belongs_to>     OntologyInterest:User_"+userinteresttype.getUserId()+";"
	            + " <http://www.owl-ontologies.com/OntologyIU-lite.owl#prioritizes>    OntologyInterest:InterestType_"+userinteresttype.getUserId()+"."
	            +"\"." + "}   ";
			
			System.out.println(consultaCrear);
			String id = UUID.randomUUID().toString();
			System.out.println(String.format("Adding %s", id));
			UpdateProcessor upp = UpdateExecutionFactory.createRemote(
					UpdateFactory.create(String.format(consultaCrear, id)),
					"http://localhost:3030/Interes/update");
			upp.execute();
			
			
			userinteresttypeSet = userinteresttype;
		} catch (Exception e) {

		}

		// TODO Auto-generated method stub
		return userinteresttypeSet;
	
	
		}
}
