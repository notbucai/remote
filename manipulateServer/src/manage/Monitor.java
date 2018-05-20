package manage;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;

import base.DB;

/**
 * 用于后台线程判断是否在线
 * 
 * @author admin
 *
 */
public class Monitor {

	public static void test() {
		HashMap<String, Socket> dbSocket = DB.getDb().getDBSocket();
		Socket[] values = dbSocket.values().toArray(new Socket[0]);
		for (Socket socket : values) {
			try {
				OutputStream outputStream = socket.getOutputStream();
				outputStream.write(0);
			} catch (IOException e) {
				e.printStackTrace();
				DB.getDb().remove(socket.getInetAddress().getHostAddress());
			}
		}
		System.out.println(DB.getDb());
	}
}
