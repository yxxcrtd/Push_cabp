package cn.digitalpublishing.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.daxtech.framework.util.CollectionsUtil;
import cn.digitalpublishing.po.Content;

/**
 * Content DAO
 * 
 * @author YangXinXin
 */
public class ContentDao extends CommonDao<Content, String> {

	@SuppressWarnings("unchecked")
	public List<Content> getContentListByProductId(String productId) throws Exception {
		List<Content> list = null;
		String hql = "FROM Content c";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("productId", productId);
		Map<String, Object> t = this.getWhere(map);
		String property = "id, nodePath, nodeValue";
		String field = "c.id, c.nodePath, c.nodeValue";
		try {
			list = super.hibernateDao.getListByHql(property, field, hql + t.get("where").toString(), ((List<Object>) t.get("condition")).toArray(), "", Content.class.getName());
		} catch (Exception e) {
			throw e;
		}
		return list;
	}

	
	/**
	 * 获取 Where 条件
	 * 
	 * @param map
	 * @return
	 */
	private Map<String, Object> getWhere(Map<String, Object> map) {
		Map<String, Object> table = new HashMap<String, Object>();
		String whereString = "";
		List<Object> condition = new ArrayList<Object>();
		int flag = 0;
		
		// 查询条件1：tsourceId
		if (CollectionsUtil.exist(map, "productId") && !"".equals(map.get("productId"))) {
			if (flag == 0) {
				whereString += " WHERE lower(c.tProduct.id) = ?";
				flag = 1;
			} else {
				whereString += " AND lower(c.tProduct.id) = ?";
			}
			condition.add(map.get("productId").toString().trim().toLowerCase());
		}

		table.put("where", whereString);
		table.put("condition", condition);
		return table;
	}
	
}
