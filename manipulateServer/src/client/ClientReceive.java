package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;

import org.json.JSONObject;

import base.DB;
import base.Flag;
import base.MsgJSON;
import io.Send;
import manage.Dispose;
import manage.Monitor;

public class ClientReceive extends Thread {
	private JSONObject jo;
	private Socket socket;
	private DB db;

	public ClientReceive(JSONObject jo) {
		this.jo = jo;
		db = DB.getDb();
	}

	@Override
	public void run() {
		//Monitor.test();
		System.out.println("客户端开启成功");
		String ip = jo.getString("ip");
		this.socket = db.get(ip);

		if (this.socket == null) {
			JSONObject parseString = MsgJSON.createJSON(Flag.MSG_CLIENT_NULL, Flag.IP_ROOT, null, null);
			new Dispose(parseString).start();
			return;
		}
		new Send(jo).start();
		try {
			socket.setSoTimeout(4000);
			// 超时机制
		} catch (SocketException e1) {
			e1.printStackTrace();
			return;
		}
		try {
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			Object readObject = ois.readObject();
			if (readObject instanceof String) {
				JSONObject parseString = MsgJSON.parseString(readObject.toString());
				new Dispose(parseString).start();
				System.out.println(parseString+ "   ====");
			}
		} catch (IOException e) {
			e.printStackTrace();
			// db.remove(ip);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			// 忽略
		}
		System.out.println("客户端结束");

	}

}
