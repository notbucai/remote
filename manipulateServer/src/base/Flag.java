package base;

public class Flag {
	// 表示接入的是控制端
	public final static byte IFY_ROOT = 2;
	// 表示接入的是被控端
	public final static byte IFY_CLIENT = 1;

	// 表示给系统发送的数据
	public final static String IP_SYSTEM = "0";
	// 表示给控制端发送的数据
	public final static String IP_ROOT = "-1";

	// 表示获取系统当前连接的客户端列表
	public final static int SYSTEM_LISTS = 0;
	
	// 无当前客户端
	public final static int MSG_CLIENT_NULL = -1;
}
