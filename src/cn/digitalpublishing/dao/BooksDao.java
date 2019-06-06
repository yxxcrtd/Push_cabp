package cn.digitalpublishing.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.daxtech.framework.util.CollectionsUtil;
import cn.digitalpublishing.po.Books;

public class BooksDao extends CommonDao<Books, String> {

	private Map<String, Object> getWhere(Map<String, Object> map) {
		Map<String, Object> table = new HashMap<String, Object>();
		String whereString = "";
		List<Object> condition = new ArrayList<Object>();
		int flag = 0;
		if (CollectionsUtil.exist(map, "title") && !"".equals(map.get("title"))) {
			if (flag == 0) {
				whereString += " where lower(b.title) like ?";
				flag = 1;
			} else {
				whereString += " and lower(b.title) like ?";
			}
			condition.add("%" + map.get("title").toString().trim().toLowerCase() + "%");
		}

		if (CollectionsUtil.exist(map, "isbn") && !"".equals(map.get("isbn"))) {
			if (flag == 0) {
				whereString += " where lower(b.isbn) like ?";
				flag = 1;
			} else {
				whereString += " and lower(b.isbn) like ?";
			}
			condition.add("%" + map.get("isbn").toString().trim().toLowerCase() + "%");
		}

		if (CollectionsUtil.exist(map, "category") && !"".equals(map.get("category"))) {
			if (flag == 0) {
				whereString += " where lower(b.category)  like ?";
				flag = 1;
			} else {
				whereString += " and lower(b.category) like ?";
			}
			condition.add("%" + map.get("category").toString().trim().toLowerCase() + "%");
		}

		if (CollectionsUtil.exist(map, "cunZai") && !"".equals(map.get("cunZai"))) {
			if (flag == 0) {
				whereString += " where b.cunZai = ?";
				flag = 1;
			} else {
				whereString += " and b.cunZai = ?";
			}
			condition.add(map.get("cunZai").toString().trim());
		}

		if (CollectionsUtil.exist(map, "isnotown") && !"".equals(map.get("isnotown"))) {
			if (flag == 0) {
				whereString += " where b.id <> ?";
				flag = 1;
			} else {
				whereString += " and b.id <> ?";
			}
			condition.add(map.get("isnotown").toString().trim().toLowerCase());
		}

		if (CollectionsUtil.exist(map, "orderNo") && !"".equals(map.get("orderNo"))) {
			if (flag == 0) {
				whereString += " where lower(b.orderNo) like ?";
				flag = 1;
			} else {
				whereString += " and lower(b.orderNo) like ?";
			}
			condition.add("%" + map.get("orderNo").toString().trim().toLowerCase() + "%");
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
		List<Books> list = null;
		Map<String, Object> t = this.getWhere(condition);
		String hql = " from Books b ";
		try {
			list = this.hibernateDao.getListByHql("id", "cast(count(*) as string)", hql + t.get("where").toString(), ((List<Object>) t.get("condition")).toArray(), "", Books.class.getName());
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
	public List<Books> getPagingList(Map<String, Object> condition, String sort, Integer pageCount, Integer page) throws Exception {
		List<Books> list = null;
		String hql = " from Books b ";
		Map<String, Object> t = this.getWhere(condition);
		String property = "id,orderNo,title,author,publishDate,isbn,price,onPrice,offPrice,publisher,location,edition,page,frame,format,sheet,cover,url,cunZai,category";
		String field = "b.id,b.orderNo,b.title,b.author,b.publishDate,b.isbn,b.price,b.onPrice,b.offPrice,b.publisher,b.location,b.edition,b.page,b.frame,b.format,b.sheet,b.cover,b.url,b.cunZai,b.category";
		try {
			list = super.hibernateDao.getListByHql(property, field, hql + t.get("where").toString(), ((List<Object>) t.get("condition")).toArray(), sort, Books.class.getName(), pageCount, page * pageCount);

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
	@SuppressWarnings("unchecked")
	public List<Books> getList(Map<String, Object> condition, String sort) throws Exception {
		List<Books> list = null;
		String hql = " from Books b ";
		Map<String, Object> t = this.getWhere(condition);
		String property = "id,orderNo,title,author,publishDate,isbn,price,onPrice,offPrice,publisher,location,edition,page,frame,format,sheet,cover,url,cunZai,introduction";
		String field = "b.id,b.orderNo,b.title,b.author,b.publishDate,b.isbn,b.price,b.onPrice,b.offPrice,b.publisher,b.location,b.edition,b.page,b.frame,b.format,b.sheet,b.cover,b.url,b.cunZai,b.introduction";
		try {
			list = super.hibernateDao.getListByHql(property, field, hql + t.get("where").toString(), ((List<Object>) t.get("condition")).toArray(), sort, Books.class.getName());

		} catch (Exception e) {
			throw e;
		}
		return list;
	}
}
