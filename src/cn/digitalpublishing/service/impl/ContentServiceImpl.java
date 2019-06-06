package cn.digitalpublishing.service.impl;

import java.util.List;

import cn.com.daxtech.framework.exception.CcsException;
import cn.digitalpublishing.po.Content;
import cn.digitalpublishing.service.ContentService;

/**
 * Content Service Implement
 * 
 * @author YangXinXin
 */
public class ContentServiceImpl extends BaseServiceImpl implements ContentService {

	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.service.ContentService#getContentListByProductId(java.lang.String)
	 */
	@Override
	public List<Content> getContentListByProductId(String productId) throws Exception {
		List<Content> list = null;
		try {
			list = this.daoFacade.getContentDao().getContentListByProductId(productId);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "Pages.books.chu.cuo", e);
		}
		return list;
	}

}
