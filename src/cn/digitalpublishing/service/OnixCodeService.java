package cn.digitalpublishing.service;

import java.util.List;
import java.util.Map;

import cn.digitalpublishing.resource.OnixCode;

public interface OnixCodeService extends BaseService {

	/**
	 * 获取记录总数
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	Integer getAllOnixCodeCount(Map<String, Object> condition) throws Exception;

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
	List<OnixCode> getOnixCodePagingList(Map<String, Object> condition, String sort, Integer pageCount, Integer page) throws Exception;

	/**
	 * 根据ID查找对象
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	OnixCode findById(String id) throws Exception;
	
	/**
	 * 保存
	 * 
	 * @param onixCode
	 * @throws Exception
	 */
	void save(OnixCode onixCode) throws Exception;
	
	/**
	 * 修改
	 * 
	 * @throws Exception
	 */
	void update(OnixCode obj, String id, String[] properties) throws Exception;
	
	/**
	 * 删除
	 * 
	 * @param id
	 * @throws Exception
	 */
	void delete(String id) throws Exception;
	
}
