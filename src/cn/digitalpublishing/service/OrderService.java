package cn.digitalpublishing.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import cn.digitalpublishing.po.OOrder;

public interface OrderService extends BaseService {
	
	public Integer getOrderCount(Map<String,Object> condition)throws Exception;
	
	public List<OOrder> getOrderPagingList(Map<String,Object> condition,String sort,Integer pageCount,Integer page)throws Exception;
	
	public OOrder getOrder(String id) throws Exception;
	
	public void upload(InputStream inputStream) throws Exception ;
	
	public void deleteOrder(String id)throws Exception;
	
	public void updateOrder(OOrder obj,String id,String[] properties)throws Exception;
}
