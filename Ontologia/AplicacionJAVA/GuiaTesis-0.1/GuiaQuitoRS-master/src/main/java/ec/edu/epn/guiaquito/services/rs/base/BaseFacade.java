package ec.edu.epn.guiaquito.services.rs.base;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.io.Serializable;

public abstract class BaseFacade<T, ID extends Serializable> {
    public abstract Response find();

    public abstract Response findById(@PathParam("id") Integer id);

    public abstract Response create(T t);

    public abstract Response update(@PathParam("id") Integer id, T t);
}
