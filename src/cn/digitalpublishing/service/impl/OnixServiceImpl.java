package cn.digitalpublishing.service.impl;

import java.util.List;
import java.util.Map;

import cn.com.daxtech.framework.exception.CcsException;
import cn.digitalpublishing.resource.Onix;
import cn.digitalpublishing.service.OnixService;

/**
 * Onix Service Implement
 * 
 * @author YangXinXin
 */
public class OnixServiceImpl extends BaseServiceImpl implements OnixService {

	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.service.OnixService#getAllOnixCount(java.util.Map)
	 */
	@Override
	public Integer getAllOnixCount(Map<String, Object> condition) throws Exception {
		Integer num = 0;
		try {
			num = daoFacade.getOnixDao().getAllOnixCount(condition);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "Onix.Info.Count.Error", e);
		}
		return num;
	}

	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.service.OnixService#getOnixPagingList(java.util.Map, java.lang.String, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<Onix> getOnixPagingList(Map<String, Object> condition, String sort, Integer pageCount, Integer page) throws Exception {
		List<Onix> list = null;
		try {
			list = daoFacade.getOnixDao().getOnixPagingList(condition, sort, pageCount, page);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "Onix.Info.List.Error", e);
		}
		return list;
	}
	
	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.service.OnixService#findById(java.lang.String)
	 */
	@Override
	public Onix findById(String id) throws Exception {
		Onix onix = null;
		try {
			onix = (Onix) daoFacade.getOnixDao().get(Onix.class.getName(), id);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "Onix.Info.FindById.Error", e);
		}
		return onix;
	}
	
	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.service.OnixService#save(cn.digitalpublishing.po.Onix)
	 */
	@Override
	public void save(Onix onix) throws Exception {
		try {
			daoFacade.getOnixDao().insert(onix);
		} catch (Exception e) {
			// 保存失败
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "Onix.Info.Save.Error", e);
		}
	}
	
	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.service.OnixService#update(cn.digitalpublishing.po.Onix, java.lang.String, java.lang.String[])
	 */
	@Override
	public void update(Onix obj, String id, String[] properties) throws Exception {
		try {
			daoFacade.getOnixDao().update(obj, Onix.class.getName(), id, properties);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "Onix.Info.Update.Error", e);
		}
	}

	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.service.OnixService#delete(java.lang.String)
	 */
	@Override
	public void delete(String id) throws Exception {
		try {
			daoFacade.getOnixDao().delete(Onix.class.getName(), id);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "Onix.Info.Delete.Error", e);
		}
	}

}
