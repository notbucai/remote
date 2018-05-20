package base;

import org.json.JSONException;
import org.json.JSONObject;

public class MsgJSON {

	public static JSONObject createJSON(int msg, String ip, Object lists, String img) {

		JSONObject jo = new JSONObject();
		try {
			jo.put("msg", msg);
			jo.put("ip", ip);
			jo.put("lists", lists);

			jo.put("img", img);

		} catch (JSONException e) {
			jo = null;
		}

		return jo;
	}

	public static JSONObject parseString(String jsonStr) {
		JSONObject jo = null;
		try {
			jo = new JSONObject(jsonStr);
		} catch (Exception e) {
			jo = null;
		}

		return jo;
	}
}
