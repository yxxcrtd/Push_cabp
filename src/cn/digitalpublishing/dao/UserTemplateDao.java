package cn.digitalpublishing.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.daxtech.framework.util.CollectionsUtil;
import cn.digitalpublishing.po.UserTemplate;

public class UserTemplateDao extends CommonDao<UserTemplate,String> {
	
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
				whereString+=" where lower(a.name) like ?";
				flag=1;
			}else{
				whereString+=" and lower(a.name) like ?";
			}
			condition.add("%"+map.get("name").toString().trim().toLowerCase()+"%");
		}
		
		/**
		 * 2.code
		 */
		if(CollectionsUtil.exist(map, "code")&&!"".equals(map.get("code"))){
			boolean isPrecise=CollectionsUtil.exist(map, "isPrecise")?Boolean.valueOf(map.get("isPrecise").toString()):true;
			if(isPrecise){
				if(flag==0){
					whereString+=" where lower(a.code) = ?";
					flag=1;
				}else{
					whereString+=" and lower(a.code) = ?";
				}
				condition.add(map.get("code").toString().trim().toLowerCase());
			}else{
				if(flag==0){
					whereString+=" where lower(a.code) like ?";
					flag=1;
				}else{
					whereString+=" and lower(a.code) like ?";
				}
				condition.add("%"+map.get("code").toString().trim().toLowerCase()+"%");
			}
		}
		
		/**
		 * 3.flag
		 */
		if(CollectionsUtil.exist(map, "flag")&&!"".equals(map.get("flag"))){
			boolean isPrecise=CollectionsUtil.exist(map, "isPrecise")?Boolean.valueOf(map.get("isPrecise").toString()):true;
			if(isPrecise){
				if(flag==0){
					whereString+=" where lower(a.flag) = ?";
					flag=1;
				}else{
					whereString+=" and lower(a.flag) = ?";
				}
				condition.add(Integer.valueOf((String) map.get("flag")));
			}else{
				if(flag==0){
					whereString+=" where lower(a.flag) = ?";
					flag=1;
				}else{
					whereString+=" and lower(a.flag) = ?";
				}
				condition.add(Integer.valueOf((String) map.get("flag")));
			}
		}
		
		
		/**
		 * 6.ftpcode
		 */
		if(CollectionsUtil.exist(map, "ftpcode")&&!"".equals(map.get("ftpcode"))){
			boolean isPrecise=CollectionsUtil.exist(map, "isPrecise")?Boolean.valueOf(map.get("isPrecise").toString()):true;
			if(isPrecise){
				if(flag==0){
					whereString+=" where lower(a.ftpConfigure.code) = ?";
					flag=1;
				}else{
					whereString+=" and lower(a.ftpConfigure.code) = ?";
				}
				condition.add(map.get("ftpcode"));
			}else{
				if(flag==0){
					whereString+=" where lower(a.ftpConfigure.code) = ?";
					flag=1;
				}else{
					whereString+=" and lower(a.ftpConfigure.code) = ?";
				}
				condition.add(map.get("ftpcode"));
			}
		}
		
		/**
		 * 1.uniqueId
		 */
		if(CollectionsUtil.exist(map, "uniqueId")&&!"".equals(map.get("uniqueId"))){
			if(flag==0){
				whereString+=" where lower(a.id) <> ?";
				flag=1;
			}else{
				whereString+=" and lower(a.id) <> ?";
			}
			condition.add(map.get("uniqueId").toString().trim().toLowerCase());
		}
		
		
		
		if(CollectionsUtil.exist(map, "tsourcecode")&&!"".equals(map.get("tsourcecode"))){
			if(flag==0){
				whereString+=" where b.code = ?";
				flag=1;
			}else{
				whereString+=" and b.code = ?";
			}
			
			condition.add(map.get("type"));
		}
		
		
		/**
		 * 3.type
		 */
		if(CollectionsUtil.exist(map, "type")&&!"".equals(map.get("type"))){
			boolean isPrecise=CollectionsUtil.exist(map, "isPrecise")?Boolean.valueOf(map.get("isPrecise").toString()):true;
			if(isPrecise){
				if(flag==0){
					whereString+=" where lower(a.type) = ?";
					flag=1;
				}else{
					whereString+=" and lower(a.type) = ?";
				}
				condition.add(Integer.valueOf((String) map.get("type")));
			}else{
				if(flag==0){
					whereString+=" where lower(a.type) = ?";
					flag=1;
				}else{
					whereString+=" and lower(a.type) = ?";
				}
				condition.add(Integer.valueOf((String) map.get("type")));
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
	public List<UserTemplate> getPagingList(Map<String,Object> condition,String sort,Integer pageCount,Integer page)throws Exception{
		List<UserTemplate> list=null;
		String hql=" from UserTemplate a ";
		Map<String,Object> t=this.getWhere(condition);
		String property="id,flag,type,originalName,path,fileName,createTime,name ";
		String field="a.id,a.flag,a.type,a.originalName,a.path,a.fileName,a.createTime,a.name ";
		try{
			list=super.hibernateDao.getListByHql(property,field, hql+t.get("where").toString(), ((List<Object>)t.get("condition")).toArray(),sort, UserTemplate.class.getName(),pageCount,page*pageCount);
			
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
		List<UserTemplate> list=null;
		Map<String,Object> t=this.getWhere(condition);
		String hql=" from UserTemplate a ";
		try{
			list=this.hibernateDao.getListByHql("id","cast(count(*) as string)", hql+t.get("where").toString(), ((List<Object>)t.get("condition")).toArray(),"", UserTemplate.class.getName());
		}catch(Exception e){
			throw e;
		}
		return list==null?0:Integer.valueOf(list.get(0).getId());
	}

	/**
	 * 根据Flag查询用户模板列表
	 * 
	 * @param condition
	 * @param sort
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<UserTemplate> getUserTemplateListByFlag(Map<String,Object> condition, String sort) throws Exception {
		List<UserTemplate> list = null;
		Map<String, Object> t = this.getWhere(condition);
		String hql = "FROM UserTemplate a";
		String property = "id, name,splitNode,type";
		String field = "a.id, a.name ,a.splitNode,a.type";
		try {
			list = super.hibernateDao.getListByHql(property, field, hql + t.get("where").toString(), ((List<Object>) t.get("condition")).toArray(), sort, UserTemplate.class.getName());
		} catch (Exception e) {
			throw e;
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<UserTemplate> getList(Map<String, Object> conditionftpCode) throws Exception {
		List<UserTemplate> list = null;
		Map<String, Object> t = this.getWhere(conditionftpCode);
		String hql = "FROM UserTemplate a ";
		String property = "id, name,splitNode,checkTLD,rootNode,bookCodeNode,commonNode,type,isbn,bookName,publisher";
		String field = "a.id, a.name ,a.splitNode,a.checkTLD,a.rootNode,a.bookCodeNode,a.commonNode,a.type,a.isbn,a.bookName,a.publisher";
		try {
			list = super.hibernateDao.getListByHql(property, field, hql + t.get("where").toString(), ((List<Object>) t.get("condition")).toArray(), "", UserTemplate.class.getName());
		} catch (Exception e) {
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
	public List<UserTemplate> getList(Map<String,Object> condition,String sort)throws Exception{
		List<UserTemplate> list=null;
		String hql=" from UserTemplate a left join a.ftpConfigure b ";
		Map<String,Object> t=this.getWhere(condition);
		
		String property="type";
		String field="a.type";
		try{
			list=super.hibernateDao.getListByHql(property,field, hql+t.get("where").toString(), ((List<Object>)t.get("condition")).toArray(),"", UserTemplate.class.getName());
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return list;
	}
	

}
