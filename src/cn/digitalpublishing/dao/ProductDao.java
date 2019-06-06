package cn.digitalpublishing.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.daxtech.framework.util.CollectionsUtil;
import cn.digitalpublishing.po.TProduct;

/**
 * Product DAO
 * 
 * @author YangXinXin
 */
@SuppressWarnings("unchecked")
public class ProductDao extends CommonDao<TProduct, String> {

	public List<TProduct> getProductListBySourceId(String tsourceId) throws Exception {
		List<TProduct> list = null;
		String hql = "FROM TProduct tp";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tsourceId", tsourceId);
		Map<String, Object> t = this.getWhere(map);
		String property = "id, code, path,isbn,bookName,publisher";
		String field = "tp.id, tp.code, tp.path,tp.isbn,tp.bookName,tp.publisher";
		try {
			list = super.hibernateDao.getListByHql(property, field, hql + t.get("where").toString(), ((List<Object>) t.get("condition")).toArray(), "", TProduct.class.getName());
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
		if (CollectionsUtil.exist(map, "tsourceId") && !"".equals(map.get("tsourceId"))) {
			if (flag == 0) {
				whereString += " WHERE lower(tp.source.id) = ?";
				flag = 1;
			} else {
				whereString += " AND lower(tp.source.id) = ?";
			}
			condition.add(map.get("tsourceId").toString().trim().toLowerCase());
		}

		table.put("where", whereString);
		table.put("condition", condition);
		return table;
	}

}
