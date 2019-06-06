package cn.digitalpublishing.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.daxtech.framework.util.CollectionsUtil;
import cn.digitalpublishing.po.UserTemplateNode;

@SuppressWarnings("unchecked")
public class UserTemplateNodeDao extends CommonDao<UserTemplateNode, String> {

	/**
	 * 获取总数（根据源ID获取目标模板节点列表）
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public Integer getTemplateNodeCount(Map<String, Object> condition) throws Exception {
		List<UserTemplateNode> list = null;
		Map<String, Object> t = this.getWhere(condition);
		String hql = "FROM UserTemplateNode a";
		try {
			list = super.hibernateDao.getListByHql("id", "CAST(COUNT(*) AS string)", hql + t.get("where").toString(), ((List<Object>) t.get("condition")).toArray(), "", UserTemplateNode.class.getName());
		} catch (Exception e) {
			throw e;
		}
		return null == list ? 0 : Integer.valueOf(list.get(0).getId());
	}

	/**
	 * 获取分页信息（根据源ID获取目标模板节点列表）
	 * 
	 * @param condition
	 * @param sort
	 * @param pageCount
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<UserTemplateNode> getTemplateNodeListByUser(Map<String, Object> condition, String sort) throws Exception {
		List<UserTemplateNode> list = null;
		String hql = "FROM UserTemplateNode a LEFT JOIN a.mapping m";
		Map<String, Object> t = this.getWhere(condition);
		String property = "id, nodeCode, nodePath, userTemplateNodeId";
		String field = "a.id, a.nodeCode, a.nodePath, m.userTemplateNodeId";
		try {
			list = super.hibernateDao.getListByHql(property, field, hql + t.get("where").toString(), ((List<Object>) t.get("condition")).toArray(), sort, UserTemplateNode.class.getName());
		} catch (Exception e) {
			throw e;
		}
		return list;
	}

	/**
	 * 获取分页信息（根据源ID获取用户模板节点列表）
	 * 
	 * @param condition
	 * @param sort
	 * @param pageCount
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<UserTemplateNode> getUserTemplateNodesPagingList(Map<String, Object> condition, String sort, Integer pageCount, Integer page) throws Exception {
		List<UserTemplateNode> list = null;
		String hql = "FROM UserTemplateNode a";
		Map<String, Object> t = this.getWhere(condition);
		String property = "id, nodeCode, nodePath,nodeName";
		String field = "a.id, a.nodeCode, a.nodePath,a.nodeName";
		try {
			list = super.hibernateDao.getListByHql(property, field, hql + t.get("where").toString(), ((List<Object>) t.get("condition")).toArray(), sort, UserTemplateNode.class.getName(), pageCount, page * pageCount);
		} catch (Exception e) {
			throw e;
		}
		return list;
	}

	/**
	 * 获取用户模板节点列表
	 * 
	 * @param condition
	 * @param sort
	 * @return
	 * @throws Exception
	 */
	public List<UserTemplateNode> getTemplateNodeListByTarget(Map<String, Object> condition, String sort, Integer pageCount, Integer page) throws Exception {
		List<UserTemplateNode> list = null;
		Map<String, Object> t = this.getWhere(condition);
		String hql = "FROM UserTemplateNode a";
		String property = "id, nodeCode, nodePath";
		String field = "a.id, a.nodeCode, a.nodePath";
		try {
			list = super.hibernateDao.getListByHql(property, field, hql + t.get("where").toString(), ((List<Object>) t.get("condition")).toArray(), sort, UserTemplateNode.class.getName(), pageCount, page * pageCount);
		} catch (Exception e) {
			throw e;
		}
		return list;
	}
	
