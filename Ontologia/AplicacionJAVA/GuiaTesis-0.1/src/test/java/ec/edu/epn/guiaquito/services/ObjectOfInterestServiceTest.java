package ec.edu.epn.guiaquito.services;

import ec.edu.epn.guiaquito.entities.ObjectOfInterest;
import fi.foyt.foursquare.api.entities.CompactVenue;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

/**
 * Description
 *
 * @author Xavier Ñauñay <xavierxc14@gmail.com>
 * @version 1.0
 */
public class ObjectOfInterestServiceTest {
	@Test
	public void findBest() throws Exception {
		ObjectOfInterestService objectOfInterestService = new ObjectOfInterestService();
		List<ObjectOfInterest> objectOfInterests = objectOfInterestService.findBest(null, -0.2062376, -78.4922425, Collections.<CompactVenue>emptyList());
		for (ObjectOfInterest objectOfInterest : objectOfInterests) {
			System.out.println(objectOfInterest.getName());
		}
	}

	@Test
	public void queryGoogleByInterestType() throws Exception {
		ObjectOfInterestService objectOfInterestService = new ObjectOfInterestService();
		List<ObjectOfInterest> objectOfInterests = objectOfInterestService.findBest(null, -0.2062376, -78.4922425, Collections.<CompactVenue>emptyList());
	}
}