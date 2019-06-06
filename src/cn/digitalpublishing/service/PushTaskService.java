package cn.digitalpublishing.service;

import java.util.List;
import java.util.Map;

import cn.com.daxtech.framework.exception.CcsException;
import cn.digitalpublishing.po.Task;

/**
 * Push Service
 * 
 * @author YangXinXin
 */
public interface PushTaskService extends BaseService {

	public List<Task> getPagingList(Map<String, Object> condition, String sort, Integer pageCount, Integer page) throws Exception;

	public int getpushTaskCount(Map<String, Object> condition) throws CcsException;
	
	public void save(Task task) throws Exception;
	
}
