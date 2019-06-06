package cn.digitalpublishing.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.daxtech.framework.util.CollectionsUtil;
import cn.digitalpublishing.po.PushSta;
import cn.digitalpublishing.po.TSource;
import cn.digitalpublishing.po.UserTemplate;

/**
 * Push DAO
 * 
 * @author YangXinXin
 */
@SuppressWarnings("unchecked")
public class PushDao extends CommonDao<TSource, String> {

	/**
	 * 获取TSource中状态是3（可以推送的）的记录总数
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public Integer getTSourceCountByStatus(Map<String, Object> condition) throws Exception {
		List<TSource> list = null;
		Map<String, Object> t = this.getWhere(condition);
		String hql = "FROM TSource ts ";
		try {
			list = super.hibernateDao.getListByHql("id", "CAST(COUNT(*) AS string)", hql + t.get("where").toString(), ((List<Object>) t.get("condition")).toArray(), "", TSource.class.getName());
		} catch (Exception e) {
			throw e;
		}
		return null == list ? 0 : Integer.valueOf(list.get(0).getId());
	}

	/**
	 * 获取TSource中状态是3（可以推送的）的分页列表
	 * 
	 * @param condition
	 * @param sort
	 * @param pageCount
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<TSource> getTSourcePagingListByStatus(Map<String, Object> condition, String sort, Integer pageCount, Integer page) throws Exception {
		List<TSource> list = null;
		String hql = "FROM TSource ts ";
		Map<String, Object> t = this.getWhere(condition);
		String property = "id, fileName, ftpcode";
		String field = "ts.id, ts.fileName, ts.ftpcode";
		try {
			list = super.hibernateDao.getListByHql(property, field, hql + t.get("where").toString(), ((List<Object>) t.get("condition")).toArray(), sort, TSource.class.getName(), pageCount, page * pageCount);
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
		
		// 查询条件1：fileName
		if (CollectionsUtil.exist(map, "fileName") && !"".equals(map.get("fileName"))) {
			if (flag == 0) {
				whereString += " WHERE lower(ts.fileName) like ?";
				flag = 1;
			} else {
				whereString += " AND lower(ts.fileName) like ?";
			}
			condition.add("%" + map.get("fileName").toString().trim().toLowerCase() + "%");
		}
		
		// 查询条件2：status
		if (CollectionsUtil.exist(map, "status") && !"".equals(map.get("status"))) {
			if (0 == flag) {
				whereString += " WHERE lower(ts.status) = ?";
				flag = 1;
			} else {
				whereString += " AND lower(ts.status) = ?";
			}
			condition.add((Integer) map.get("status"));
		}

		table.put("where", whereString);
		table.put("condition", condition);
		return table;
	}

}
