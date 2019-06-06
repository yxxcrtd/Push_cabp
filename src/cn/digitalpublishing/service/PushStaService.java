package cn.digitalpublishing.service;

import java.util.List;
import java.util.Map;

import cn.com.daxtech.framework.exception.CcsException;
import cn.digitalpublishing.po.PushSta;

public interface PushStaService extends BaseService {
	
	
	public void save(PushSta pushSta) throws Exception;
	public PushSta getPushSta(String taskId) throws Exception;
	
	public void updatePushSta(PushSta obj,String id,String[] properties)throws Exception;
	
	PushSta getList(Map<String, Object> condition) throws Exception;
	
	public int getpushStaCount(Map<String, Object> condition) throws CcsException;
	
	public List<PushSta> getPagingList(Map<String, Object> condition, String sort, Integer pageCount, Integer page) throws Exception;
	
}
