package cn.itcast.ssm.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sun.org.apache.xpath.internal.SourceTree;

import cn.itcast.ssm.controller.validation.ValidGroup1;
import cn.itcast.ssm.exception.CustomException;
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

	@ModelAttribute("itemsTypes")
	public Map getMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("101", "电子");
		map.put("102", "母婴");
		 
		return map;
	}

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
		// 判断商品为空
		if (itemsCustom == null) {
			throw new CustomException("要修改的商品不存在！！！");// 业务类最好放在service层
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("items", itemsCustom);
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
	 * 
	 *             // 在需要校验的pojo前边添加@Validated，在需要校验的pojo后边添加BindingResult //
	 *             bindingResult接收校验出错信息 // 注意：@Validated和BindingResult
	 *             bindingResult是配对出现，并且形参顺序是固定的（一前一后）。 //
	 *             value={ValidGroup1.class}指定使用ValidGroup1分组的 校验
	 *             // @ModelAttribute可以指定pojo回显到页面在request中的key
	 */
	@RequestMapping("/editItemsSubmit")
	public String editItemsSubmit(Model model, Integer id,
			@ModelAttribute("items") @Validated(value = { ValidGroup1.class }) ItemsCustom itemsCustom,
			BindingResult bindingResult, MultipartFile items_pic) throws Exception {
		// 调用service 更新商品信息 页面需要将商品信息传到此方法(用参数绑定)

		// 获取校验错误信息
		if (bindingResult.hasErrors()) {
			// 输出错误信息
			List<ObjectError> allErrors = bindingResult.getAllErrors();

			for (ObjectError objectError : allErrors) {
				// 输出错误信息
				System.out.println(objectError.getDefaultMessage());

			}
			// 将错误信息传到页面
			model.addAttribute("allErrors", allErrors);

			// 如果不用@ModeAttribute可以直接使用model将提交pojo回显到页面
			// model.addAttribute("items", itemsCustom);

			// 出错重新到商品修改页面
			return "/items/editItems";
		}
		// 上传图片
		if (!items_pic.isEmpty()) {
			// 存储图片的位置
			String pic_path = "G:\\FFFFFF\\upload\\temp";
			String originalFilename = items_pic.getOriginalFilename();
			// 新的图片名称
			String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
			int hash = newFileName.hashCode();
			String hashStr = Integer.toHexString(hash);// 转成十六进制（长度为8）
														// 每一位生成一个文件夹（每一级最多16个目录）
			char[] hss = hashStr.toCharArray();// 转为char型数组
			String pic = "";
			for (char c : hss) {
				pic_path += "\\" + c;
				pic += "\\" + c;
			}
			new File(pic_path).mkdirs();
			File newFile = new File(pic_path, newFileName);
			// 将内存中的数据写入磁盘
			items_pic.transferTo(newFile);
			// 如果上传成功 要将新的图片名称 写到itemsCustom中
			itemsCustom.setPic(pic + "\\" + newFileName);
		}

		itemsService.updateItems(id, itemsCustom);
		return "redirect:queryItems.action";// 转发时可以利用 request形参共享request
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
	 * 
	 * @param itemsQueryVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/editAllItemsSubmit")
	public String editAllItemsSubmit(ItemsQueryVo itemsQueryVo) throws Exception {
		// 更新
		// ......
		return "/success";

	}

	// 查询商品信息输出json
	/**
	 * @PathVariable 用于将请求url中的模板变量{xxx}映射到功能处理方法的参数上
	 * @param id
	 * @return
	 * @throws Exception
	 * 
	 * http://localhost:8080/1springmvc_mybatis/items/itemsView/1.action
	 * 之前配置的控制器是只拦截 *.action
	 * 要想去掉后边的action要配置一个新的前端控制器  多个控制器可以并存
	 */
	@RequestMapping("/itemsView/{id}")
	public @ResponseBody ItemsCustom itemsView(@PathVariable("id") Integer id12) throws Exception {
		ItemsCustom itemCustom = itemsService.findItemsById(id12);

		return itemCustom;

	}

}
