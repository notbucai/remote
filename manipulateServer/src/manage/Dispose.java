package manage;

import org.json.JSONObject;

import base.Flag;
import client.ClientReceive;
import io.Send;

public class Dispose extends Thread{
	private JSONObject jo;
	private String ip = "-1";
	private int msg = -1;
	private Object lists;
	private Object img;

	public Dispose(JSONObject jo) {
		this.jo = jo;
		init();
	}

	private void init() {
		if (jo.isNull("ip") || jo.isNull("msg")) {
			return;
		}
		ip = jo.getString("ip");
		msg = jo.getInt("msg");
		if (!jo.isNull("lists")) {
			lists = jo.get("lists");
		}
		if (!jo.isNull("img")) {
			img = jo.get("img");
		}
	}
	@Override
	public void run() {
//		System.out.println(jo);
//		System.out.println(msg);
//		System.out.println(ip);
		if (Flag.IP_SYSTEM.equals(ip)) {
			// 控制端发送给系统的数据
			new SystemRespond(msg).start();
		} else if (Flag.IP_ROOT.equals(ip)) {
			// 被控端返回的数据
			new Send(jo).start();
		} else if (ip.matches("[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}")) {
			// 控制端发送给被控端的数据
			//new Send(jo).start();
			System.out.println(jo);
			new ClientReceive(jo).start();
			
		}
	}
}
