package cn.itcast.ssm.po;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品的包装对象
 *  n
 * @author Fangchao
 *
 */
public class ItemsQueryVo {
  //商品信息
	Items items;
	//为了更好的扩展 对原始的po进行扩展
	ItemsCustom itemsCustom;
	
	//list参数绑定
	List<ItemsCustom> itemsList;
	//Map类型参数绑定
	/**
	 * <input type="text"name="itemInfo['name']"/>
	 */
	private Map<String, Object> itemInfo = new HashMap<String, Object>();
	
	public Map<String, Object> getItemInfo() {
		return itemInfo;
	}
	public void setItemInfo(Map<String, Object> itemInfo) {
		this.itemInfo = itemInfo;
	}
	public List<ItemsCustom> getItemsList() {
		return itemsList;
	}
	public void setItemsList(List<ItemsCustom> itemsList) {
		this.itemsList = itemsList;
	}
	public Items getItems() {
		return items;
	}
	public void setItems(Items items) {
		this.items = items;
	}
	public ItemsCustom getItemsCustom() {
		return itemsCustom;
	}
	public void setItemsCustom(ItemsCustom itemsCustom) {
		this.itemsCustom = itemsCustom;
	}
	
}
