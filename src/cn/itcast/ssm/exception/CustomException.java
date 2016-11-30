package cn.itcast.ssm.exception;
/**
 * 全局异常类  针对预期的异常处理 需要在程序中抛出此类的异常
 * @author Fangchao
 *
 */
public class CustomException extends Exception{
//异常信息
	public String message;
	public CustomException(String message){
		super(message);
		this.message=message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
