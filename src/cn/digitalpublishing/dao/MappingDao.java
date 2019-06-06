package cn.digitalpublishing.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.daxtech.framework.util.CollectionsUtil;
import cn.digitalpublishing.po.Mapping;

/**
 * Mapping DAO
 * 
 * @author YangXinXin
 */
@SuppressWarnings("unchecked")
public class MappingDao extends CommonDao<Mapping, String> {
	
	/**
	 * 根据 目标模板ID 获取 所有映射列表
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public List<Mapping> findByTargetTemplateId(Map<String, Object> condition) throws Exception {
		List<Mapping> list = null;
		Map<String, Object> t = this.getWhere(condition);
		String hql = "FROM Mapping m";
		String property = "id, userTemplateNodeId";
		String field = "m.id, m.userTemplateNodeId";
		try {
			list = super.hibernateDao.getListByHql(property, field, hql + t.get("where").toString(), ((List<Object>) t.get("condition")).toArray(), "", Mapping.class.getName());
		} catch (Exception e) {
			throw e;
		}
		return list;
	}
	
	/**
	 * 根据 用户模板ID和目标模板ID 取 映射的列表
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public List<Mapping> findMappingListByUserTemplateIdAndTargetTemplateId(Map<String, Object> condition) throws Exception {
		List<Mapping> list = null;
		Map<String, Object> t = this.getWhere(condition);
		String hql = "FROM Mapping m";
		String property = "id, userTemplateNodeId, userTemplateId, targetTemplateId, targetTemplateNodeId";
		String field = "m.id, m.userTemplateNodeId, m.userTemplateId, m.targetTemplateId, m.targetTemplateNodeId";
		try {
			list = super.hibernateDao.getListByHql(property, field, hql + t.get("where").toString(), ((List<Object>) t.get("condition")).toArray(), "", Mapping.class.getName());
		} catch (Exception e) {
			throw e;
		}
		return list;
	}
	
	/**
	 * 根据 目标模板ID 和 目标模板节点ID 查找 映射对象
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public Mapping findByTargetTemplateIdAndtargetTemplateNodeId(Map<String, Object> condition) throws Exception {
		Mapping mapping = null;
		String hql = "FROM Mapping m";
		Map<String, Object> t = this.getWhere(condition);
		String property = "id, targetTemplateId, targetTemplateNodeId, userTemplateNodeId";
		String field = "m.id, m.targetTemplateId, m.targetTemplateNodeId, m.userTemplateNodeId";
		try {
			List<Mapping> list = super.hibernateDao.getListByHql(property, field, hql + t.get("where").toString(), ((List<Object>) t.get("condition")).toArray(), "", Mapping.class.getName());
			mapping = 0 == list.size() ? null : list.get(0);
		} catch (Exception e) {
			throw e;
		}
		return mapping;
	}

	// Where 条件
	private Map<String, Object> getWhere(Map<String, Object> map) {
		Map<String, Object> table = new HashMap<String, Object>();
		String whereString = "";
		List<Object> condition = new ArrayList<Object>();
		int flag = 0;

		if (CollectionsUtil.exist(map, "targetTemplateId") && !"".equals(map.get("targetTemplateId"))) {
			if (0 == flag) {
				whereString += " WHERE lower(m.targetTemplateId) = ?";
				flag = 1;
			} else {
				whereString += " AND lower(m.targetTemplateId) = ?";
			}
			condition.add(map.get("targetTemplateId").toString().trim().toLowerCase());
		}
		
		if (CollectionsUtil.exist(map, "userTemplateId") && !"".equals(map.get("userTemplateId"))) {
			if (0 == flag) {
				whereString += " WHERE lower(m.userTemplateId) = ?";
				flag = 1;
			} else {
				whereString += " AND lower(m.userTemplateId) = ?";
			}
			condition.add(map.get("userTemplateId").toString().trim().toLowerCase());
		}
		
		if (CollectionsUtil.exist(map, "targetTemplateNodeId") && !"".equals(map.get("targetTemplateNodeId"))) {
			if (0 == flag) {
				whereString += " WHERE lower(m.targetTemplateNodeId) = ?";
				flag = 1;
			} else {
				whereString += " AND lower(m.targetTemplateNodeId) = ?";
			}
			condition.add(map.get("targetTemplateNodeId").toString().trim().toLowerCase());
		}
		
		table.put("where", whereString);
		table.put("condition", condition);
		return table;
	}

}
