package net.loyin.util.image;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 * 图片合并
 */
public class AddWatermark
{
	/**
	  * 获得原始图片的Image对象
	  */
	private BufferedImage OriginalImage=null;
	private String Original;
	private String Append;
	private int OriginalWidth;//原始图片宽
	private int OriginalHeigth;//原始图片的高
	private int AppendWidth;//添加图片的宽
	private int AppendHeigth;//添加图片的高
	private int X;//添加图片的位X位置
	private int Y;//添加图片的位Y位置
	private String saveas;//保存路径
	/**
	 * @param saveas the saveas to set
	 */
	public void setSaveas(String saveas) {
		this.saveas = saveas;
	}

	private BufferedImage AppendImage=null;
	/**
	 * 坐标
	 * 上左(默认)
	 * <pre>
	 *    # * *
	 *    * * *
	 *    * * *
	 * </pre>
	 */
	public static final int COORDINATE_UP_LEFT=1;
	/**
	 * 坐标
	 * 上中
	 * <pre>
	 *    * # *
	 *    * * *
	 *    * * *
	 * </pre>
	 */
	public static final int COORDINATE_UP_MIDDLE=2;
	/**
	 * 坐标
	 * 中左
	 * <pre>
	 *    * * *
	 *    # * *
	 *    * * *
	 * </pre>
	 */
	public static final int COORDINATE_UP_RIGHT=3;
	/**
	 * 坐标
	 * 上右
	 * <pre>
	 *    * * #
	 *    * * *
	 *    * * *
	 * </pre>
	 */
	public static final int COORDINATE_MIDDLE_LEFT=4;
	/**
	 * 坐标
	 * X居中Y居中
	 * <pre>
	 *    * * *
	 *    * # *
	 *    * * *
	 * </pre>
	 */
	public static final int COORDINATE_MIDDLE_MIDDLE=5;
	/**
	 * 坐标
	 * 中右
	 * <pre>
	 *    * * *
	 *    * * #
	 *    * * *
	 * </pre>
	 */
	public static final int COORDINATE_MIDDLE_RIGHT=6;
	/**
	 * 坐标
	 * 下左
	 * <pre>
	 *    * * *
	 *    * * *
	 *    # * *
	 * </pre>
	 */
	public static final int COORDINATE_DOWN_LEFT=7;
	/**
	 * 坐标
	 * 下中
	 * <pre>
	 *    * * *
	 *    * * *
	 *    * # *
	 * </pre>
	 */
	public static final int COORDINATE_DOWN_MIDDLE=8;
	/**
	 * 坐标
	 * 下右
	 * <pre>
	 *    * * *
	 *    * * *
	 *    * * #
	 * </pre>
	 */
	public static final int COORDINATE_DOWN_RIGHT=9;
	public AddWatermark(){}
	/**
	 * @param originalWidth the originalWidth to set
	 */
	protected void setOriginalWidth(int originalWidth) {
		OriginalWidth = originalWidth;
	}

	/**
	 * @param appendWidth the appendWidth to set
	 */
	protected void setAppendWidth(int appendWidth) {
		AppendWidth = appendWidth;
	}

	/**
	 * @param appendHeigth the appendHeigth to set
	 */
	protected void setAppendHeigth(int appendHeigth) {
		AppendHeigth = appendHeigth;
	}
	 public AddWatermark(String Original, String Append)
	 {
		 this.Original=Original;
		 this.Append=Append;
	 }
	 /**
		 * @param originalImage the originalImage to set
		 */
		protected void setOriginalImage(BufferedImage originalImage) {
			
			OriginalImage = originalImage;
		}
		/**
		 * @param originalHeigth the originalHeigth to set
		 */
		protected void setOriginalHeigth(int originalHeigth) {
			OriginalHeigth = originalHeigth;
		}
		/**
		 * @param appendImage the appendImage to set
		 */
		protected void setAppendImage(BufferedImage appendImage) {
			AppendImage = appendImage;
			
		}
	 
	 
	 	/**原始图片的高
		 * @return the originalHeigth
		 */
		protected int getOriginalHeigth() {
			return OriginalHeigth;
		}
	 
		/**添加图片的宽
		 * @return the appendWidth
		 */
		protected int getAppendWidth() {
			return AppendWidth;
		}
		/**添加图片的高
		 * @return the appendHeigth
		 */
		protected int getAppendHeigth() {
			return AppendHeigth;
		}
		
	 /**
		 * 原始图片的宽
		 * @return
		 */
		protected int getOriginalWidth() 
		{
			return OriginalWidth;
		}
	 
	 /**
	  * 获得添加图片的信息
	  * @return
	  */
	 protected BufferedImage getAppendImage()
	 {
		 return this.AppendImage;
	 }
	 
	 
	 
