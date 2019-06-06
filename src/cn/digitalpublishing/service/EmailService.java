package cn.digitalpublishing.service;

import java.util.List;
import java.util.Map;

import cn.digitalpublishing.po.Email;

public interface EmailService extends BaseService {
	
	public Integer getEmailCount(Map<String,Object> condition)throws Exception;
	
	public List<Email> getEmailPagingList(Map<String,Object> condition,String sort,Integer pageCount,Integer page)throws Exception;
	
	public void save(Email email) throws Exception;

	
	public void deleteEmail(String id)throws Exception;
	
	


}
