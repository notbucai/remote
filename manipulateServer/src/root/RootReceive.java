package root;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;

import org.json.JSONObject;

import base.DB;
import base.MsgJSON;
import manage.Dispose;

public class RootReceive extends Thread {
	private Socket socket;
	private DB db;

	public RootReceive() {
		this.db = DB.getDb();
		this.socket = db.rootSocket;
		try {
			socket.setSoTimeout(0);
			// 无限时间
		} catch (SocketException e) {

		}
	}

	@Override
	public void run() {

		try {

			while (true) {
				System.out.println("等待接受");
				InputStream inputStream = socket.getInputStream();
				
				ObjectInputStream ois = new ObjectInputStream(inputStream);
				Object jsonStr = ois.readObject();
				// System.out.println(jsonStr.toString());
				if (jsonStr instanceof String) {

					JSONObject jo = MsgJSON.parseString(jsonStr.toString());
					System.out.println("接收到" + jsonStr.toString());
					if (jo == null) {
						continue;
					}
					// System.out.println(jo);
					new Dispose(jo).start();

				}

			}
		} catch (IOException e) {
			e.printStackTrace();
			db.rootSocket = null;
			return;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			// 忽略
		}
	}
}
