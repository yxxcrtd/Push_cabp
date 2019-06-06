package cn.digitalpublishing.service.impl;

import java.util.List;
import java.util.Map;

import cn.com.daxtech.framework.exception.CcsException;
import cn.digitalpublishing.po.RTemplate;
import cn.digitalpublishing.service.RTemplateService;

/**
 * Rtemplate Service Implement
 * 
 * @author YangXinXin
 */
public class RTemplateServiceImpl extends BaseServiceImpl implements RTemplateService {

	@Override
	public Integer getAllTemplateCount(Map<String, Object> condition)
			throws Exception {
		// TODO Auto-generated method stub
		Integer num = 0;
		try {
			num = daoFacade.getRtemplateDao().getAllTemplateCount(condition);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "Pages.books.chu.cuo", e);
		}
		return num;
	}

	@Override
	public List<RTemplate> getTemplatePagingList(Map<String, Object> condition,
			String sort, Integer pageCount, Integer page) throws Exception {
		// TODO Auto-generated method stub
		List<RTemplate> list = null;
		try {
			list = daoFacade.getRtemplateDao().getTemplatePagingList(condition, sort, pageCount, page);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "Pages.books.chu.cuo", e);
		}
		return list;
	}

	@Override
	public RTemplate findById(String id) throws Exception {
		// TODO Auto-generated method stub
		RTemplate rtemplate = null;
		try {
			rtemplate = (RTemplate) daoFacade.getRtemplateDao().get(RTemplate.class.getName(), id);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "Pages.books.chu.cuo", e);
		}
		return rtemplate;
	}

	@Override
	public void save(RTemplate rtemplate) throws Exception {
		// TODO Auto-generated method stub
		try {
			daoFacade.getRtemplateDao().insert(rtemplate);
		} catch (Exception e) {
			// 保存失败
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "Pages.books.chu.cuo", e);
		}
	}

	@Override
	public void update(RTemplate obj, String id, String[] properties)
			throws Exception {
		// TODO Auto-generated method stub
		try {
			daoFacade.getRtemplateDao().update(obj, RTemplate.class.getName(), id, properties);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "Pages.books.chu.cuo", e);
		}
	}

	@Override
	public void delete(String id) throws Exception {
		// TODO Auto-generated method stub
		try {
			daoFacade.getRtemplateDao().delete(RTemplate.class.getName(), id);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "Pages.books.chu.cuo", e);
		}
	}

	@Override
	public List<RTemplate> getRTemplateListById(Map<String, Object> condition,
			String sort) throws CcsException {
		// TODO Auto-generated method stub
		List<RTemplate> list = null;
		try {
			list = this.daoFacade.getRtemplateDao().getRTemplateListById(condition, sort);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Pages.books.chu.cuo", e);
		}
		return list;
	}

	@Override
	public List<RTemplate> getRTemplateListByUserTemplateId(Map<String, Object> condition, String sort) throws Exception {
		List<RTemplate> list = null;
		try {
			list = this.daoFacade.getRtemplateDao().getRTemplateListByUserTemplateId(condition, sort);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "Pages.books.chu.cuo", e);
		}
		return list;
	}

}
