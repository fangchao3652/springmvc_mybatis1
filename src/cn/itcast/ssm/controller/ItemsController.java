package cn.itcast.ssm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sun.org.apache.xpath.internal.SourceTree;

import cn.itcast.ssm.po.Items;
import cn.itcast.ssm.po.ItemsCustom;
import cn.itcast.ssm.po.ItemsQueryVo;
import cn.itcast.ssm.service.ItemsService;
import cn.itcast.ssm.service.impl.ItemsServiceImpl;

@Controller
// 对url进行分类管理 可以定义一个根路径
@RequestMapping("/items")

public class ItemsController {
	@Autowired
	private ItemsService itemsService;

	/**
	 * 包装类型的pojo（pojo的属性也是pojo例如ItemsQueryVo）参数绑定 商品名称：
	 * <input name="itemsCustom.name" /> 注意：itemsCustom和包装pojo中的属性一致即可。
	 * 
	 * 若是商品和用户都传来一个name 那么就不知道往哪绑定 所以用包装类型的pojo可以解决这个问题
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryItems.action")
	public ModelAndView queryItems(HttpServletRequest request, ItemsQueryVo itemsQueryVo) throws Exception {

		List<ItemsCustom> itemsList = itemsService.findItemsList(itemsQueryVo);
		// 返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		// 相当 于request的setAttribut，在jsp页面中通过itemsList取数据
		modelAndView.addObject("itemsList", itemsList);
		// 指定视图
		modelAndView.setViewName("/items/itemsList");
		return modelAndView;
	}

	/**
	 * 商品信息修改页面显示
	 * 
	 * @return
	 * @throws Exception
	 */
	// @RequestMapping("/editItems")
	@RequestMapping(value = "/editItems", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView editItems(@RequestParam(value = "id", required = true, defaultValue = "") Integer id11)
			throws Exception {
		// 调用service查询商品信息

		ItemsCustom itemsCustom = itemsService.findItemsById(id11);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("itemsCustom", itemsCustom);
		modelAndView.setViewName("/items/editItems");
		return modelAndView;

	}

	/*
	 * @RequestMapping(value = "/editItems1", method = { RequestMethod.POST,
	 * RequestMethod.GET }) public String editItems1(Model model) throws
	 * Exception { // 调用service查询商品信息 ItemsCustom itemsCustom =
	 * itemsService.findItemsById(1); // 通过形参中的Mode将mode数据传到页面
	 * 相当于modelAndView.addObject方法 model.addAttribute("itemsCustom",
	 * itemsCustom); // 真正视图(jsp路径)=前缀+逻辑视图名+后缀 return "/items/editItems"; }
	 */
	@RequestMapping(value = "/editItems1", method = { RequestMethod.POST, RequestMethod.GET })
	public String editItems1() throws Exception {

		// return "redirect:queryItems.action";
		return "forward:queryItems.action";// 转发时可以利用 request参数共享request
	}

	/**
	 * 修改后提交
	 * 
	 * @return
	 * @throws Exception
	 *             页面中input的name和controller的pojo形参中的属性名称一致，将页面中数据绑定到pojo。
	 */
	@RequestMapping("/editItemsSubmit")
	public String editItemsSubmit(Integer id, ItemsCustom itemsCustom) throws Exception {
		// 调用service 更新商品信息 页面需要将商品信息传到此方法(用参数绑定)
		itemsService.updateItems(id, itemsCustom);
		// ...
		// ModelAndView modelAndView = new ModelAndView();
		// modelAndView.setViewName("/success");
		// return modelAndView;
		return "forward:queryItems.action";// 转发时可以利用 request形参共享request
	}

	// 批量删除
	@RequestMapping("/deleteItems")
	public String deleteItems(int[] items_id) throws Exception {
		// itemsService.delItemsByid(items_id );
		System.out.println(items_id);
		return "/success";
	}

	// 批量修改商品页面
	@RequestMapping("/editItemsQuery.action")
	public ModelAndView editItemsQuery(HttpServletRequest request, ItemsQueryVo itemsQueryVo) throws Exception {

		List<ItemsCustom> itemsList = itemsService.findItemsList(itemsQueryVo);
		// 返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		// 相当 于request的setAttribut，在jsp页面中通过itemsList取数据
		modelAndView.addObject("itemsList", itemsList);
		// 指定视图
		modelAndView.setViewName("/items/editItemsQuery");
		return modelAndView;
	}

	// 批量修改商品提交
	/**
	 * 通过ItemsQueryVo接收批量提交的商品信息 将商品信息list存放到ItemsQueryVo中的itemsList属性中
	 * @param itemsQueryVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/editAllItemsSubmit")
	public  String editAllItemsSubmit(ItemsQueryVo itemsQueryVo)throws Exception{
		
		return "/success";
		
	}

}
