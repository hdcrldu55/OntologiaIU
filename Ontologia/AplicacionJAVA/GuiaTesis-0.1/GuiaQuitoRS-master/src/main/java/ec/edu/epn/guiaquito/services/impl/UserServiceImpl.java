package ec.edu.epn.guiaquito.services.impl;

import java.util.UUID;

import ec.edu.epn.guiaquito.entities.User;
import ec.edu.epn.guiaquito.services.UserService;
import ec.edu.epn.guiaquito.services.base.BaseServiceImpl;

import javax.ejb.Stateless;

import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;


@Stateless
public class UserServiceImpl extends BaseServiceImpl<User, Integer> implements UserService {
	
	@Override
	public User create(User user) throws Exception {
		User userSet = new User();
		try
		{
		String consultaCrear = 
	            "prefix BusquedaInteres: <http://www.owl-ontologies.com/InteresUsuario.owl#>"
	            + "INSERT DATA"
	            + "{ <http://www.owl-ontologies.com/InteresUsuario.owl#Profile_"+user.getFacebookId()+"> "
	            + "   BusquedaInteres:LastName  "+user.getLastName()+";"
	            + "   BusquedaInteres:FirstName "+user.getFirstName()+";"
	            + "   BusquedaInteres:Birthay   "+user.getBirthday()+" ;"
	            + "   BusquedaInteres:Email     "+user.getEmail()+" ;"
	            + "   BusquedaInteres:FacebookId"+user.getFacebookId()+"." + "}";
				
		  String id = UUID.randomUUID().toString();
	      System.out.println(String.format("Adding %s", id));
	      UpdateProcessor upp = UpdateExecutionFactory.createRemote(
	      UpdateFactory.create(String.format(consultaCrear, id)), "http://sesame2-interesontologia.rhcloud.com/openrdf-workbench/repositories/OntologiaId/update");
	      upp.execute();
	  	  userSet = user;		
		}
		catch (Exception e) 
		{
			
		}
	
		// TODO Auto-generated method stub
		return userSet;
	}

	@Override
	public User update(User entity) throws Exception {
		// TODO Auto-generated method stub
		return super.update(entity);
	}

	@Override
	public void delete(User entity) throws Exception {
		// TODO Auto-generated method stub
		super.delete(entity);
	}
	
}

/*
 * package ec.edu.epn.guiaquito.services.impl;

import java.util.UUID;

import ec.edu.epn.guiaquito.entities.User;
import ec.edu.epn.guiaquito.services.UserService;
import ec.edu.epn.guiaquito.services.base.BaseServiceImpl;

import javax.ejb.Stateless;

import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;


@Stateless
public class UserServiceImpl extends BaseServiceImpl<User, Integer> implements UserService {
	
	@Override
	public User create(User user) throws Exception {
		User userSet = new User();
		try
		{
		String consultaCrear = 
	            "PREFIX BusquedaInteres: <http://www.owl-ontologies.com/InteresesEPN.owl#>"
	            + "INSERT DATA"
	            + "{ <http://www.owl-ontologies.com/InteresesEPN.owl#Usuario_"+user.getFacebookId()+"> "
	            + "   BusquedaInteres:last_name    \""+user.getLastName()+"\" ;"
	            + "   BusquedaInteres:first_name    \""+user.getFirstName()+"\" ;"
	            + "   BusquedaInteres:birthday    \""+user.getBirthday()+"\" ;"
	            + "   BusquedaInteres:email    \""+user.getEmail()+"\" ;"
	            + "   BusquedaInteres:id_usuario   \""+user.getFacebookId()+"\" ;"
	            + "   BusquedaInteres:facebook_id    \""+user.getFacebookId()+"\"." + "}   ";
		
		  String id = UUID.randomUUID().toString();
	      System.out.println(String.format("Adding %s", id));
	      UpdateProcessor upp = UpdateExecutionFactory.createRemote(
	      UpdateFactory.create(String.format(consultaCrear, id)), "http://localhost:3030/InteresEPN/update");
	      upp.execute();
	  	  userSet = user;		
		}
		catch (Exception e) 
		{
			
		}
	
		// TODO Auto-generated method stub
		return userSet;
	}

	@Override
	public User update(User entity) throws Exception {
		// TODO Auto-generated method stub
		return super.update(entity);
	}

	@Override
	public void delete(User entity) throws Exception {
		// TODO Auto-generated method stub
		super.delete(entity);
	}
	
}
*/
 