	/**
	 * 根据模板Id获取模板节点列表
	 * 
	 * @param condition
	 * @param sort
	 * @return
	 * @throws Exception
	 */
	public List<UserTemplateNode> getTemplateNodeListByUserTemplateId(Map<String, Object> condition, String sort) throws Exception {
		List<UserTemplateNode> list = null;
		Map<String, Object> t = this.getWhere(condition);
		String hql = "FROM UserTemplateNode a";
		String property = "id, nodeCode, nodePath";
		String field = "a.id, a.nodeCode, a.nodePath";
		try {
			list = super.hibernateDao.getListByHql(property, field, hql + t.get("where").toString(), ((List<Object>) t.get("condition")).toArray(), sort, UserTemplateNode.class.getName());
		} catch (Exception e) {
			throw e;
		}
		return list;
	}

	// Where 条件
	private Map<String, Object> getWhere(Map<String, Object> map) {
		Map<String, Object> table = new HashMap<String, Object>();
		String whereString = "";
		List<Object> condition = new ArrayList<Object>();
		int flag = 0;

		// targetId 查询条件（以targetId为主，先判断。当查询sourceId时，则先要将targetId设为空[condition.put("targetId", "");]）
		if (CollectionsUtil.exist(map, "targetId") && !"".equals(map.get("targetId"))) {
			if (0 == flag) {
				whereString += " WHERE lower(a.userTemplate.id) = ?";
				flag = 1;
			} else {
				whereString += " AND lower(a.userTemplate.id) = ?";
			}
			condition.add(map.get("targetId").toString().trim().toLowerCase());
		} else 
		// sourceId 查询条件
		if (CollectionsUtil.exist(map, "sourceId") && !"".equals(map.get("sourceId"))) {
			if (0 == flag) {
				whereString += " WHERE lower(a.userTemplate.id) = ?";
				flag = 1;
			} else {
				whereString += " AND lower(a.userTemplate.id) = ?";
			}
			condition.add(map.get("sourceId").toString().trim().toLowerCase());
		}
		
		// parentid 查询条件
		if (CollectionsUtil.exist(map, "parentid") && !"".equals(map.get("parentid"))) {
			if (0 == flag) {
				whereString += " WHERE a.userTemplate.id = ?";
				flag = 1;
			} else {
				whereString += " AND a.userTemplate.id = ?";
			}
			condition.add(map.get("parentid").toString().trim().toLowerCase());
		}
		
		// 用户模板ID
		if (CollectionsUtil.exist(map, "userTemplateId") && !"".equals(map.get("userTemplateId"))) {
			if (0 == flag) {
				whereString += " WHERE lower(a.userTemplateid) = ?";
				flag = 1;
			} else {
				whereString += " AND lower(a.userTemplateid) = ?";
			}
			condition.add(map.get("userTemplateId").toString().trim().toLowerCase());
		}
		
		// 目标模板ID
		if (CollectionsUtil.exist(map, "targetTemplateId") && !"".equals(map.get("targetTemplateId"))) {
			if (0 == flag) {
				whereString += " WHERE lower(a.userTemplateid) = ?";
				flag = 1;
			} else {
				whereString += " AND lower(a.userTemplateid) = ?";
			}
			condition.add(map.get("targetTemplateId").toString().trim().toLowerCase());
		}
		
		// ID1
		if (CollectionsUtil.exist(map, "id1") && !"".equals(map.get("id1"))) {
			if (0 == flag) {
				whereString += " WHERE lower(a.userTemplate.id) = ?";
				flag = 1;
			} else {
				whereString += " AND lower(a.userTemplate.id) = ?";
			}
			condition.add(map.get("id1").toString().trim().toLowerCase());
		}
		
		if (CollectionsUtil.exist(map, "id2") && !"".equals(map.get("id2"))) {
			if (0 == flag) {
				whereString += " WHERE lower(a.id) = ?";
				flag = 1;
			} else {
				whereString += " AND lower(a.id) = ?";
			}
			condition.add(map.get("id2").toString().trim().toLowerCase());
		}
		
		table.put("where", whereString);
		table.put("condition", condition);
		return table;
	}

}
