package manipulateClient;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import org.json.JSONObject;

public class Send {
	private OutputStream os;

	public Send(OutputStream os) {
		this.os = os;
	}

	public void sendData(int msg) {
		JSONObject jo = new JSONObject();
		jo.put("msg", msg);
		jo.put("data", "");
		if (msg == 2) {
			// 获取截图
			jo.put("data", "");// 图片数据
			// 将字节转换成字符串在将字符串转为字节
		}
		try {
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject(jo.toString());
			// 将数据已字符串形式发送
			// 在服务器解压
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
