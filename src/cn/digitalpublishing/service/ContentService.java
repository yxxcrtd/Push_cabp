package cn.digitalpublishing.service;

import java.util.List;

import cn.digitalpublishing.po.Content;


/**
 * Content Service
 * 
 * @author YangXinXin
 */
public interface ContentService extends BaseService {
	
	List<Content> getContentListByProductId(String productId) throws Exception;
	
}
