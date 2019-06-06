package cn.digitalpublishing.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.daxtech.framework.util.CollectionsUtil;
import cn.digitalpublishing.dao.CommonDao;
import cn.digitalpublishing.po.TSource;

public class TSourceDao extends CommonDao<TSource,String> {
	
	private Map<String,Object> getWhere(Map<String,Object> map){
		Map<String,Object> table=new HashMap<String,Object>();
		String whereString="";
		List<Object> condition=new ArrayList<Object>();
		int flag=0;
		/**
		 * 1.name
		 */
		if(CollectionsUtil.exist(map, "fileName")&&!"".equals(map.get("fileName"))){
			boolean isPrecise=CollectionsUtil.exist(map, "isPrecise")?Boolean.valueOf(map.get("isPrecise").toString()):true;
			if(isPrecise){
				if(flag==0){
					whereString+=" where a.fileName = ?";
					flag=1;
				}else{
					whereString+=" and a.fileName = ?";
				}
				condition.add(map.get("fileName").toString().trim().toLowerCase());
			}else{
				if(flag==0){
					whereString+=" where lower(a.fileName) like ?";
					flag=1;
				}else{
					whereString+=" and lower(a.fileName) like ?";
				}
				condition.add("%"+map.get("fileName").toString().trim().toLowerCase()+"%");
			}
		}
		
		/**
		 * 2.status
		 */
		if(CollectionsUtil.exist(map, "status")&&map.get("status")!=null){
			if(flag==0){
				whereString+=" where a.status = ?";
				flag=1;
			}else{
				whereString+=" and a.status = ?";
			}
			condition.add(map.get("status"));
		}
		
		/**
		 * 3.type
		 */
		if(CollectionsUtil.exist(map, "type")&&!"".equals(map.get("type"))){
			if(flag==0){
				whereString+=" where a.type = ?";
				flag=1;
			}else{
				whereString+=" and a.type = ?";
			}
			condition.add(map.get("type"));
		}
		
		/**
		 * 4.ftpFileDir
		 */
		if(CollectionsUtil.exist(map, "ftpFileDir")&&!"".equals(map.get("ftpFileDir"))){
			if(flag==0){
				whereString+=" where lower(a.ftpFileDir) = ?";
				flag=1;
			}else{
				whereString+=" and lower(a.ftpFileDir) = ?";
			}
			condition.add(map.get("ftpFileDir").toString().trim().toLowerCase());
		}
		
		/**
		 * 5.ftpcode
		 */
		if(CollectionsUtil.exist(map, "ftpcode")&&!"".equals(map.get("ftpcode"))){
			if(flag==0){
				whereString+=" where lower(a.ftpcode) = ?";
				flag=1;
			}else{
				whereString+=" and lower(a.ftpcode) = ?";
			}
			condition.add(map.get("ftpcode").toString().trim().toLowerCase());
		}
		
		table.put("where",whereString);
		table.put("condition", condition);
		return table;
	}
	
	/**
	 * 获取列表
	 * @param condition
	 * @param sort
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<TSource> getList(Map<String,Object> condition,String sort)throws Exception{
		this.getWhere(condition);
		List<TSource> list=null;
		String hql=" from TSource a ";
		Map<String,Object> t=this.getWhere(condition);
		String property="id,fileName,filePath,status,ftpcode";
		String field="a.id,a.fileName,a.filePath,a.status,a.ftpcode";
		try{
			list=super.hibernateDao.getListByHql(property,field, hql+t.get("where").toString(), ((List<Object>)t.get("condition")).toArray(),sort, TSource.class.getName());
		}catch(Exception e){
			throw e;
		}
		return list;
	}
	/**
	 * 获取分页列表
	 * @param condition
	 * @param sort
	 * @param pageCount
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<TSource> getPagingList(Map<String,Object> condition,String sort,Integer pageCount,Integer page)throws Exception{
		List<TSource> list=null;
		String hql=" from TSource a";
		Map<String,Object> t=this.getWhere(condition);
		String property="id,fileName,filePath,status,ftpcode,ftpFileDir,updateDate";
		String field="a.id,a.fileName,a.filePath,a.status,a.ftpcode,a.ftpFileDir,a.updateDate";
		try{
			list=super.hibernateDao.getListByHql(property,field, hql+t.get("where").toString(), ((List<Object>)t.get("condition")).toArray(),sort, TSource.class.getName(),pageCount,page*pageCount);
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
		List<TSource> list=null;
		Map<String,Object> t=this.getWhere(condition);
		String hql=" from TSource a left join a.source b left join a.platform c ";
		try{
			list=this.hibernateDao.getListByHql("id","cast(count(*) as string)", hql+t.get("where").toString(), ((List<Object>)t.get("condition")).toArray(),"", TSource.class.getName());
		}catch(Exception e){
			throw e;
		}
		return list==null?0:Integer.valueOf(list.get(0).getId());
	}
	
	
	@SuppressWarnings("unchecked")
	public TSource getTSource(Map<String,Object> condition)throws Exception{
		TSource ts=new TSource();
		String hql=" from TSource a left join a.source b ";
		Map<String,Object> t=this.getWhere(condition);
		String property="id,fileName,filePath,status,zipPath,type,updateDate,source,source.code";
		String field="a.id,a.fileName,a.filePath,a.status,a.zipPath,a.type,a.updateDate,a.source,b.code";
		try{
			List<TSource> list =super.hibernateDao.getListByHql(property,field, hql+t.get("where").toString(), ((List<Object>)t.get("condition")).toArray(),"", TSource.class.getName());
			if(list.size() != 0){
				ts=list.get(0);
			}
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return ts;
	}

}
