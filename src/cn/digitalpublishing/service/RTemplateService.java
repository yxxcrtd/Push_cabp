package cn.digitalpublishing.service;

import java.util.List;
import java.util.Map;

import cn.com.daxtech.framework.exception.CcsException;
import cn.digitalpublishing.po.RTemplate;

/**
 * Rtemplate Service
 * 
 * @author cuixian
 */
public interface RTemplateService extends BaseService {
	
	/**
	 * 获取记录总数
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public Integer getAllTemplateCount(Map<String, Object> condition) throws Exception;

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
	public List<RTemplate> getTemplatePagingList(Map<String, Object> condition, String sort, Integer pageCount, Integer page) throws Exception;

	/**
	 * 根据ID查找对象
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public RTemplate findById(String id) throws Exception;
	
	/**
	 * 保存
	 * 
	 * @param RTemplate
	 * @throws Exception
	 */
	public void save(RTemplate rtemplate) throws Exception;
	
	/**
	 * 修改
	 * 
	 * @throws Exception
	 */
	public void update(RTemplate obj, String id, String[] properties) throws Exception;
	
	/**
	 * 删除
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void delete(String id) throws Exception;

	/**
	 * 根据用户模板ID获取模板映射列表
	 * 
	 * @return
	 * @throws Exception
	 */
	List<RTemplate> getRTemplateListByUserTemplateId(Map<String, Object> condition, String sort) throws Exception;
	
	/**
	 * 根据编辑页面提交的值判断排重
	 * 
	 * @return
	 * @throws Exception
	 */	
	public List<RTemplate> getRTemplateListById(Map<String,Object> condition, String sort) throws CcsException;
}
