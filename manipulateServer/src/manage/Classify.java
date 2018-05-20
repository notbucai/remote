package manage;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import base.DB;
import base.Flag;
import root.RootReceive;

/**
 * 用于判断是控制端还是被控端
 * 
 * @author admin
 *
 */
public class Classify extends Thread {
	private Socket socket;
	private DB db;
	private String ip;
	private boolean flag;

	public Classify(Socket socket) {
		this.socket = socket;
		try {
			socket.setSoTimeout(100);
		} catch (SocketException e) {
			System.out.println("sdfsaadf失败");
		}
	}

	@Override
	public void run() {
		ip = socket.getInetAddress().getHostAddress();
		db = DB.getDb();
		InputStream is = null;
		try {
			is = socket.getInputStream();
			byte[] bs = new byte[1024];
			int len = is.read(bs);
			if (len != 1) {
				return;
			}
			analyze(bs[0]);
		}catch (SocketTimeoutException e) {
			e.printStackTrace();
			return;
		} catch (IOException e) {
			e.printStackTrace();
			DB.getDb().remove(socket.getInetAddress().getHostAddress());
		}
	}

	/**
	 * 用于分发连接
	 * 
	 * @param b 分析的头信息
	 */
	private void analyze(byte b) {

		if (b == Flag.IFY_ROOT) {
			System.out.println("系统用户连接成功");
			db.rootSocket = socket;
			new RootReceive().start();
			// 实时监听控制端的消息
		} else if (b == Flag.IFY_CLIENT) {
			// 不需要监听被控端
			// 只有给被控端发送数据时才需要得到响应

			System.out.println("被控端连接成功");
			db.add(ip, socket);
		}
	}
}
