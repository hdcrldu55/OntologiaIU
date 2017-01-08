package ec.edu.epn.guiaquito.services;

import org.junit.Test;

/**
 * Description.
 *
 * @author Xavier Ñauñay <xavierxc14@gmail.com>
 * @version 1.0
 */
public class ConfigurationServiceTest {
	@Test
	public void readProperties() {
		ConfigurationService configurationService = new ConfigurationService();
		configurationService.readProperties();
		System.out.println(ConfigurationService.ACCESS_KEY);
	}

}