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
	//用于身份认证和身份授权
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	// 进入handler方法之后，返回modeandview之前执行
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}
	//执行完Handler方法 之后
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}
}
