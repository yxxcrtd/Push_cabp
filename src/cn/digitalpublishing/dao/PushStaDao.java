package cn.digitalpublishing.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.daxtech.framework.util.CollectionsUtil;
import cn.digitalpublishing.po.PushSta;

public class PushStaDao extends CommonDao<PushSta,String> {
	
	private Map<String,Object> getWhere(Map<String,Object> map){
		Map<String,Object> table=new HashMap<String,Object>();
		String whereString="";
		List<Object> condition=new ArrayList<Object>();
		int flag=0;
		/**
		 * 2.tsourceId
		 * 
		 */
		if(CollectionsUtil.exist(map, "fileName")&&map.get("fileName")!=null&&!"".equals(map.get("fileName"))){
			if(flag==0){
				whereString+=" where a.fileName like ?";
				flag=1;
			}else{
				whereString+=" and a.fileName like ?";
			}
			condition.add(map.get("fileName").toString());
		}
		
		if(CollectionsUtil.exist(map, "ftpFileDir")&&map.get("ftpFileDir")!=null&&!"".equals(map.get("ftpFileDir"))){
			if(flag==0){
				whereString+=" where a.ftpFileDir like ?";
				flag=1;
			}else{
				whereString+=" and a.ftpFileDir like ?";
			}
			condition.add(map.get("ftpFileDir").toString());
		}
		
		if(CollectionsUtil.exist(map, "tsourceId")&&map.get("tsourceId")!=null&&!"".equals(map.get("tsourceId"))){
			if(flag==0){
				whereString+=" where b.taskId = ?";
				flag=1;
			}else{
				whereString+=" and b.taskId = ?";
			}
			condition.add(map.get("tsourceId").toString());
		}
		
		if(CollectionsUtil.exist(map, "ftpsDateond")&&!"".equals(map.get("ftpsDateond"))){
			if(flag==0){
				whereString+=" where b.ftpsDate >= ?";
				flag=1;
			}else{
				whereString+=" and b.ftpsDate >= ?";
			}
			condition.add(map.get("ftpsDateond"));
		}
		
		if(CollectionsUtil.exist(map, "ftpsDatetod")&&!"".equals(map.get("ftpsDatetod"))){
			if(flag==0){
				whereString+=" where b.ftpsDate <= ?";
				flag=1;
			}else{
				whereString+=" and b.ftpsDate <= ?";
			}  
			condition.add(map.get("ftpsDatetod"));
		}
		
		
		if(CollectionsUtil.exist(map, "tempflag")&&map.get("tempflag")!=null&&!"".equals(map.get("tempflag"))){
			if(flag==0){
				whereString+=" where a.id = b.taskId";
				flag=1;
			}else{
				whereString+=" and a.id = b.taskId";
			}
		}
		
		
		table.put("where",whereString);
		table.put("condition", condition);
		return table;
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
	public List<PushSta> getList(Map<String,Object> condition,String sort)throws Exception{
		List<PushSta> list=null;
		String hql=" from PushSta b ";
	
		Map<String,Object> t=this.getWhere(condition);
		String property="id,ftpsDate,ftpeDate,caisDate,caieDate";
		String field="b.id,b.ftpsDate,b.ftpeDate,b.caisDate,b.caieDate";
		try{
			list=super.hibernateDao.getListByHql(property,field, hql+t.get("where").toString(), ((List<Object>)t.get("condition")).toArray(),sort, PushSta.class.getName());
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
		List<PushSta> list=null;
		Map<String,Object> t=this.getWhere(condition);
		String hql=" from PushSta b,TSource a ";
		try{
			list=this.hibernateDao.getListByHql("id","cast(count(*) as string)", hql+t.get("where").toString(), ((List<Object>)t.get("condition")).toArray(),"", PushSta.class.getName());
		}catch(Exception e){
			throw e;
		}
		return list==null?0:Integer.valueOf(list.get(0).getId());
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
	public List<PushSta> getPagingList(Map<String,Object> condition,String sort,Integer pageCount,Integer page)throws Exception{
		List<PushSta> list=null;
		String hql=" from PushSta b,TSource a ";
		Map<String,Object> t=this.getWhere(condition);
		String property="ftpFileDir,fileName,id,ftpsDate,ftpeDate,caisDate,caieDate,puSta";
		String field="a.ftpFileDir,a.fileName,b.id,b.ftpsDate,b.ftpeDate,b.caisDate,b.caieDate,a.status";
		try{
			list=super.hibernateDao.getListByHql(property,field, hql+t.get("where").toString(), ((List<Object>)t.get("condition")).toArray(),"", PushSta.class.getName(),pageCount,page*pageCount);
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return list;
	}
	

}
