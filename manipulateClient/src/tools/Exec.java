package tools;

import java.io.IOException;

public class Exec {
	
	private static Process exec(String exec) {
		Process returnExec = null;
		try {
			returnExec = Runtime.getRuntime().exec(exec);
		} catch (IOException e) {
			// 无所谓
		}
		return returnExec;
	}
	/**
	 * 用于执行关机
	 */
	public static void off() {
		exec("shutdown /p /f");
	}
	/**
	 * 锁定
	 */
	public static void sellp() {
		exec("shutdown /h");
	}

	/**
	 * 重启
	 */
	public static void reset() {
		exec("shutdown /r /t 1");
	}
	/**
	 * 额外的接口
	 */
	public static Process xexec(String exec) {
		return exec(exec);
	}
}
