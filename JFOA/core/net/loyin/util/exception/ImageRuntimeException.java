package net.loyin.util.exception;

@SuppressWarnings("serial")
public class ImageRuntimeException extends RuntimeException {
	public ImageRuntimeException(String path,String proesstype){
		super("对图片"+path +"进行"  + proesstype +"出错" );
	}
}
