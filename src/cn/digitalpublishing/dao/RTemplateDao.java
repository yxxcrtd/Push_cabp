package cn.digitalpublishing.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.daxtech.framework.util.CollectionsUtil;
import cn.digitalpublishing.po.RTemplate;

/**
 * RTemplate DAO
 * 
 * @author cuixian
 */
@SuppressWarnings("unchecked")
public class RTemplateDao extends CommonDao<RTemplate,String> {
	
	/**
	 * 获取总数
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public Integer getAllTemplateCount(Map<String, Object> condition) throws Exception {
		List<RTemplate> list = null;
		Map<String, Object> t = this.getWhere(condition);
		String hql = "FROM RTemplate t ";
		try {
			list = hibernateDao.getListByHql("id", "CAST(COUNT(*) AS string)", hql + t.get("where").toString(), ((List<Object>) t.get("condition")).toArray(), "", RTemplate.class.getName());
		} catch (Exception e) {
			throw e;
		}
		return null == list ? 0 : Integer.valueOf(list.get(0).getId());
	}

	/**
	 * 根据id查询信息排重
	 * 
	 * @param condition
	 * @param sort
	 * @return
	 * @throws Exception
	 */
	public List<RTemplate> getRTemplateListById(Map<String,Object> condition, String sort) throws Exception {
		List<RTemplate> list = null;
		Map<String, Object> t = this.getWhere(condition);
		String hql = "FROM RTemplate t";
		String property = "id, userTemplateId.name,userTemplateId.id,targetTemplateId.id,targetTemplateId.name";
		String field = "t.id, t.userTemplateId.name,t.userTemplateId.id,t.targetTemplateId.id,t.targetTemplateId.name";
		try {
			list = super.hibernateDao.getListByHql(property, field, hql + t.get("where").toString(), ((List<Object>) t.get("condition")).toArray(), sort, RTemplate.class.getName());
		} catch (Exception e) {
			throw e;
		}
		return list;
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
	public List<RTemplate> getTemplatePagingList(Map<String, Object> condition, String sort, Integer pageCount, Integer page) throws Exception {
		List<RTemplate> list = null;
		String hql = "FROM RTemplate t";
		Map<String, Object> t = this.getWhere(condition);
		String property = "id, userTemplateId.id,userTemplateId.name, targetTemplateId.id,targetTemplateId.name";
		String field = "t.id, t.userTemplateId.id,t.userTemplateId.name, t.targetTemplateId.id,t.targetTemplateId.name";
		try {
			list = super.hibernateDao.getListByHql(property, field, hql + t.get("where").toString(), ((List<Object>) t.get("condition")).toArray(), sort, RTemplate.class.getName(), pageCount, page * pageCount);
		} catch (Exception e) {
			throw e;
		}
		return list;
	}
	
	/**
	 * 根据用户模板ID获取模板映射列表
	 * 
	 * @param condition
	 * @param sort
	 * @return
	 * @throws Exception
	 */
	public List<RTemplate> getRTemplateListByUserTemplateId(Map<String, Object> condition, String sort) throws Exception {
		List<RTemplate> list = null;
		String hql = "FROM RTemplate t";
		Map<String, Object> t = this.getWhere(condition);
		String property = "userTemplateId.id,id, targetTemplateId.id,targetTemplateId.name,userTemplateId.name";
		String field = "DISTINCT t.userTemplateId.id,t.id, t.targetTemplateId.id,targetTemplateId.name,userTemplateId.name";
		try {
			list = super.hibernateDao.getListByHql(property, field, hql + t.get("where").toString(), ((List<Object>) t.get("condition")).toArray(), sort, RTemplate.class.getName());
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
		// 查询条件1：userTemplateId
		if (CollectionsUtil.exist(map, "userTemplateId") && !"".equals(map.get("userTemplateId"))) {
			if (flag == 0) {
				whereString += " WHERE lower(t.userTemplateId.id) = ?";
				flag = 1;
			} else {
				whereString += " AND lower(t.userTemplateId.id) = ?";
			}
			condition.add(map.get("userTemplateId").toString().trim().toLowerCase());
		}
		
		// 查询条件2：targetTemplateId
		if (CollectionsUtil.exist(map, "targetTemplateId") && !"".equals(map.get("targetTemplateId"))) {
			if (flag == 0) {
				whereString += " WHERE lower(t.targetTemplateId.id) = ?";
				flag = 1;
			} else {
				whereString += " AND lower(t.targetTemplateId.id) = ?";
			}
			condition.add(map.get("targetTemplateId").toString().trim().toLowerCase());
		}
		
		table.put("where", whereString);
		table.put("condition", condition);
		return table;
	}	

}
