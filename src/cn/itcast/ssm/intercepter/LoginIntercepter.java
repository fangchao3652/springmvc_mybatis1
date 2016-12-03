package cn.itcast.ssm.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
 
public class LoginIntercepter implements HandlerInterceptor {

	// 进入Handler方法之前执行
	//应用场景 :;用于身份认证和身份授权
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//获取url
		String url=request.getRequestURI();
		//判断url是否是公开地址（实际是使用时 要将公开地址配置到配置文件中）
		if(url.contains("login.action")){
			return true;
		}
		//判断session
		HttpSession session = request.getSession();
		//从session中取出用户信息
		String username=(String) session.getAttribute("username");
		if(username!=null){
			//身份存在放行
			return true;
		}
		//表示用户身份需要认证
		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
		return false;
	}

	// 进入handler方法之后，返回modeandview之前执行
	//应用场景从modeAndView出发：将公用的模型数据（比如菜单导航）在这里放入视图 也可以统一指定视图
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
		ModelAndView modelAndView) throws Exception {
		System.out.println("HandlerIntercepter login----------postHandle");

	}
	//执行完Handler方法 之后
	//应用场景 可以使用统一的异常处理 ，统一的日志处理
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
			System.out.println("HandlerIntercepter login----------afterCompletion");

	}
}
