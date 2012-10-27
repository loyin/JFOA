package net.loyin.util.exception;
/**
 * 系统运行异常
 * @author 刘声凤
 *
 */
public class SystemRunException extends Exception {
	private static final long serialVersionUID = 2331615285308618027L;
	private String errCode;
	private String errTitle;
	private String errMsg;
	public SystemRunException(){
		
	}
	public SystemRunException(String errCode,String errTitle,String errMsg,Throwable e){
		super(errMsg,e);
		this.errCode=errCode;
		this.errMsg=errMsg;
		this.errTitle=errTitle;
	}
	public String getErrCode() {
		return errCode;
	}
	public String getErrTitle() {
		return errTitle;
	}
	public String getErrMsg() {
		return errMsg;
	}
}
