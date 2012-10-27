package net.loyin.util.exception;
/**
 * 业务逻辑异常
 * @author 刘声凤
 */
public class BizException extends Exception {
	private static final long serialVersionUID = -3900033543225822658L;
	private String errCode;
	private String errTitle;
	private String errMsg;
	public BizException(){
		
	}
	public BizException(String errCode,String errTitle,String errMsg,Throwable e){
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
