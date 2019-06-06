package cn.digitalpublishing.service;

import java.util.List;

import cn.digitalpublishing.po.TProduct;

/**
 * Product Service
 * 
 * @author YangXinXin
 */
public interface ProductService extends BaseService {

	/**
	 * 根据 tsourceId 获取 tproduct 列表
	 * 
	 * @param tsourceId
	 * @return
	 * @throws Exception
	 */
	List<TProduct> getProductListBySourceId(String tsourceId) throws Exception;
	
}
