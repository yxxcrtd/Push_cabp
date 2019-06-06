package cn.digitalpublishing.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.daxtech.framework.util.CollectionsUtil;
import cn.digitalpublishing.resource.OnixCode;

/**
 * OnixCode DAO
 * 
 * @author YangXinXin
 */
@SuppressWarnings("unchecked")
public class OnixCodeDao extends CommonDao<OnixCode, String> {

	/**
	 * 获取总数
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public Integer getAllOnixCodeCount(Map<String, Object> condition) throws Exception {
		List<OnixCode> list = null;
		Map<String, Object> t = this.getWhere(condition);
		String hql = "FROM OnixCode oc ";
		try {
			list = hibernateDao.getListByHql("onixCodeID", "CAST(count(*) as string)", hql + t.get("where").toString(), ((List<Object>) t.get("condition")).toArray(), "", OnixCode.class.getName());
		} catch (Exception e) {
			throw e;
		}
		return null == list ? 0 : Integer.valueOf(list.get(0).getOnixCodeID());
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
	public List<OnixCode> getOnixCodePagingList(Map<String, Object> condition, String sort, Integer pageCount, Integer page) throws Exception {
		List<OnixCode> list = null;
		String hql = "FROM OnixCode oc ";
		Map<String, Object> t = this.getWhere(condition);
		String property = "onixCodeID, codeList, codeValue, description, notes";
		String field = "oc.onixCodeID, oc.codeList, oc.codeValue, oc.description, oc.notes";
		try {
			list = super.hibernateDao.getListByHql(property, field, hql + t.get("where").toString(), ((List<Object>) t.get("condition")).toArray(), sort, OnixCode.class.getName(), pageCount, page * pageCount);
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
		
		// 查询条件1：CodeList
		if (CollectionsUtil.exist(map, "codeList") && !"".equals(map.get("codeList"))) {
			if (flag == 0) {
				whereString += " WHERE lower(oc.codeList) like ?";
				flag = 1;
			} else {
				whereString += " AND lower(oc.codeList) like ?";
			}
			condition.add(Integer.valueOf((String) map.get("codeList")));
		}

		// 查询条件2：描述
		if (CollectionsUtil.exist(map, "description") && !"".equals(map.get("description"))) {
			if (flag == 0) {
				whereString += " WHERE lower(oc.description) like ?";
				flag = 1;
			} else {
				whereString += " AND lower(oc.description) like ?";
			}
			condition.add("%" + map.get("description").toString().trim().toLowerCase() + "%");
		}

		// 查询条件3：备注
		if (CollectionsUtil.exist(map, "notes") && !"".equals(map.get("notes"))) {
			if (flag == 0) {
				whereString += " WHERE lower(oc.notes) like ?";
				flag = 1;
			} else {
				whereString += " AND lower(oc.notes) like ?";
			}
			condition.add("%" + map.get("notes").toString().trim().toLowerCase() + "%");
		}

		// 查询条件4：中文翻译
		if (CollectionsUtil.exist(map, "comment") && !"".equals(map.get("comment"))) {
			if (flag == 0) {
				whereString += " WHERE lower(oc.comment) like ?";
				flag = 1;
			} else {
				whereString += " AND lower(oc.comment) like ?";
			}
			condition.add("%" + map.get("comment").toString().trim().toLowerCase() + "%");
		}
		
		table.put("where", whereString);
		table.put("condition", condition);
		return table;
	}

}
