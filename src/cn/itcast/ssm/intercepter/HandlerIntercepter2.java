package cn.itcast.ssm.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 其实是利用AOP机制
 * 
 * @author Fangchao
 *
 */
public class HandlerIntercepter2 implements HandlerInterceptor {
	// 进入Handler方法之前执行
	//应用场景 :;用于身份认证和身份授权
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("HandlerIntercepter2----------preHandle");
		//return false;//false 表示拦截 不向下执行
		 return true;//放行
	}

	// 进入handler方法之后，返回modeandview之前执行
	//应用场景从modeAndView出发：将公用的模型数据（比如菜单导航）在这里放入视图 也可以统一指定视图
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("HandlerIntercepter2----------postHandle");

	}
	//执行完Handler方法 之后
	//应用场景 可以使用统一的异常处理 ，统一的日志处理
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("HandlerIntercepter2----------afterCompletion");

	}
}
