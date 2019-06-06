package cn.digitalpublishing.service.impl;

import java.util.List;
import java.util.Map;

import cn.com.daxtech.framework.exception.CcsException;
import cn.digitalpublishing.resource.OnixCode;
import cn.digitalpublishing.service.OnixCodeService;

/**
 * OnixCode Service Implement
 * 
 * @author YangXinXin
 */
public class OnixCodeServiceImpl extends BaseServiceImpl implements OnixCodeService {

	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.service.OnixCodeService#getAllOnixCodeCount(java.util.Map)
	 */
	@Override
	public Integer getAllOnixCodeCount(Map<String, Object> condition) throws Exception {
		Integer num = 0;
		try {
			num = daoFacade.getOnixCodeDao().getAllOnixCodeCount(condition);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "OnixCode.Info.Count.Error", e);
		}
		return num;
	}

	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.service.OnixCodeService#getOnixCodePagingList(java.util.Map, java.lang.String, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<OnixCode> getOnixCodePagingList(Map<String, Object> condition, String sort, Integer pageCount, Integer page) throws Exception {
		List<OnixCode> list = null;
		try {
			list = daoFacade.getOnixCodeDao().getOnixCodePagingList(condition, sort, pageCount, page);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "OnixCode.Info.List.Error", e);
		}
		return list;
	}
	
	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.service.OnixCodeService#findById(java.lang.String)
	 */
	@Override
	public OnixCode findById(String id) throws Exception {
		OnixCode onixCode = null;
		try {
			onixCode = (OnixCode) daoFacade.getOnixCodeDao().get(OnixCode.class.getName(), id);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "OnixCode.Info.FindById.Error", e);
		}
		return onixCode;
	}
	
	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.service.OnixCodeService#save(cn.digitalpublishing.po.OnixCode)
	 */
	@Override
	public void save(OnixCode onixCode) throws Exception {
		try {
			daoFacade.getOnixDao().insert(onixCode);
		} catch (Exception e) {
			// 保存失败
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "OnixCode.Info.Save.Error", e);
		}
	}
	
	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.service.OnixCodeService#update(cn.digitalpublishing.po.OnixCode, java.lang.String, java.lang.String[])
	 */
	@Override
	public void update(OnixCode obj, String id, String[] properties) throws Exception {
		try {
			daoFacade.getOnixCodeDao().update(obj, OnixCode.class.getName(), id, properties);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "OnixCode.Info.Update.Error", e);
		}
	}

	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.service.OnixCodeService#delete(java.lang.String)
	 */
	@Override
	public void delete(String id) throws Exception {
		try {
			daoFacade.getOnixCodeDao().delete(OnixCode.class.getName(), id);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "OnixCode.Info.Delete.Error", e);
		}
	}

}
