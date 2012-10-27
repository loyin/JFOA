package net.loyin.util.image;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import sun.misc.BASE64Encoder;

public class Image2Base64str {
	public static String GetImageStr(File img) {
		byte[] data = null;

		// 读取图片字节数组
		try {
			InputStream in = new FileInputStream(img);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);// 返回Base64编码过的字节数组字符串
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(Image2Base64str.GetImageStr(new File("E:/laba.png")));
	}

}
