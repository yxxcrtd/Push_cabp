package cn.digitalpublishing.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.daxtech.framework.exception.CcsException;
import cn.digitalpublishing.po.FtpConfigure;
import cn.digitalpublishing.service.FtpService;

public class FtpServiceImpl extends BaseServiceImpl implements FtpService {

	/*************************************FTP Configure**********************************************/
	
	public FtpConfigure getFtp(String id) throws Exception { 
		// TODO Auto-generated method stub
		FtpConfigure obj = null;
		try{
			obj = (FtpConfigure)this.daoFacade.getFtpConfigureDao().get(FtpConfigure.class.getName(), id);
		}catch(Exception e){
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt()	: "Pages.books.chu.cuo", e);//获取FTP配置信息失败�?
		}
		return obj;
	}
	
	public Integer getFtpCount(Map<String, Object> condition) throws Exception {
		// TODO Auto-generated method stub
		Integer num = 0;
		try{
			num = this.daoFacade.getFtpConfigureDao().getCount(condition);
		}catch(Exception e){
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt()	: "Pages.books.chu.cuo", e);//获取FTP配置信息总数失败�?
		}
		return num;
	}

	public void addFtp(FtpConfigure obj) throws Exception {
		// TODO Auto-generated method stub
		try{
			Map<String,Object> condition = new HashMap<String,Object>();
			condition.put("code",obj.getCode());
			if(this.daoFacade.getFtpConfigureDao().getCount(condition)>0){
				throw new CcsException("ftp.info.identical.add.error");//存在相同编号的FTP配置信息，禁止新�?
			}
			this.daoFacade.getFtpConfigureDao().insert(obj);
		}catch(Exception e){
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt():"Pages.books.chu.cuo", e);//新增FTP配置信息失败�?
		}
		
	}
	
	public void updateFtp(FtpConfigure obj, String id, String[] properties)throws Exception {
		// TODO Auto-generated method stub
		try{
			Map<String,Object> condition = new HashMap<String,Object>();
			condition.put("code",obj.getCode());
			condition.put("uniqueId",id);
			if(this.daoFacade.getFtpConfigureDao().getCount(condition)>0){
				System.out.println(11111);
				throw new CcsException("ftp.info.identical.update.error");//存在相同编号的FTP配置信息，禁止更�?
			}
			this.daoFacade.getFtpConfigureDao().update(obj, FtpConfigure.class.getName(), id, properties);
		}catch(Exception e){
			e.printStackTrace();
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt():"Pages.books.chu.cuo", e);//更新FTP配置信息失败�?
		}
	}

	public List<FtpConfigure> getFtpPagingList(Map<String, Object> condition,String sort, Integer pageCount, Integer page) throws Exception {
		// TODO Auto-generated method stub
		List<FtpConfigure> list = null;
		try{
			list = this.daoFacade.getFtpConfigureDao().getPagingList(condition, sort, pageCount, page);
		}catch(Exception e){
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt():"Pages.books.chu.cuo", e);//获取FTP配置信息分页列表失败�?
		}
		return list;
	}
	
	public void deleteFtp(String id) throws Exception {
		// TODO Auto-generated method stub
		try{
			Map<String,Object> condition = new HashMap<String,Object>();
			condition.put("configureId",id);
			if(((FtpConfigure)this.daoFacade.getFtpConfigureDao().get(FtpConfigure.class.getName(), id)).getDirectories().size()>0){
				throw new CcsException("ftp.info.exist.directory.ban.delete");
			}
			this.daoFacade.getFtpConfigureDao().delete(FtpConfigure.class.getName(), id);
		}catch(Exception e){
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt()	: "Pages.books.chu.cuo", e);//删除FTP配置信息失败�?
		}
	}

	@Override
	public List<FtpConfigure> getftpNameAndId(Map<String,Object> condition,String order) throws Exception {
		// TODO Auto-generated method stub
		List<FtpConfigure> list = null;
		try{
			list = this.daoFacade.getFtpConfigureDao().getList(condition,order);
		}catch(Exception e){
			e.printStackTrace();
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt():"Pages.books.chu.cuo", e);//获取FTP配置信息分页列表失败�?
		}
		return list;
	}
	
	@Override
	public List<FtpConfigure> getFtpUnbindTemplate(String order) throws Exception {
		// TODO Auto-generated method stub
		List<FtpConfigure> list = null;
		try{
			list = this.daoFacade.getFtpConfigureDao().getFtpUnbindTemplate(order);
		}catch(Exception e){
			e.printStackTrace();
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt():"Pages.books.chu.cuo", e);//获取FTP配置信息分页列表失败�?
		}
		return list;
	}
	
	@Override
	public List<FtpConfigure> getList(Map<String, Object> condition,
			String sort) throws CcsException {
		// TODO Auto-generated method stub
		List<FtpConfigure> list = null;
		try {
			list = this.daoFacade.getFtpConfigureDao().getList(condition, sort);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Pages.books.chu.cuo", e);
		}
		return list;
	}
}
