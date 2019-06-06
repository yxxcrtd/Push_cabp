package cn.digitalpublishing.service.impl;

import java.util.List;

import cn.com.daxtech.framework.exception.CcsException;
import cn.digitalpublishing.po.TProduct;
import cn.digitalpublishing.service.ProductService;

/**
 * Product Service Implement
 * 
 * @author YangXinXin
 */
public class ProductServiceImpl extends BaseServiceImpl implements ProductService {

	@Override
	public List<TProduct> getProductListBySourceId(String tsourceId) throws Exception {
		List<TProduct> list = null;
		try {
			list = this.daoFacade.getProductDao().getProductListBySourceId(tsourceId);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "Pages.books.chu.cuo", e);
		}
		return list;
	}
	
}

