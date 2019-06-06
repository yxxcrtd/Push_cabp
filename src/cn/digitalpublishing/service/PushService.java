package cn.digitalpublishing.service;

import java.util.List;
import java.util.Map;

import cn.digitalpublishing.po.TSource;
import cn.digitalpublishing.po.UserTemplate;

/**
 * Push Service
 * 
 * @author YangXinXin
 */
public interface PushService extends BaseService {

	/**
	 * 获取TSource中状态是3（可以推送的）的记录总数
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	Integer getTSourceCountByStatus(Map<String, Object> condition) throws Exception;

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
	List<TSource> getTSourcePagingListByStatus(Map<String, Object> condition, String sort, Integer pageCount, Integer page) throws Exception;
	
	public UserTemplate getList(Map<String, Object> condition, String sort) throws Exception;

}
