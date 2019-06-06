package cn.digitalpublishing.service.impl;

import java.util.List;
import java.util.Map;

import cn.com.daxtech.framework.exception.CcsException;
import cn.digitalpublishing.po.Mapping;
import cn.digitalpublishing.service.MappingService;

/**
 * Mapping Service Implement
 * 
 * @author YangXinXin
 */
public class MappingServiceImpl extends BaseServiceImpl implements MappingService {
	
	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.service.MappingService#save(cn.digitalpublishing.po.Mapping)
	 */
	@Override
	public void save(Mapping mapping) throws Exception {
		try {
			daoFacade.getMappingDao().insert(mapping);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "Pages.books.chu.cuo", e);
		}
	}
	
	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.service.MappingService#update(cn.digitalpublishing.po.Mapping, java.lang.String, java.lang.String[])
	 */
	@Override
	public void update(Mapping mapping, String id, String[] properties) throws Exception {
		try {
			daoFacade.getMappingDao().update(mapping, Mapping.class.getName(), id, properties);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "Pages.books.chu.cuo", e);
		}
	}
	
	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.service.MappingService#findByTargetTemplateId(java.util.Map)
	 */
	@Override
	public List<Mapping> findByTargetTemplateId(Map<String, Object> condition) throws Exception {
		List<Mapping> list = null;
		try {
			list = this.daoFacade.getMappingDao().findByTargetTemplateId(condition);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "Pages.books.chu.cuo", e);
		}
		return list;
	}
	
	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.service.MappingService#findMappingListByUserTemplateIdAndTargetTemplateId(java.util.Map)
	 */
	@Override
	public List<Mapping> findMappingListByUserTemplateIdAndTargetTemplateId(Map<String, Object> condition) throws Exception {
		List<Mapping> list = null;
		try {
			list = this.daoFacade.getMappingDao().findMappingListByUserTemplateIdAndTargetTemplateId(condition);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "Pages.books.chu.cuo", e);
		}
		return list;
	
	}

	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.service.MappingService#findByTargetTemplateIdAndtargetTemplateNodeId(java.util.Map)
	 */
	@Override
	public Mapping findByTargetTemplateIdAndtargetTemplateNodeId(Map<String, Object> condition) throws Exception {
		Mapping mapping = null;
		try {
			mapping = daoFacade.getMappingDao().findByTargetTemplateIdAndtargetTemplateNodeId(condition);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "Pages.books.chu.cuo", e);
		}
		return mapping;
	}
	
	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.service.MappingService#delete(java.lang.String)
	 */
	@Override
	public void delete(String id) throws Exception {
		try {
			daoFacade.getMappingDao().delete(Mapping.class.getName(), id);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "Pages.books.chu.cuo", e);
		}
	}

}
