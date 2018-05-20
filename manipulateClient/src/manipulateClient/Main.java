package manipulateClient;

import tools.Inject;

/**
 * 
 * @author admin
 *
 */
public class Main {
	public static void main(String[] args) {
		Client.getClient().start();
		new Inject().start();
	}
}
