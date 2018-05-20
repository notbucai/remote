package base;

public class Flag {
    // 表示接入的是控制端
    public final static byte IFY_ROOT = 0;
    // 表示接入的是被控端
    public final static byte IFY_CLIENT = 1;

    // 表示给系统发送的数据
    public final static String IP_SYSTEM = "0";
    // 表示给控制端发送的数据
    public final static String IP_ROOT = "-1";

    // 表示获取系统当前连接的客户端列表
    public final static int SYSTEM_LISTS = 0;

    // 表示收到的是正确的消息
    public final static int WHAT_YES = 0;
    // 表示收到的是失败的消息
    public final static int WHAT_NO = 1;


    //关机
    public final static int CLIENT_OFF = 0;
    //重启
    public final static int CLIENT_RESET = 1;
    //休眠
    public final static int CLIENT_SELLP = 2;
    //截屏
    public final static int CLIENT_PRTSC = 3;
}
