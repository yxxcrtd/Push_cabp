package cn.digitalpublishing.service;

import java.util.List;
import java.util.Map;

import cn.digitalpublishing.resource.Onix;

/**
 * Onix Service
 * 
 * @author YangXinXin
 */
public interface OnixService extends BaseService {

	/**
	 * 获取记录总数
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	Integer getAllOnixCount(Map<String, Object> condition) throws Exception;

	/**
	 * 获取分页列表
	 * 
	 * @param condition
	 * @param sort
	 * @param pageCount
	 * @param page
	 * @return
	 * @throws Exception
	 */
	List<Onix> getOnixPagingList(Map<String, Object> condition, String sort, Integer pageCount, Integer page) throws Exception;

	/**
	 * 根据ID查找对象
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Onix findById(String id) throws Exception;
	
	/**
	 * 保存
	 * 
	 * @param onix
	 * @throws Exception
	 */
	void save(Onix onix) throws Exception;
	
	/**
	 * 修改
	 * 
	 * @throws Exception
	 */
	void update(Onix obj, String id, String[] properties) throws Exception;
	
	/**
	 * 删除
	 * 
	 * @param id
	 * @throws Exception
	 */
	void delete(String id) throws Exception;
	
}
