import ec.edu.epn.guiaquito.services.ObjectOfInterestService;

/**
 * Created by xavier on 17/03/16.
 */
public class TestWikipedia {
	public static void main(String[] args) {
		ObjectOfInterestService objectOfInterestService = new ObjectOfInterestService();
		objectOfInterestService.getDescriptionFromWikipedia(-0.22083333, -78.51388889);
	}
}
