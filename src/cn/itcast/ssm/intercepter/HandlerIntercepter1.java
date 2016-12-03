package cn.itcast.ssm.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 其实是利用AOP机制
 * springmvc的拦截器和struts2的不太一样 不是全局的 
 * 1.针对某一个 handlerMapping进行拦截的，只有经过handlerMapping映射成功的handler才使用这个拦截器
 * 
 * 2.springMvc 配置类似全局的拦截器：将配置的拦截器注入到每一个hanlderMapping中
 * @author Fangchao
 *
 *
 *1放行 2不放行
 *HandlerIntercepter1----------preHandle
 *HandlerIntercepter2----------preHandle
 *HandlerIntercepter1----------afterCompletion
 */
public class HandlerIntercepter1 implements HandlerInterceptor {

	// 进入Handler方法之前执行
	//应用场景 :;用于身份认证和身份授权
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("HandlerIntercepter1----------preHandle");
		//return false;//false 表示拦截 不向下执行
		 return true;//放行
	}

	// 进入handler方法之后，返回modeandview之前执行
	//应用场景从modeAndView出发：将公用的模型数据（比如菜单导航）在这里放入视图 也可以统一指定视图
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
		ModelAndView modelAndView) throws Exception {
		System.out.println("HandlerIntercepter1----------postHandle");

	}
	//执行完Handler方法 之后
	//应用场景 可以使用统一的异常处理 ，统一的日志处理
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("HandlerIntercepter1----------afterCompletion");

	}
}
