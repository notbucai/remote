package io;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import manage.Classify;

public class Server implements Runnable {
	private final int port = 9998;
	private ServerSocket ss;
	private boolean flag;

	public Server() {
		try {
			ss = new ServerSocket(port);
			flag = true;
			System.out.println("启动成功");
		} catch (IOException e) {
			// 端口被占用
		}
	}

	@Override
	public void run() {
		while (flag) {
			Socket socket = null;
			try {
				socket = ss.accept();
				// 每当新连接连接时就测试连接
				new Classify(socket).start();
				System.out.println(socket + "连接");
			} catch (IOException e) {
				e.printStackTrace();
				// IO异常，可能是连接错误
			}

		}
	}
}
