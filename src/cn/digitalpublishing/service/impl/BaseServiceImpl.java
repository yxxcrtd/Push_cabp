package cn.digitalpublishing.service.impl;

import org.apache.log4j.Logger;

import cn.digitalpublishing.facade.DaoFacade;
import cn.digitalpublishing.service.BaseService;

/**
 * Generci BaseService Implement
 * 
 * @author YangXinXin
 *
 * @param <T>
 * @param <PK>
 */
public class BaseServiceImpl implements BaseService {
	
	public Logger log = Logger.getLogger(this.getClass());
	
	protected DaoFacade daoFacade;

	public DaoFacade getDaoFacade() {
		return daoFacade;
	}

	public void setDaoFacade(DaoFacade daoFacade) {
		this.daoFacade = daoFacade;
	}
}
