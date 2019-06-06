package cn.digitalpublishing.service;

import java.util.List;
import java.util.Map;

import cn.com.daxtech.framework.exception.CcsException;
import cn.digitalpublishing.po.TProduct;
import cn.digitalpublishing.po.TSource;
import cn.digitalpublishing.po.Task;
import cn.digitalpublishing.po.UserTemplate;


public interface ThreadService extends BaseService {

	public List<TSource> getSouceDateList(Map<String, Object> condition, String sort)
			throws Exception;
	
	
	public List<UserTemplate> getUserTemplate(Map<String, Object> conditionftpCode)
			throws Exception;


	public void splitMetadata(TSource tSource, UserTemplate template) throws CcsException;


	public List<TProduct> getTproductList(Map<String, Object> map,String sort) throws CcsException;


	public void downLoadFile(List<TProduct>list)throws CcsException;


	public List<Task> getTaskPagingList(Map<String, Object> condition,String sort, Integer pageCount, Integer page) 
			throws CcsException;


	public void sendFile(Task task)throws CcsException;
	
	
	public void update(Task task, String id, String[] properties)throws CcsException;
	
	public void updateTSource(TSource tSource, String id, String[] properties)throws CcsException;
}
