package cn.digitalpublishing.service;

import java.util.List;
import java.util.Map;

import cn.digitalpublishing.po.UserTemplate;
import cn.digitalpublishing.po.UserTemplateNode;

public interface UserTemplateNodeService extends BaseService {
	
	public void analysisTemplateAddNode(UserTemplate obj)throws Exception;

	List<UserTemplateNode> getUserTemplateNodesPagingList(Map<String, Object> condition, String sort, Integer pageCount, Integer page) throws Exception;
	
	public Integer getTemplateNodeCount(Map<String, Object> condition) throws Exception;
	
	public List<UserTemplateNode> getTemplateNodeListByTarget(Map<String, Object> condition, String sort,Integer pageCount, Integer page) throws Exception;
	
	public List<UserTemplateNode> getTemplateNodeListByUser(Map<String,Object> condition, String sort) throws  Exception;
	
	public List<UserTemplateNode> getTemplateNodeListByUserTemplateId(Map<String,Object> condition, String sort) throws  Exception;

	public UserTemplateNode getbyid(String string) throws Exception;
	
}
