package cn.itcast.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itcast.ssm.po.ItemsCustom;

@Controller
public class JsonController {
	/**
	 * 请求json 串  返回json
	 * @RequestBody 将请求的json串转成对象（商品信息）
	 * @ResponseBody 将对象转成json输出
	 * @return
	 */
	@RequestMapping("/requestJson")
	public @ResponseBody ItemsCustom requestJson(@RequestBody ItemsCustom itemsCustom ) {
		
		return itemsCustom;
	}
	
	
	@RequestMapping("/responseJson")
	public @ResponseBody ItemsCustom responseJson( ItemsCustom itemsCustom ) {
		
		return itemsCustom;
	}

}
