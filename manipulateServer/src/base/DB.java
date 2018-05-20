package base;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

public class DB {
	private HashMap<String, Socket> DBSocket;
	private static DB db;
	public Socket rootSocket;

	public static DB getDb() {
		if (db == null) {
			synchronized (DB.class) {
				if (db == null) {
					db = new DB();
				}
			}
		}
		return db;
	}

	// 保证线程安全
	private DB() {
		DBSocket = new HashMap<>();
	}

	public HashMap<String, Socket> getDBSocket() {
		return DBSocket;
	}

	public synchronized void add(String ip, Socket s) {
		DBSocket.put(ip, s);
	}

	public synchronized void remove(String ip) {
		try {
			get(ip).close();
		} catch (IOException e) {
			// 忽略
		}
		DBSocket.remove(ip);
	}

	public synchronized Socket get(String ip) {
		return DBSocket.get(ip);
	}

	public synchronized boolean containsKey(String ip) {
		return DBSocket.containsKey(ip);
	}

	public int size() {
		return DBSocket.size();

	}

	public void nullRoot() {
		rootSocket = null;
	}

	@Override
	public String toString() {
		return DBSocket.toString();
	}
}
