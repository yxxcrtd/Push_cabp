package cn.digitalpublishing.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.daxtech.framework.util.CollectionsUtil;
import cn.digitalpublishing.po.Task;

public class PushTaskDao extends CommonDao<Task,String> {
	
	private Map<String,Object> getWhere(Map<String,Object> map){
		Map<String,Object> table=new HashMap<String,Object>();
		String whereString="";
		List<Object> condition=new ArrayList<Object>();
		int flag=0;
		/**
		 * 2.status
		 */
		if(CollectionsUtil.exist(map, "status")&&map.get("status")!=null&&!"".equals(map.get("status"))){
			if(flag==0){
				whereString+=" where a.status = ?";
				flag=1;
			}else{
				whereString+=" and a.status = ?";
			}
			condition.add(Integer.valueOf(map.get("status").toString()));
		}
		
		/**
		 * 3.type
		 */
		if(CollectionsUtil.exist(map, "type")&&!"".equals(map.get("type"))&&map.get("type")!=null){
			if(flag==0){
				whereString+=" where b.type = ?";
				flag=1;
			}else{
				whereString+=" and b.type = ?";
			}
			condition.add(map.get("type"));
		}
		
		//书名
		if(CollectionsUtil.exist(map, "bookName")&&!"".equals(map.get("bookName"))&&map.get("bookName")!=null){
			if(flag==0){
				whereString+=" where a.bookName like ?";
				flag=1;
			}else{
				whereString+=" and a.bookName like ?";
			}
			condition.add(map.get("bookName"));
		}
		
		
		//书名
				if(CollectionsUtil.exist(map, "publisher")&&!"".equals(map.get("publisher"))&&map.get("publisher")!=null){
					if(flag==0){
						whereString+=" where a.publisher like ?";
						flag=1;
					}else{
						whereString+=" and a.publisher like ?";
					}
					condition.add("%" + map.get("publisher").toString().trim().toLowerCase() + "%");
				}
		
		
		// 查询条件3：
				if (CollectionsUtil.exist(map, "userTemplateName") && !"".equals(map.get("userTemplateName"))) {
					if (flag == 0) {
						whereString += " WHERE lower(a.userTemplateName) like ?";
						flag = 1;
					} else {
						whereString += " AND lower(a.userTemplateName) like ?";
					}
					condition.add("%" + map.get("userTemplateName").toString().trim().toLowerCase() + "%");
				}
				
				// 查询条件4：targateTemplateName
				if (CollectionsUtil.exist(map, "targateTemplateName") && !"".equals(map.get("targateTemplateName"))) {
					if (flag == 0) {
						whereString += " WHERE lower(a.targateTemplateName) like ?";
						flag = 1;
					} else {
						whereString += " AND lower(a.targateTemplateName) like ?";
					}
					condition.add("%" + map.get("targateTemplateName").toString().trim().toLowerCase() + "%");
				}
				
				if (CollectionsUtil.exist(map, "isbn") && !"".equals(map.get("isbn"))) {
					if (flag == 0) {
						whereString += " WHERE lower(a.isbn) like ?";
						flag = 1;
					} else {
						whereString += " AND lower(a.isbn) like ?";
					}
					condition.add("%" + map.get("isbn").toString().trim().toLowerCase() + "%");
				}
				
				if(CollectionsUtil.exist(map, "createdond")&&!"".equals(map.get("createdond"))){
					if(flag==0){
						whereString+=" where lower(a.createTime) >= ?";
						flag=1;
					}else{
						whereString+=" and lower(a.createTime) >= ?";
					}
					condition.add(map.get("createdond"));
				}
				
				if(CollectionsUtil.exist(map, "createdtod")&&!"".equals(map.get("createdtod"))){
					if(flag==0){
						whereString+=" where lower(a.createTime) <= ?";
						flag=1;
					}else{
						whereString+=" and lower(a.createTime) <= ?";
					}
					condition.add(map.get("createdtod"));
				}
				// 查询条件5：targateServer
				if (CollectionsUtil.exist(map, "targateServer") && !"".equals(map.get("targateServer"))) {
					if (flag == 0) {
						whereString += " WHERE lower(a.targateServer) like ?";
						flag = 1;
					} else {
						whereString += " AND lower(a.targateServer) like ?";
					}
					condition.add("%" + map.get("targateServer").toString().trim().toLowerCase() + "%");
				}
				
				if (CollectionsUtil.exist(map, "classify") && !"".equals(map.get("classify"))) {
					if (flag == 0) {
						whereString += " WHERE lower(a.classify) like ?";
						flag = 1;
					} else {
						whereString += " AND lower(a.classify) like ?";
					}
					condition.add("%" + map.get("classify").toString().trim().toLowerCase() + "%");
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
		List<Task> list=null;
		Map<String,Object> t=this.getWhere(condition);
		String hql=" from Task a ";
		try{
			list=this.hibernateDao.getListByHql("id","cast(count(*) as string)", hql+t.get("where").toString(), ((List<Object>)t.get("condition")).toArray(),"", Task.class.getName());
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
	public List<Task> getPagingList(Map<String,Object> condition,String sort,Integer pageCount,Integer page)throws Exception{
		List<Task> list=null;
		String hql=" from Task a ";
	
		Map<String,Object> t=this.getWhere(condition);
		String property="id,fileName,targateServer,status,userTemplateName,targateTemplateName,ip,port,username,password,createTime,pushFilePath,ftpdir,bookName,isbn,publisher,classify,dabaoDate,tuisDate,tuieDate ";
		String field="a.id,a.fileName,a.targateServer,a.status,a.userTemplateName,a.targateTemplateName,a.ip,a.port,a.username,a.password,a.createTime,a.pushFilePath,a.ftpdir,a.bookName,a.isbn,a.publisher,a.classify,a.dabaoDate,a.tuisDate,a.tuieDate";
		try{
			list=super.hibernateDao.getListByHql(property,field, hql+t.get("where").toString(), ((List<Object>)t.get("condition")).toArray(),sort, Task.class.getName(),pageCount,page*pageCount);
		}catch(Exception e){
			throw e;
		}
		return list;
	}

}
