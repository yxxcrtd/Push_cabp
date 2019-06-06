package cn.digitalpublishing.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import cn.digitalpublishing.po.OOrderDetail;

/**
 * Mapping Service
 * 
 * @author
 */
public interface OrderDetailService extends BaseService {
	
	public Integer getOrderCount(Map<String,Object> condition)throws Exception;
	
	public List<OOrderDetail> getOrderPagingList(Map<String,Object> condition,String sort,Integer pageCount,Integer page)throws Exception;
	
	public OOrderDetail getOrder(String id) throws Exception;
	
	public void upload(InputStream inputStream) throws Exception ;
	
	public void updateOrder(OOrderDetail obj,String id,String[] properties)throws Exception;
	
	public void deleteOrder(String id)throws Exception;
}
