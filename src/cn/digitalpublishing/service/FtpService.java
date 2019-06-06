package cn.digitalpublishing.service;

import java.util.List;
import java.util.Map;

import cn.com.daxtech.framework.exception.CcsException;
import cn.digitalpublishing.po.FtpConfigure;

public interface FtpService extends BaseService{
	
	/**
	 * 获取FTP配置
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public FtpConfigure getFtp(String id)throws Exception;
	/**
	 * 获取分页列表
	 * @param condition
	 * @param sort
	 * @param pageCount
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<FtpConfigure> getFtpPagingList(Map<String,Object> condition,String sort,Integer pageCount,Integer page)throws Exception;
	/**
	 * 获取总数
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public Integer getFtpCount(Map<String,Object> condition)throws Exception;
	
	/**
	 * 更新FTP配置
	 * @param obj
	 * @param id
	 * @param properties
	 * @throws Exception
	 */
	public void updateFtp(FtpConfigure obj,String id,String[] properties)throws Exception;
	
	/**
	 * 新增FTP配置
	 * @param obj
	 * @throws Exception
	 */
	public void addFtp(FtpConfigure obj)throws Exception;
	
	/**
	 * 删除FTP配置
	 * @param id
	 * @param path
	 * @throws Exception
	 */
	public void deleteFtp(String id)throws Exception;
	
	/**
	 * 取得FTP配置 的name 和id
	 * @throws Exception
	 */
	
	public List<FtpConfigure> getftpNameAndId(Map<String,Object> condition,String older) throws Exception;
	/**
	 * 查询未绑定用户模板的ftp对象集合
	 * @param order 排序规则
	 * @return ftp对象列表
	 */
	public List<FtpConfigure> getFtpUnbindTemplate(String order)throws Exception;
	
	/**
	 * 根据编辑页面提交的值判断排重
	 * 
	 * @return
	 * @throws Exception
	 */	
	public List<FtpConfigure> getList(Map<String,Object> condition, String sort) throws CcsException;
}
