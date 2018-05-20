package manipulateClient;

import org.json.JSONObject;

import base.Flag;
import base.MsgJSON;
import tools.Exec;
import tools.Tools;

public class Dispose {
	private JSONObject jo;

	public Dispose(JSONObject jo) {
		this.jo = jo;

	}

	public JSONObject execute() {
		int msg = -1;
		System.out.println(111);
		/**
		 * msg =1为执行命令 msg = 2 为截图
		 */
		int exeMsg = -1;
		String img = null;
		if (!jo.isNull("msg")) {
			exeMsg = jo.getInt("msg");
		}
		switch (exeMsg) {
		case Flag.CLIENT_OFF:
			// 关机
			msg = Flag.WHAT_YES;
			Exec.off();
			System.out.println("关机");
			break;
		case Flag.CLIENT_RESET:
			// 重启
			msg = Flag.WHAT_YES;
			Exec.reset();
			System.out.println("重启");
			break;
		case Flag.CLIENT_SELLP:
			// 休眠
			msg = Flag.WHAT_YES;
			Exec.sellp();
			System.out.println("休眠");
			break;
		case Flag.CLIENT_PRTSC:
			// 截图
			msg = Flag.WHAT_YES;
			img = Tools.getPrtSc();
			System.out.println("截图");
			break;
		default:
			msg = Flag.WHAT_NO;
			break;
		}
		JSONObject json = MsgJSON.createJSON(msg, Flag.IP_ROOT, null, img);

		return json;
	}
}
