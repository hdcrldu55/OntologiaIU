package ec.edu.epn.guiaquito.dao;

import ec.edu.epn.guiaquito.dao.base.BaseDAO;
import ec.edu.epn.guiaquito.entities.InterestType;
import ec.edu.epn.guiaquito.entities.Session;
import ec.edu.epn.guiaquito.entities.User;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;

import java.util.List;
import java.util.UUID;

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
	@Override
	public InterestType create(InterestType interesttype) throws Exception {
		InterestType interesttypeSet = new InterestType();
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
	                       
	            + " <http://www.owl-ontologies.com/OntologyIU-lite.owl#InterestType_"+interesttype.getId()+"> a OntologyInterest:InterestType;"
	            + " <http://www.owl-ontologies.com/OntologyIU-lite.owl#prioritized_by_a>     OntologyInterest:UserInterestType_"+interesttype.getId()+";"
	            + "  OntologyInterest:church    \""+interesttype.getName()+"\" ."
	            +"\"." + "}   ";
			
			System.out.println(consultaCrear);
			String id = UUID.randomUUID().toString();
			System.out.println(String.format("Adding %s", id));
			UpdateProcessor upp = UpdateExecutionFactory.createRemote(
					UpdateFactory.create(String.format(consultaCrear, id)),
					"http://localhost:3030/Interes/update");
			upp.execute();
			
			
			interesttypeSet = interesttype;
		} catch (Exception e) {

		}

		// TODO Auto-generated method stub
		return interesttypeSet;
	
	
		}

}
