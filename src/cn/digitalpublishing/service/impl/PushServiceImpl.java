package cn.digitalpublishing.service.impl;

import java.util.List;
import java.util.Map;

import cn.com.daxtech.framework.exception.CcsException;
import cn.digitalpublishing.po.PushSta;
import cn.digitalpublishing.po.TSource;
import cn.digitalpublishing.po.UserTemplate;
import cn.digitalpublishing.service.PushService;

/**
 * Push Service Implement
 * 
 * @author YangXinXin
 */
public class PushServiceImpl extends BaseServiceImpl implements PushService {

	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.service.PushService#getTSourceCountByStatus(java.util.Map)
	 */
	@Override
	public Integer getTSourceCountByStatus(Map<String, Object> condition) throws Exception {
		Integer num = 0;
		try {
			num = this.daoFacade.getPushDao().getTSourceCountByStatus(condition);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "Pages.books.chu.cuo", e);
		}
		return num;
	}

	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.service.PushService#getTSourcePagingListByStatus(java.util.Map, java.lang.String, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<TSource> getTSourcePagingListByStatus(Map<String, Object> condition, String sort, Integer pageCount, Integer page) throws Exception {
		List<TSource> list = null;
		try {
			list = this.daoFacade.getPushDao().getTSourcePagingListByStatus(condition, sort, pageCount, page);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "Pages.books.chu.cuo", e);
		}
		return list;
	}
	
	@Override
	public UserTemplate getList(Map<String,Object> condition,String sort)throws Exception{
			List<UserTemplate> list = null;
			UserTemplate obj=null;
			try{
				list = this.daoFacade.getUserTemplateDao().getList(condition,sort);
				if(list!=null&&list.size()>0){
					obj = list.get(0);
				}
			}catch(Exception e){
				throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt()	: "Pages.books.chu.cuo", e);
			}
			return obj;
	}
}
