package cn.itcast.ssm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginControler {

	//登录
	@RequestMapping("/login")
	public  String login(HttpSession session,String username,String password)throws Exception{
		//service 登陆验证
		//。。。
		//保存用户身份信息
		session.setAttribute("username", username);
		//重定向到商品的列表
		return "redirect:/items/queryItems.action";
	}
	//注销
	@RequestMapping("/logout")
	public  String logout(HttpSession session)throws Exception{
		 //清除session 
		session.invalidate();
		return "redirect:/items/queryItems.action";
	}
}
