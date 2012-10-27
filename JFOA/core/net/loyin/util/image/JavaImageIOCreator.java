package net.loyin.util.image;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import net.loyin.util.exception.ImageRuntimeException;
import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 使用javax image io生成缩略图
 * @author kingapex
 * 2010-7-10下午11:43:05
 */
public class JavaImageIOCreator{

	private String srcFile;

	private String destFile;

	private int width;

	private int height;

	private Image img;

	public JavaImageIOCreator(String sourcefile, String targetFile) {
		File _file = new File(sourcefile); // 读入文件
		this.srcFile = _file.getName();
		this.destFile = targetFile;
		try {
			img = javax.imageio.ImageIO.read(_file);
		} catch (IOException e) {
			e.printStackTrace();
		} // 构造Image对象
		width = img.getWidth(null); // 得到源图宽
		height = img.getHeight(null); // 得到源图长
	}

	public void resize(int w, int h) {
		

		int target_w, target_h; // 目标宽高
		int x = 0, y = 0; // 缩略图在背景的座标
		x = y = 0;
		target_w = w;
		target_h = h;

		/* 计算目标宽高 */
		if (width / height > w / h) { // 原图长:上下补白
			target_w = w;
			target_h = (int) (target_w * height / width);
			x = 0;
			y = (int) (h - target_h) / 2;

		}

		if (width / height < w / h) { // 原图高:左右补白
			target_h = h;
			target_w = (int) (target_h * width / height) ;
			y = 0;
			x = (int) (w - target_w) / 2;
		
		}

		
		BufferedImage _image = new BufferedImage(w, h,
				BufferedImage.TYPE_INT_RGB);
		Graphics graphics = _image.getGraphics();
		graphics.setColor(Color.white);
		graphics.fillRect(0, 0, _image.getWidth(), _image.getHeight());
		graphics.drawImage(img, x, y,target_w, target_h, null); // 绘制缩小后的图
		FileOutputStream out;
		try {
			out = new FileOutputStream(destFile);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			encoder.encode(_image);
			out.close();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			throw new ImageRuntimeException(srcFile, "生成缩略图");
		} // 输出到文件流
		catch (ImageFormatException e) {
			e.printStackTrace();
			throw new ImageRuntimeException(srcFile, "生成缩略图");
		} catch (IOException e) {
			e.printStackTrace();
			throw new ImageRuntimeException(srcFile, "生成缩略图");
		}

	}

	public static void main(String args[]){
		JavaImageIOCreator creator = new JavaImageIOCreator("E:/1.jpg", "E:/2.jpg");
		creator.resize(200, 200);
	}
}
