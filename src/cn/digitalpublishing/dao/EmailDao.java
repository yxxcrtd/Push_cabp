package cn.digitalpublishing.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.daxtech.framework.util.CollectionsUtil;
import cn.digitalpublishing.po.Email;

public class EmailDao extends CommonDao<Email, String> {

	private Map<String,Object> getWhere(Map<String,Object> map){
		Map<String,Object> table=new HashMap<String,Object>();
		String whereString="";
		List<Object> condition=new ArrayList<Object>();
		int flag=0;
		if(CollectionsUtil.exist(map, "contacts")&&!"".equals(map.get("contacts"))){
			if(flag==0){
				whereString+=" where lower(a.contacts) like ?";
				flag=1;
			}else{
				whereString+=" and lower(a.contacts) like ?";
			}
			condition.add("%"+map.get("contacts").toString().trim().toLowerCase()+"%");
		}
		
		if(CollectionsUtil.exist(map, "createdond")&&!"".equals(map.get("createdond"))){
			if(flag==0){
				whereString+=" where a.sendDate >= ?";
				flag=1;
			}else{
				whereString+=" and a.sendDate >= ?";
			}
			condition.add(map.get("createdond"));
		}
		
		if(CollectionsUtil.exist(map, "createdtod")&&!"".equals(map.get("createdtod"))){
			if(flag==0){
				whereString+=" where a.sendDate <= ?";
				flag=1;
			}else{
				whereString+=" and a.sendDate <= ?";
			}
			condition.add(map.get("createdtod"));
		}
		
		
		table.put("where",whereString);
		table.put("condition", condition);
		return table;
		
		
	}
	
	/**
	 * 获取总数
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Integer getCount(Map<String,Object> condition)throws Exception{
		List<Email> list=null;
		Map<String,Object> t=this.getWhere(condition);
		String hql=" from Email a ";
		try{
			list=this.hibernateDao.getListByHql("id","cast(count(*) as string)", hql+t.get("where").toString(), ((List<Object>)t.get("condition")).toArray(),"", Email.class.getName());
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
	public List<Email> getPagingList(Map<String,Object> condition,String sort,Integer pageCount,Integer page)throws Exception{
		List<Email> list=null;
		String hql=" from Email a ";
		Map<String,Object> t=this.getWhere(condition);
		String property="id,contacts,email,sendDate,content";
		String field="a.id,a.contacts,a.email,a.sendDate,a.content";
		try{
			list=super.hibernateDao.getListByHql(property,field, hql+t.get("where").toString(), ((List<Object>)t.get("condition")).toArray(),sort, Email.class.getName(),pageCount,page*pageCount);
			
		}catch(Exception e){
			throw e;
		}
		return list;
	}
	
	
}
