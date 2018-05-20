package io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import org.json.JSONObject;

import base.DB;

public class Send extends Thread {
	private JSONObject jo;
	private Socket socket;
	private DB db = DB.getDb();

	public Send(JSONObject jo) {
		this.jo = jo;
	}

	@Override
	public void run() {
		String ip = jo.getString("ip");
		System.out.println("即将将" + jo.toString() + "到" + ip);
		if ("-1".equals(ip)) {
			socket = db.rootSocket;
		} else {
			socket = db.get(ip);
		}
		if (socket == null) {
			return;
		}

		try {
			OutputStream os = socket.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);

			// 将数据转发出去
			oos.writeObject(jo.toString());
			oos.flush();
			System.out.println("发送成功");
		} catch (IOException e) {

		}

	}
}
