package cn.digitalpublishing.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.daxtech.framework.util.CollectionsUtil;
import cn.digitalpublishing.resource.Onix;

/**
 * Onix DAO
 * 
 * @author YangXinXin
 */
@SuppressWarnings("unchecked")
public class OnixDao extends CommonDao<Onix, String> {

	/**
	 * 获取总数
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public Integer getAllOnixCount(Map<String, Object> condition) throws Exception {
		List<Onix> list = null;
		Map<String, Object> t = this.getWhere(condition);
		String hql = "FROM Onix o ";
		try {
			list = hibernateDao.getListByHql("onixID", "CAST(COUNT(*) AS string)", hql + t.get("where").toString(), ((List<Object>) t.get("condition")).toArray(), "", Onix.class.getName());
		} catch (Exception e) {
			throw e;
		}
		return null == list ? 0 : Integer.valueOf(list.get(0).getOnixID());
	}

	/**
	 * 获取分页信息
	 * 
	 * @param condition
	 * @param sort
	 * @param pageCount
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<Onix> getOnixPagingList(Map<String, Object> condition, String sort, Integer pageCount, Integer page) throws Exception {
		List<Onix> list = null;
		String hql = "FROM Onix o ";
		Map<String, Object> t = this.getWhere(condition);
		String property = "onixID, nodePath, necessary, only, comment";
		String field = "o.onixID, o.nodePath, o.necessary, o.only, o.comment";
		try {
			list = super.hibernateDao.getListByHql(property, field, hql + t.get("where").toString(), ((List<Object>) t.get("condition")).toArray(), sort, Onix.class.getName(), pageCount, page * pageCount);
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
		
		// 查询条件1：nodePath
		if (CollectionsUtil.exist(map, "nodePath") && !"".equals(map.get("nodePath"))) {
			if (flag == 0) {
				whereString += " WHERE lower(o.nodePath) like ?";
				flag = 1;
			} else {
				whereString += " AND lower(o.nodePath) like ?";
			}
			condition.add("%" + map.get("nodePath").toString().trim().toLowerCase() + "%");
		}
		
		// 查询条件2：comment
		if (CollectionsUtil.exist(map, "comment") && !"".equals(map.get("comment"))) {
			if (flag == 0) {
				whereString += " WHERE lower(o.comment) like ?";
				flag = 1;
			} else {
				whereString += " AND lower(o.comment) like ?";
			}
			condition.add("%" + map.get("comment").toString().trim().toLowerCase() + "%");
		}

		table.put("where", whereString);
		table.put("condition", condition);
		return table;
	}

}
