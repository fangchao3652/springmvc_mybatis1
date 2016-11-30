package cn.itcast.ssm.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局的异常解析器
 * @author Fangchao
 *
 */
public class CustomExceptionResolver implements HandlerExceptionResolver {

	
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		//handler是处理器适配器要执行的Handler对象
		
		//解析出异常类型
		//如果是系统自定义的异常 直接取出异常 在错误页面显示 
//		String message;
//		if(ex instanceof CustomException){
//			message=((CustomException)ex).getMessage();
//		}else{
//			//如果该异常不是系统自定义的异常 构造一个自定义的异常类型（信息为 “未知错误”）
//			message="未知错误";
//		}
		//上面代码变为
		CustomException customException=null;
		if(ex instanceof CustomException){
			customException=(CustomException) ex;
		}else{
			customException=new CustomException("未知错误");
		}
		String message=customException.getMessage();
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("message", message);
		modelAndView.setViewName("/error");
		return modelAndView;
	}

}
