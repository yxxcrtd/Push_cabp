package cn.digitalpublishing.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.daxtech.framework.util.CollectionsUtil;
import cn.digitalpublishing.po.FtpConfigure;

public class FtpConfigureDao extends CommonDao<FtpConfigure,String> {
	
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
				System.out.println(map.get("code"));
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
				condition.add(Integer.valueOf(map.get("flag").toString()));
			}else{
				if(flag==0){
					whereString+=" where lower(a.flag) = ?";
					flag=1;
				}else{
					whereString+=" and lower(a.flag) = ?";
				}
				condition.add(Integer.valueOf((map.get("flag").toString())));
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
		/**
		 * 1.unbeind
		 */
		if(CollectionsUtil.exist(map, "unbeind")&&!"".equals(map.get("unbeind"))){
			if(flag==0){
				whereString+=" where a.flag=0 and a.id not in(select u.ftpConfigure from UserTemplate u)";
				flag=1;
			}else{
				whereString+=" and a.flag=0 and a.id not in(select u.ftpConfigure from UserTemplate u)";
			}
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
	public List<FtpConfigure> getList(Map<String,Object> condition,String sort)throws Exception{
		List<FtpConfigure> list=null;
		Map<String,Object> t=this.getWhere(condition);
		String hql=" from FtpConfigure a ";
		String property="id,name ";
		String field="a.id,a.name ";
		try{
			list=super.hibernateDao.getListByHql(property,field, hql+t.get("where").toString(), ((List<Object>)t.get("condition")).toArray(),sort, FtpConfigure.class.getName());
		}catch(Exception e){
			e.printStackTrace();
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
	public List<FtpConfigure> getPagingList(Map<String,Object> condition,String sort,Integer pageCount,Integer page)throws Exception{
		List<FtpConfigure> list=null;
		String hql=" from FtpConfigure a ";
		Map<String,Object> t=this.getWhere(condition);
		String property="id,name,code,ip,port,username,password ";
		String field="a.id,a.name,a.code,a.ip,a.port,a.username,a.password ";
		try{
			list=super.hibernateDao.getListByHql(property,field, hql+t.get("where").toString(), ((List<Object>)t.get("condition")).toArray(),sort, FtpConfigure.class.getName(),pageCount,page*pageCount);
			
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
		List<FtpConfigure> list=null;
		Map<String,Object> t=this.getWhere(condition);
		String hql=" from FtpConfigure a ";
		try{
			list=this.hibernateDao.getListByHql("id","cast(count(*) as string)", hql+t.get("where").toString(), ((List<Object>)t.get("condition")).toArray(),"", FtpConfigure.class.getName());
		}catch(Exception e){
			throw e;
		}
		return list==null?0:Integer.valueOf(list.get(0).getId());
	}

	@SuppressWarnings("unchecked")
	public List<FtpConfigure> getFtpUnbindTemplate(String sort) throws Exception {
		// TODO Auto-generated method stub
		List<FtpConfigure> list=null;
		Map<String,Object> t1=new HashMap<String,Object>();
		t1.put("unbeind", "unbeind");
		Map<String,Object> t=this.getWhere(t1);
		String hql=" from FtpConfigure a ";
		String property="id,name ";
		String field="a.id,a.name ";
		try{
			list=super.hibernateDao.getListByHql(property,field, hql+t.get("where").toString(), ((List<Object>)t.get("condition")).toArray(),sort, FtpConfigure.class.getName());
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return list;
	}


}
