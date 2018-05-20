package manipulateClient;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.json.JSONObject;

import base.RootSocket;

public class Send extends Thread{
	private Socket socket;
	private JSONObject jo;

	public Send(JSONObject jo) {
		this.socket = RootSocket.socket;
		this.jo = jo;
	}
	
	
	@Override
	public void run() {
		
		try {
			
			OutputStream outputStream = socket.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(outputStream);
			
			oos.writeObject(jo.toString());
			oos.flush();
			System.out.println(jo);
		} catch (IOException e) {

		}
		
	}
}
