package cn.digitalpublishing.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.daxtech.framework.util.CollectionsUtil;
import cn.digitalpublishing.po.FtpDirConfigure;

public class FtpDirConfigureDao extends CommonDao<FtpDirConfigure,String> {
	
	private Map<String,Object> getWhere(Map<String,Object> map){
		Map<String,Object> table=new HashMap<String,Object>();
		String whereString="";
		List<Object> condition=new ArrayList<Object>();
		int flag=0;
		/**
		 * 1.name
		 */
		if(CollectionsUtil.exist(map, "name")&&!"".equals(map.get("name"))){
			if(flag==0){
				whereString+=" where lower(b.name) like ?";
				flag=1;
			}else{
				whereString+=" and lower(b.name) like ? ";
			}
			condition.add("%"+map.get("name").toString().trim().toLowerCase()+"%");
		}
		
		/**
		 * 2.dirName
		 */
		if(CollectionsUtil.exist(map, "dirName")&&!"".equals(map.get("dirName"))){
			if(flag==0){
				whereString+=" where lower(a.dirName) like ?";
				flag=1;
			}else{
				whereString+=" and lower(a.dirName) like ? ";
			}
			condition.add("%"+map.get("dirName").toString().trim().toLowerCase()+"%");
		}
		
		/**
		 * 3.flag
		 */
		if(CollectionsUtil.exist(map, "flag")&&!"".equals(map.get("flag"))){
			boolean isPrecise=CollectionsUtil.exist(map, "isPrecise")?Boolean.valueOf(map.get("isPrecise").toString()):true;
			if(isPrecise){
				if(flag==0){
					whereString+=" where lower(b.flag) = ?";
					flag=1;
				}else{
					whereString+=" and lower(b.flag) = ?";
				}
				condition.add(Integer.valueOf(map.get("flag").toString()));
			}else{
				if(flag==0){
					whereString+=" where lower(b.flag) = ?";
					flag=1;
				}else{
					whereString+=" and lower(b.flag) = ?";
				}
				condition.add(Integer.valueOf(map.get("flag").toString()));
			}
		}
		
		
		/**
		 * 4.ftpid
		 */
		if(CollectionsUtil.exist(map, "ftpid")&&!"".equals(map.get("ftpid"))){
			if(flag==0){
				whereString+=" where lower(a.ftpConfigure.id) = ?";
				flag=1;
			}else{
				whereString+=" and lower(a.ftpConfigure.id) = ?";
			}
			condition.add(map.get("ftpid").toString().trim().toLowerCase());
		}
		
		table.put("where",whereString);
		table.put("condition", condition);
		return table;
	}
	
	/**
	 * 获取配置列表
	 * @param condition
	 * @param sort
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<FtpDirConfigure> getList(Map<String,Object> condition,String sort)throws Exception{
		List<FtpDirConfigure> list=null;
		String hql=" from FtpDirConfigure a left join a.ftpConfigure b";
		Map<String,Object> t=this.getWhere(condition);
		
		String property="id,ftpConfigure,ftpdir,dirName,description ";
		String field="a.id,a.ftpConfigure,a.ftpdir,a.dirName,a.description ";
		
		try{
			list=super.hibernateDao.getListByHql(property,field, hql+t.get("where").toString(), ((List<Object>)t.get("condition")).toArray(),sort, FtpDirConfigure.class.getName());
		}catch(Exception e){
			throw e;
		}
		return list;
	}
	/**
	 * 获取分页信息
	 * @param condition
	 * @param sort
	 * @param pageCount
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<FtpDirConfigure> getPagingList(Map<String,Object> condition,String sort,Integer pageCount,Integer page)throws Exception{
		List<FtpDirConfigure> list=null;
		String hql=" from FtpDirConfigure a left join a.ftpConfigure b";
		Map<String,Object> t=this.getWhere(condition);
		String property="id,ftpConfigure,ftpdir,dirName,description ";
		String field="a.id,a.ftpConfigure,a.ftpdir,a.dirName,a.description ";
		try{
			list=super.hibernateDao.getListByHql(property,field, hql+t.get("where").toString(), ((List<Object>)t.get("condition")).toArray(),sort, FtpDirConfigure.class.getName(),pageCount,page*pageCount);
		}catch(Exception e){
			throw e;
		}
		return list;
	}
	/**
	 * 获取总数
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Integer getCount(Map<String,Object> condition)throws Exception{
		List<FtpDirConfigure> list=null;
		Map<String,Object> t=this.getWhere(condition);
		String hql=" from FtpDirConfigure a left join a.ftpConfigure b";
		try{
			list=this.hibernateDao.getListByHql("id","cast(count(*) as string)", hql+t.get("where").toString(), ((List<Object>)t.get("condition")).toArray(),"", FtpDirConfigure.class.getName());
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return list==null?0:Integer.valueOf(list.get(0).getId());
	}

	@SuppressWarnings("unchecked")
	public List<FtpDirConfigure> getFtpDirByFtpid(Map<String, Object> condition) throws Exception {
		List<FtpDirConfigure> list=null;
		String hql=" from FtpDirConfigure a";
		Map<String,Object> t=this.getWhere(condition);
		String property="id,ftpdir,dirName ";
		String field="a.id,a.ftpdir,a.dirName ";
		try{
			list=super.hibernateDao.getListByHql(property,field, hql+t.get("where").toString(), ((List<Object>)t.get("condition")).toArray(),"", FtpDirConfigure.class.getName());
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return list;
	}


}
