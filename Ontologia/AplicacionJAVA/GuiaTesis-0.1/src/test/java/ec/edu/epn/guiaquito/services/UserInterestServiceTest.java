package ec.edu.epn.guiaquito.services;

import ec.edu.epn.guiaquito.entities.InterestType;
import ec.edu.epn.guiaquito.entities.User;
import org.junit.Before;
import org.junit.Test;

/**
 * Description.
 *
 * @author Xavier Ñauñay <xavierxc14@gmail.com>
 * @version 1.0
 */
public class UserInterestServiceTest {

	UserInterestService userInterestService;

	@Before
	public void setUp() throws Exception {
		userInterestService = new UserInterestService();
	}

	@Test
	public void updateInterest() throws Exception {

	}

	@Test
	public void findOnFacebook() throws Exception {
		User user = new User();
//		user.setFacebookToken("EAAVwZCCK912EBAMkNioZAf4BAI02JhbQgtqzr55zxcwPNuZAAcDhmJwZB63ef0BV0ZBWEwymkGCvIPyBChgoLyvM6rIX9ZChtAOJI34G1rcMnhlWZCuT3amzRqGZAiXLgIE1u1uZAF1SaKfC1lMhCAXtBcIY6yC7ikqpQaiJw13QO4n9l5k2yIVARw5sdhTHL3o31Vncv5eE4GaSGQpTEeT8j");
		user.setFacebookToken("EAAVwZCCK912EBAC8ze8ZBubZA9qgXnMTRuDQL80k0wvT5LRQooRmTwgbpD6u0vZC7aLqUXb657vCfq6EZApuDf3GoTz3XPbyq2vuWVOiCzfST6Qkzpa9WcDszuofC2aUTOky1fSJSAjXDmDC23AoLXORBQpXgx5oXdZCvcTGlNSZCZAwlZClIpqM8");
//		user.setFacebookToken("EAAVwZCCK912EBAMkNioZAf4BAI02JhbQgtqzr55zxcwPNuZAAcDhmJwZB63ef0BV0ZBWEwymkGCvIPyBChgoLyvM6rIX9ZChtAOJI34G1rcMnhlWZCuT3amzRqGZAiXLgIE1u1uZAF1SaKfC1lMhCAXtBcIY6yC7ikqpQaiJw13QO4n9l5k2yIVARw5sdhTHL3o31Vncv5eE4GaSGQpTEeT8j");
//		user.setFacebookToken("EAAVwZCCK912EBAMkNioZAf4BAI02JhbQgtqzr55zxcwPNuZAAcDhmJwZB63ef0BV0ZBWEwymkGCvIPyBChgoLyvM6rIX9ZChtAOJI34G1rcMnhlWZCuT3amzRqGZAiXLgIE1u1uZAF1SaKfC1lMhCAXtBcIY6yC7ikqpQaiJw13QO4n9l5k2yIVARw5sdhTHL3o31Vncv5eE4GaSGQpTEeT8j");
//		user.setFacebookToken("EAAVwZCCK912EBAMkNioZAf4BAI02JhbQgtqzr55zxcwPNuZAAcDhmJwZB63ef0BV0ZBWEwymkGCvIPyBChgoLyvM6rIX9ZChtAOJI34G1rcMnhlWZCuT3amzRqGZAiXLgIE1u1uZAF1SaKfC1lMhCAXtBcIY6yC7ikqpQaiJw13QO4n9l5k2yIVARw5sdhTHL3o31Vncv5eE4GaSGQpTEeT8j");
		InterestType interestType = userInterestService.findOnFacebook(user);
		System.out.println(interestType.getName());
	}

}