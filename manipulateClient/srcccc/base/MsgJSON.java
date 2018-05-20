package base;

import org.json.JSONException;
import org.json.JSONObject;

public class MsgJSON {

    public static JSONObject createJSON(int msg, String ip, Object lists, byte[] bs) {

        JSONObject jo = new JSONObject();
        try {
            jo.put("msg", msg);
            jo.put("ip", ip);
            jo.put("lists", lists);
            if (bs != null) {
                jo.put("img", new String(bs, 0, bs.length));
            }
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
