package tools;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import sun.misc.BASE64Encoder;

public class Tools {
	public static String getPrtSc() {
		String data = null;
		Robot robot = null;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		try {
			robot = new Robot();

		} catch (AWTException e) {
			e.printStackTrace();

		}
		if (robot != null) {
			BufferedImage bufferedImage = robot.createScreenCapture(
					new Rectangle(0, 0, (int) screenSize.getWidth(), (int) screenSize.getHeight()));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			try {
				ImageIO.write(bufferedImage, "gif", baos);
				byte[] byteArray = baos.toByteArray();
				BASE64Encoder encoder = new BASE64Encoder();
				data = encoder.encode(byteArray);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return data;
	}

	/**
	 * 负责写入文件
	 * 
	 * @param formerFile
	 * @param newFile
	 * @return
	 */
	public static boolean copyFile(String formerFile, String newFile) {
		boolean flag = true;
		FileInputStream fileInputStream = null;
		FileOutputStream fileOutputStream = null;
		try {
			fileInputStream = new FileInputStream(formerFile);
			fileOutputStream = new FileOutputStream(newFile);
			int len = 0;
			byte[] bs = new byte[1024];
			while ((len = fileInputStream.read(bs)) != -1) {
				fileOutputStream.write(bs, 0, len);
			}

		} catch (FileNotFoundException e) {

			flag = false;
		} catch (IOException e) {

			flag = false;
		} finally {
			if (fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (IOException e) {

					flag = false;

				}
			}
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {

					flag = false;

				}
			}
		}

		return flag;

	}

	public static boolean isExist(String testFile) {
		File file = new File(testFile);
		return file.isFile();
	}

	public static boolean isDirectory(String testFile) {
		File file = new File(testFile);
		return file.isDirectory();
	}

	public static Process hide(String file) {
		return Exec.xexec("attrib +H \"" + file + "\"");
	}
}
