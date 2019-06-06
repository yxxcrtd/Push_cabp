package cn.digitalpublishing.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.daxtech.framework.util.CollectionsUtil;
import cn.digitalpublishing.po.OOrderDetail;

public class OrderDetailDao extends CommonDao<OOrderDetail, String> {

	private Map<String, Object> getWhere(Map<String, Object> map) {
		Map<String, Object> table = new HashMap<String, Object>();
		String whereString = "";
		List<Object> condition = new ArrayList<Object>();
		int flag = 0;
		if (CollectionsUtil.exist(map, "name") && !"".equals(map.get("name"))) {
			if (flag == 0) {
				whereString += " where lower(o.name) like ?";
				flag = 1;
			} else {
				whereString += " and lower(o.name) like ?";
			}
			condition.add("%" + map.get("name").toString().trim().toLowerCase() + "%");
		}

		if (CollectionsUtil.exist(map, "publisher") && !"".equals(map.get("publisher"))) {
			if (flag == 0) {
				whereString += " where lower(o.publisher) like ?";
				flag = 1;
			} else {
				whereString += " and lower(o.publisher) like ?";
			}
			condition.add("%" + map.get("publisher").toString().trim().toLowerCase() + "%");
		}

		if (CollectionsUtil.exist(map, "author") && !"".equals(map.get("author"))) {
			if (flag == 0) {
				whereString += " where lower(o.author) like ?";
				flag = 1;
			} else {
				whereString += " and lower(o.author) like ?";
			}
			condition.add("%" + map.get("author").toString().trim().toLowerCase() + "%");
		}

		if (CollectionsUtil.exist(map, "ostatus") && !"".equals(map.get("ostatus"))) {
			if (flag == 0) {
				whereString += " where o.status = ?";
				flag = 1;
			} else {
				whereString += " and o.status = ?";
			}
			condition.add(map.get("ostatus"));
		}

		if (CollectionsUtil.exist(map, "createdon") && !"".equals(map.get("createdon"))) {
			if (flag == 0) {
				whereString += " where lower(o.createdon) = ?";
				flag = 1;
			} else {
				whereString += " and lower(o.createdon) = ?";
			}
			condition.add(map.get("createdon"));
		}
		table.put("where", whereString);
		table.put("condition", condition);
		return table;
	}

	/**
	 * 获取总数
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Integer getCount(Map<String, Object> condition) throws Exception {
		List<OOrderDetail> list = null;
		Map<String, Object> t = this.getWhere(condition);
		String hql = " from OOrderDetail o ";
		try {
			list = this.hibernateDao.getListByHql("id", "cast(count(*) as string)", hql + t.get("where").toString(), ((List<Object>) t.get("condition")).toArray(), "", OOrderDetail.class.getName());
		} catch (Exception e) {
			throw e;
		}
		return list == null ? 0 : Integer.valueOf(list.get(0).getId());
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
	@SuppressWarnings("unchecked")
	public List<OOrderDetail> getPagingList(Map<String, Object> condition, String sort, Integer pageCount, Integer page) throws Exception {
		List<OOrderDetail> list = null;
		String hql = " from OOrderDetail o ";
		Map<String, Object> t = this.getWhere(condition);
		String property = "id,odetailNum,ourCode,createdby,createdon,updatedby,updatedon,name,author,publisher,pubid,ip,listPrice,quantity,settledPrice,status";
		String field = "o.id,o.odetailNum,o.ourCode,o.createdby,o.createdon,o.updatedby,o.updatedon,o.name,o.author,o.publisher,o.pubid,o.ip,o.listPrice,o.quantity,o.settledPrice,o.status";
		try {
			list = super.hibernateDao.getListByHql(property, field, hql + t.get("where").toString(), ((List<Object>) t.get("condition")).toArray(), sort, OOrderDetail.class.getName(), pageCount, page * pageCount);

		} catch (Exception e) {
			throw e;
		}
		return list;
	}
}
