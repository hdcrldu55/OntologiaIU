package ec.edu.epn.guiaquito.services;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 * Description.
 *
 * @author Xavier Ñauñay <xavierxc14@gmail.com>
 * @version 1.0
 */
@Startup
@Singleton
public class StartupService {

	@PostConstruct
	public void init() {
	}
}
