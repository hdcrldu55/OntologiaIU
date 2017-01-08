package ec.edu.epn.guiaquito.services.rs.base;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

public abstract class BaseFacade<T, ID extends String> {
	public abstract Response find();

	public abstract Response findById(@PathParam("id") ID id);

	public abstract Response create(T t);

	public abstract Response update(@PathParam("id") ID id, T t);
}
