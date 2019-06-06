package cn.digitalpublishing.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.daxtech.framework.exception.CcsException;
import cn.digitalpublishing.po.FtpConfigure;
import cn.digitalpublishing.po.FtpDirConfigure;
import cn.digitalpublishing.po.TSource;
import cn.digitalpublishing.service.FtpDirService;
import cn.digitalpublishing.util.FtpUploadTool;

public class FtpDirServiceImpl extends BaseServiceImpl implements FtpDirService {

	
	public FtpDirConfigure getFtpDir(String id) throws Exception {
		// TODO Auto-generated method stub
		FtpDirConfigure obj = null;
		try{
			obj = (FtpDirConfigure)this.daoFacade.getFtpDirConfigureDao().get(FtpDirConfigure.class.getName(), id);
		}catch(Exception e){
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt()	: "Pages.books.chu.cuo", e);//获取FTP配置信息失败！
		}
		return obj;
	}
	
	public Integer getFtpdirCount(Map<String, Object> condition) throws Exception {
		// TODO Auto-generated method stub
		Integer num = 0;
		try{
			num = this.daoFacade.getFtpDirConfigureDao().getCount(condition);
		}catch(Exception e){
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt()	: "Pages.books.chu.cuo", e);//获取FTP配置信息总数失败！
		}
		return num;
	}

	public void addFtpdir(FtpDirConfigure obj) throws Exception {
		// TODO Auto-generated method stub
		try{
			this.daoFacade.getFtpDirConfigureDao().insert(obj);
		}catch(Exception e){
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt():"Pages.books.chu.cuo", e);//新增FTP配置信息失败！
		}
		
	}
	
	public void updateFtpdir(FtpDirConfigure obj, String id, String[] properties)throws Exception {
		// TODO Auto-generated method stub
		try{
			this.daoFacade.getFtpDirConfigureDao().update(obj, FtpDirConfigure.class.getName(), id, properties);
		}catch(Exception e){
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt():"Pages.books.chu.cuo", e);//更新FTP配置信息失败！
		}
	}

	public List<FtpDirConfigure> getFtpdirPagingList(Map<String, Object> condition,String sort, Integer pageCount, Integer page) throws Exception {
		// TODO Auto-generated method stub
		List<FtpDirConfigure> list = null;
		try{
			list = this.daoFacade.getFtpDirConfigureDao().getPagingList(condition, sort, pageCount, page);
		}catch(Exception e){
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt():"Pages.books.chu.cuo", e);//获取FTP配置信息分页列表失败！
		}
		return list;
	}
	
	public void deleteFtpdir(String id) throws Exception {
		// TODO Auto-generated method stub
		try{
			Map<String,Object> condition = new HashMap<String,Object>();
			condition.put("configureId",id);
			this.daoFacade.getFtpDirConfigureDao().delete(FtpDirConfigure.class.getName(), id);
		}catch(Exception e){
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt()	: "Pages.books.chu.cuo", e);//删除FTP配置信息失败！
		}
	}

	@Override
	public List<FtpDirConfigure> getList(Map<String, Object> condition,
			String sort) throws Exception {
		List<FtpDirConfigure> list;
		try{
			list = this.daoFacade.getFtpDirConfigureDao().getList(condition, sort);
		}catch(Exception e){
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt()	: "Pages.books.chu.cuo", e);
		}
		return list;
	}
	
	
	@Override
	public List<String> getFileNames(FtpConfigure obj,
			String removePath)throws Exception {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<String>();
		try{
			if(obj!=null){
				FtpUploadTool tool = new FtpUploadTool(obj.getIp(),obj.getPort(),obj.getUsername(),obj.getPassword());
				List<String> fileNames = tool.getFileNames(removePath);
				//过滤出要下载的文件
				for (String s : fileNames) {
					if(s.toLowerCase().endsWith(".xml")){
						list.add(s);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt()	: "Pages.books.chu.cuo", e);//获取远程文件列表失败！
		}
		return list;
	}

	@Override
	public Map<String, String> notExistFileNames(List<String> fileNames,
			String fptcode, String ftpdir) throws Exception {
		Map<String,String> fileMap = null;
		try{
			if(fileNames!=null&&!fileNames.isEmpty()){
				fileMap = new HashMap<String,String>();
				Map<String,Object> condition = new HashMap<String,Object>();
				//取得tsource中的数据 条件是fptcode 和 ftpdir
				condition.put("ftpcode", fptcode);
				condition.put("ftpFileDir", ftpdir);
				List<TSource> list = this.getSourceList(condition,"");
				boolean isadd = false;
				for(String fileName:fileNames){
					for (TSource tSource : list){
						if(tSource.getFileName().equals(fileName)){
							isadd=true;
							break;
						}
					}
					if(!isadd){ 	
						fileMap.put(fileName, fileName);
					}
					isadd = false;
				}
			}
		}catch (Exception e) {
			throw e;
		}
		System.out.println("fileNames"+fileNames);
		System.out.println(fileMap);
		return fileMap;
	}

	/**
	 * 下载excel文件 并返回 下载文件名字list集合
	 */
	@Override
	public List<List> downloadRemoteFile(FtpConfigure ftpConfigure,
			String localPath, String targetPath,
			Map<String, String> notExistFileNames) throws Exception {
		List<List> listAll = null;
		try{
			if(ftpConfigure!=null){
				FtpUploadTool tool = new FtpUploadTool(ftpConfigure.getIp(),ftpConfigure.getPort(),ftpConfigure.getUsername(),ftpConfigure.getPassword());
				listAll = tool.downloadFile2(targetPath,localPath,notExistFileNames,ftpConfigure);
			}
		}catch(Exception e){
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt()	: "Pages.books.chu.cuo", e);//FTP下载失败！
		}
		return listAll;
	}
	//保存t_source
	@Override
	public void saveTSources(List<TSource> list) throws Exception {
		try{
			for (TSource tSource : list) {
				this.daoFacade.getTSourceDao().insert(tSource);
			}
		}catch (Exception e) {
			e.printStackTrace();
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt()	: "Pages.books.chu.cuo", e);
		}
	}
	
	public List<TSource> getSourceList(Map<String, Object> condition, String sort) throws Exception {
		List<TSource> list=null;
		try {
			list=this.daoFacade.getTSourceDao().getList(condition, sort);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt()	: "Pages.books.chu.cuo", e);
		}
		return list;
	}

	@Override
	public List<FtpDirConfigure> getFtpDirByFtpid(Map<String, Object> condition)
			throws Exception {
		// TODO Auto-generated method stub
		List<FtpDirConfigure> list = null;
		try{
			list = this.daoFacade.getFtpDirConfigureDao().getFtpDirByFtpid(condition);
		}catch(Exception e){
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt():"Pages.books.chu.cuo", e);//获取FTP配置信息分页列表失败！
		}
		return list;
	}

}
