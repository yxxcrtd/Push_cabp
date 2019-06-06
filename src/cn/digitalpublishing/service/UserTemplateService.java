package cn.digitalpublishing.service;

import java.util.List;
import java.util.Map;

import cn.com.daxtech.framework.exception.CcsException;
import cn.digitalpublishing.po.UserTemplate;

public interface UserTemplateService extends BaseService {
	
	/**
	 * 获取FTP配置
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<UserTemplate> getUserTemplatePagingList(Map<String,Object> condition,String sort,Integer pageCount,Integer page)throws Exception;
	/**
	 * 获取总数
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public Integer getDataCount(Map<String,Object> condition)throws Exception;
	
	/**
	 * 修改用户模板配置
	 * @param obj
	 * @param id
	 * @param properties
	 * @throws Exception
	 */
	public void updateUserTemplate(UserTemplate obj,String id,String[] properties)throws Exception;
	
	/**
	 * 新增用户模板
	 * @param obj
	 * @throws Exception
	 */
	public void addUserTemplate(UserTemplate obj)throws Exception;
	
	/**
	 * 删除用户模板配置
	 * @param id
	 * @param path
	 * @throws Exception
	 */
	public void deleteUserTemplate(String id)throws Exception;
	
	/**
	 * 根据ID查找对象
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public UserTemplate findById(String id) throws Exception;
	
	public UserTemplate getUserTemplate(String string) throws CcsException;
	
	public List<UserTemplate> getUserTemplateListByFlag(Map<String,Object> condition, String sort) throws CcsException;
	
	public UserTemplate getUserTemplateAndNodes(String id) throws Exception;
	
	public List<UserTemplate> getUserTemplateListByFtpcod(Map<String,Object> condition)throws Exception;
}
