import org.junit.AfterClass;
import org.junit.BeforeClass;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by xavier on 11/04/16.
 */
public class MongoTest {

	private static EntityManagerFactory entityManagerFactory;

	@BeforeClass
	public static void setUpEntityManagerFactory() {
		entityManagerFactory = Persistence.createEntityManagerFactory("GuiaQuitoPU");
	}

	@AfterClass
	public static void closeEntityManagerFactory() {
		entityManagerFactory.close();
	}


}