package manipulateClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.json.JSONObject;

import base.MsgJSON;

public class Client extends Thread {

	private static Client client;
	private Socket socket;
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
				System.out.println("等待接受");
				InputStream inputStream = socket.getInputStream();
				byte[] bs = new byte[1111111];
				int len=0;
				while((len=inputStream.read(bs ))!=-1) {
					for(int i=0;i<len;i++) {
						System.out.println(bs[i]);
					}
				}
				BufferedReader ois = new BufferedReader(new InputStreamReader(inputStream));
				System.out.println(ois.readLine());
				
//				 System.out.println(jsonStr.toString());
//				if (jsonStr instanceof String) {
//
//					JSONObject jo = MsgJSON.parseString(jsonStr.toString());
//					System.out.println("接收到" + jsonStr.toString());
//					// System.out.println(jo);
//
//				}
			} catch (IOException e) {
				// 流出错 重新获取端口 
				e.printStackTrace();
				getSocket();
				continue;
			} 
			
			
			
		}
	}

	private void getSocket() {
		while (true) {
			try {
				socket = new Socket("192.168.0.102", 9998);
//				System.out.println(11);
				// 发送认证包
				socket.getOutputStream().write(new byte[] { 1 });
				
				break;
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
		}
	}

}
