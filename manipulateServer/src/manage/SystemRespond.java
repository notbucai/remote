package manage;

import org.json.JSONObject;

import base.DB;
import base.Flag;
import base.MsgJSON;
import io.Send;

public class SystemRespond extends Thread {
	private int msg;
	private Object lists;

	public SystemRespond(int msg) {
		this.msg = msg;
	}

	@Override
	public void run() {
		int msgTempl = -1;
//		System.out.println(msg+"=======");
		switch (msg) {
		case Flag.SYSTEM_LISTS:
			// 获取被控端列表
			lists = DB.getDb().getDBSocket().keySet();
			msgTempl = 0;
			//System.out.println(DB.getDb().getDBSocket().keySet());
			break;

		default:
//			System.out.println(1122);
			return;
		}
		JSONObject jo = MsgJSON.createJSON(msgTempl, Flag.IP_ROOT, lists, null);
//		System.out.println(jo);
		new Send(jo).start();
		// new RootSend(jo.toString()).start();
	}
}