	 /**
	  * 获得原始图片的Image对象
	  * @return
	  */
	 public BufferedImage getOriginalImage()
	 {
		return this.OriginalImage;
	 }
	/**
	 * 获得原始图片的地址
	 * @return
	 */
	public String getOriginal() {
		return Original;
	}
/**
 * 设置原始图片的路径
 * @param originalImage
 */
	public void setOriginalImage(String originalImage) {
		Original = originalImage;
	}
/**
 * 获得追加的图片路径
 * @return
 */
	public String getAppend() {
		return Append;
	}
/**
 * 设置追加的图片路径
 * @param appendImage
 */
	public void setAppendImage(String appendImage) 
	{
		Append = appendImage;
	}
	/**
	 *获得原始图片信息
	 * @return 成功返回true 否则返回false 
	 */
	protected  void readOriginalImage() throws Exception
	{
		
			if(this.OriginalImage==null)
			this.OriginalImage=ImageIO.read(new File(this.Original));
			if(OriginalImage!=null)
			{
				this.OriginalWidth=this.OriginalImage.getWidth();
				this.OriginalHeigth=this.OriginalImage.getHeight();
			}
	}
	/**
	 * 获得添加图片的信息
	 * @return 成功返回true 否则返回false 
	 */
	protected boolean readAppendImage()
	{
		boolean succeedread=false;
		try 
		{
			if(this.AppendImage==null)
			this.AppendImage=ImageIO.read(new File(this.Append));
			if(AppendImage!=null)
			{
				this.AppendHeigth=this.AppendImage.getHeight();
				this.AppendWidth=this.AppendImage.getWidth();
			succeedread=true;
			}
		} 
		catch (IOException e)
		{
			succeedread=false;
			e.printStackTrace();
		}
		return succeedread;
	}
	/**
	 * 合并图片
	 * <pre>
	 * 也就是说把原始图片和追加图片合并处理并写生成一个新的图片
	 * </pre>
	 * @param x 添加到图片会放到x坐标指定的原始图片的位置上
	 * @param y 添加到图片会放到y坐标指定的原始图片的位置上
	 * @param saveas 将合并好的文件保存到saveas指定的路径下
	 * @throws Exception 
	 */
	public void Merger(int x,int y,String saveas) throws Exception
	{
		this.X=x;
		this.Y=y;
		 MergerImage(saveas);
	}
	/**
	 * 合并图片
	 * <pre>
	 * 也就是说把原始图片和追加图片合并处理并写生成一个新的图片
	 * </pre>
	 * @param saveas  合并图片的保存地址
	 * @return
	 */
	public void  Merger(String saveas) throws Exception
	{
		MergerImage(saveas);
	}
	
	/**
	 * 合并图片
	 * <pre>
	 * 也就是说把原始图片和追加图片合并处理并写生成一个新的图片
	 * </pre>
	 * @param saveas
	 */
	private  void  MergerImage(String saveas)throws Exception
	{
		this.saveas=saveas;
		if(this.AppendImage==null)
		this.readAppendImage();
		if(this.OriginalImage==null)
		this.readOriginalImage();
		this.OriginalImage.getGraphics().drawImage(this.AppendImage, X, Y, null);
		save(null,0,0);
	}
	/**
	 * 保存
	 * @param image
	 * @param x
	 * @param y
	 * @throws Exception
	 */
	protected void save(BufferedImage image,int x,int y)throws Exception
	{
		if(image!=null)
			OriginalImage=image;
		if(x>0)
			X=x;
		if(y>0)
			Y=y;
		BufferedOutputStream outputStream=null;
		try 
		{
			
			FileOutputStream stream=new FileOutputStream(saveas);
			 outputStream=new BufferedOutputStream(stream);
			ImageIO.write(OriginalImage, "jpg", outputStream);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		finally
		{
			if(outputStream!=null)
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	
	/**
	 * 设置坐标位置
	 */
	public  void setCoordinate(int parm) throws Exception 
	{	
		
		this.readAppendImage();
		this.readOriginalImage();
		switch(parm)
		{
			case 1://上左
			this.X=0;
			this.Y=0;
			break;
			case 2://上中
				this.X=(this.OriginalWidth-this.AppendWidth)/2;
				this.Y=0;
			break;
			case 3://上右
				this.X=(this.OriginalWidth-this.AppendWidth);
				this.Y=0;
				break;
			case 4://中左
				this.X=0;
				this.Y=(this.OriginalHeigth-this.AppendHeigth)/2;
				break;
			case 5://居中
				this.X=(this.OriginalWidth-this.AppendWidth)/2;
				this.Y=(this.OriginalHeigth-this.AppendHeigth)/2;
				break;
			case 6://中右
				this.X=(this.OriginalWidth-this.AppendWidth);
				this.Y=(this.OriginalHeigth-this.AppendHeigth)/2;
				break;
			case 7://下左
				this.X=0;
				this.Y=(this.OriginalHeigth-this.AppendHeigth);
				break;
			case 8://下中
				this.X=(this.OriginalWidth-this.AppendWidth)/2;
				this.Y=(this.OriginalHeigth-this.AppendHeigth);
				break;
			case 9://下右
				this.X=this.OriginalWidth-this.AppendWidth;
				this.Y=this.OriginalHeigth-this.AppendHeigth;
				break;
		}
		
		X=Math.abs(X)-10;
		Y=Math.abs(Y)-10;
	}
	
	
}
