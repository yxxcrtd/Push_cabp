package cn.digitalpublishing.service.impl;

import java.util.List;
import java.util.Map;

import cn.com.daxtech.framework.exception.CcsException;
import cn.digitalpublishing.po.UserTemplate;
import cn.digitalpublishing.service.UserTemplateService;

public class UserTemplateServiceImpl extends BaseServiceImpl implements UserTemplateService {

	
	public UserTemplate getFtp(String id) throws Exception {
		// TODO Auto-generated method stub
		UserTemplate obj = null;
		try{
			obj = (UserTemplate)this.daoFacade.getUserTemplateDao().get(UserTemplate.class.getName(), id);
		}catch(Exception e){
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt()	: "Pages.books.chu.cuo", e);//获取FTP配置信息失败�?
		}
		return obj;
	}
	
	public Integer getDataCount(Map<String, Object> condition) throws Exception {
		// TODO Auto-generated method stub
		Integer count = 0;
		try{
			count = this.daoFacade.getUserTemplateDao().getCount(condition);
		}catch(Exception e){
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt()	: "Pages.books.chu.cuo", e);//获取FTP配置信息总数失败�?
		}
		return count;
	}
	@Override
	public void addUserTemplate(UserTemplate obj) throws Exception {
		// TODO Auto-generated method stub
		try{
			this.daoFacade.getUserTemplateDao().insert(obj);
		}catch(Exception e){
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt():"usertemlate.info.add.error", e);//新增用户模板失败
		}
		
	}
	@Override
	public void updateUserTemplate(UserTemplate obj, String id, String[] properties)throws Exception {
		// TODO Auto-generated method stub
		try{
			this.daoFacade.getUserTemplateDao().update(obj, UserTemplate.class.getName(), id, properties);
		}catch(Exception e){
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt():"ftp.info.update.error", e);//更新FTP配置信息失败�?
		}
	}

	public List<UserTemplate> getUserTemplatePagingList(Map<String, Object> condition,String sort, Integer pageCount, Integer page) throws Exception {
		// TODO Auto-generated method stub
		List<UserTemplate> list = null;
		try{
			System.err.println(this.daoFacade.getUserTemplateDao());
			list = this.daoFacade.getUserTemplateDao().getPagingList(condition, sort, pageCount, page);
		}catch(Exception e){
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt():"ftp.info.page.list.error", e);//获取FTP配置信息分页列表失败�?
		}
		return list;
	}
	
	public void deleteUserTemplate(String id) throws Exception {
		// TODO Auto-generated method stub
		try{
			this.daoFacade.getUserTemplateDao().delete(UserTemplate.class.getName(), id);
		}catch(Exception e){
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt()	: "msg.info.delete.error", e);//删除用户模板异常
		}
	}

	@Override
	public UserTemplate getUserTemplate(String id) throws CcsException {
		// TODO Auto-generated method stub
		UserTemplate userTemplate = null;
		try{
			userTemplate=(UserTemplate) this.daoFacade.getUserTemplateDao().get(UserTemplate.class.getName(), id);
			//将ftp的name 设置给 模板对象 在页面显示
			if(userTemplate.getFtpConfigure()!=null){
				userTemplate.setFtpName(userTemplate.getFtpConfigure().getName());
			}
		}catch(Exception e){
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt()	: "Msg.info.select.error", e);//查询异常
		}
		return userTemplate;
	}

	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.service.UserTemplateService#getUserTemplateListByFlag(java.util.Map, java.lang.String)
	 */
	@Override
	public List<UserTemplate> getUserTemplateListByFlag(Map<String,Object> condition, String sort) throws CcsException {
		List<UserTemplate> list = null;
		try {
			list = this.daoFacade.getUserTemplateDao().getUserTemplateListByFlag(condition, sort);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "ftp.info.page.list.error", e);
		}
		return list;
	}
	
	@Override
	public UserTemplate getUserTemplateAndNodes(String id) throws Exception {
		// TODO Auto-generated method stub
		UserTemplate userTemplate = null;
		try{
			userTemplate = (UserTemplate) this.daoFacade.getUserTemplateDao().get(UserTemplate.class.getName(), id);
		}catch(Exception e){
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt():"ftp.info.page.list.error", e);//获取FTP配置信息分页列表失败�?
		}
		return userTemplate;
	}

	@Override
	public List<UserTemplate> getUserTemplateListByFtpcod(Map<String,Object> condition)
			throws Exception {
		List<UserTemplate> list = null;
		try {
			list = this.daoFacade.getUserTemplateDao().getList(condition);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "ftp.info.page.list.error", e);
		}
		return list;
	}

	@Override
	public UserTemplate findById(String id) throws Exception {
		// TODO Auto-generated method stub
		UserTemplate usertemplate = null;
		try {
			usertemplate = (UserTemplate) daoFacade.getUserTemplateDao().get(UserTemplate.class.getName(), id);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "RTemplate.Info.FindById.Error", e);
		}
		return usertemplate;
	}
}
