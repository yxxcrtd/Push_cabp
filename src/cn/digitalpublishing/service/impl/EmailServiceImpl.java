package cn.digitalpublishing.service.impl;

import java.util.List;
import java.util.Map;

import cn.com.daxtech.framework.exception.CcsException;
import cn.digitalpublishing.po.Email;
import cn.digitalpublishing.service.EmailService;

public class EmailServiceImpl extends BaseServiceImpl implements EmailService {

	@Override
	public Integer getEmailCount(Map<String, Object> condition) throws Exception {
		Integer num = 0;
		try{
			num = this.daoFacade.getEmailDao().getCount(condition);
		}catch(Exception e){
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt()	: "Pages.books.chu.cuo", e);
		}
		return num;
	}

	@Override
	public List<Email> getEmailPagingList(Map<String, Object> condition, String sort, Integer pageCount, Integer page) throws Exception {
		List<Email> list = null;
		try{
			list = this.daoFacade.getEmailDao().getPagingList(condition, sort, pageCount, page);
		}catch(Exception e){
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt():"Pages.books.chu.cuo", e);
		}
		return list;
	}
	
	
	

	@Override
	public void deleteEmail(String id) throws Exception {
		try {
			daoFacade.getEmailDao().delete(Email.class.getName(), id);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "Pages.books.chu.cuo", e);
		}
	}

	@Override
	public void save(Email email) throws Exception {
		try {
			this.daoFacade.getEmailDao().insert(email);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "Pages.books.chu.cuo", e);
		}
	}

	
}
