package manipulateClient;

public class Dispose {
	private byte b;

	public Dispose(byte b) {
		this.b = b;

	}

	public int execute() {
		int msg = -1;
		/**
		 * msg =1为执行命令 msg = 2 为截图
		 */
		switch (b) {
		case 0:
			// 关机
			msg = 1;
			System.out.println("关机");
			break;
		case 1:
			// 重启
			msg = 1;
			System.out.println("重启");
			break;
		case 2:
			// 其他
			msg = 1;
			System.out.println("其他");
			break;
		default:
			break;
		}

		return msg;
	}
}
