package main;

import io.Server;

public class Main {
	public static void main(String[] args) {
		Server s = new Server();
		new Thread(s).start();
		/*@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		while(true) {
			String nextLine = sc.nextLine();
			if ("1".equals(nextLine)) {
				new Monitor().start();
			} else if ("2".equals(nextLine)) {
				System.out.println(DB.getDb().rootSocket);
				
			}
		}*/
	}
}
