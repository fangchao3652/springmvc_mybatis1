package cn.itcast.ssm.service;

import java.util.List;

import cn.itcast.ssm.po.ItemsCustom;
import cn.itcast.ssm.po.ItemsQueryVo;

public interface ItemsService {
	// 商品查詢列表
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;
	//根据id查询商品信息
	public  ItemsCustom findItemsById(int id) throws Exception;
	//修改商品信息
	/**
	 * 
	 * @param id  虽然 itemsCustom 里面有id 但是为了让其他人知道其功能是根据id修改信息 所以还是传个id
	 * @param itemsCustom
	 * @throws Exception
	 */
	public void  updateItems(int id,ItemsCustom itemsCustom) throws Exception;
	
	public void delItemsByid(int id) throws Exception;
}
