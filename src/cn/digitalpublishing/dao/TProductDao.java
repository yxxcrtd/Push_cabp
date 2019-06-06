package cn.digitalpublishing.dao;

import cn.com.daxtech.framework.util.CollectionsUtil;
import cn.digitalpublishing.po.TProduct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class TProductDao extends CommonDao<TProduct,String> {
	
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
			condition.add(map.get("status"));
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
		
		
		/**
		 * 4.sourcestatus  
		 */
		if(CollectionsUtil.exist(map, "sourcestatus")&&map.get("sourcestatus")!=null&&!"".equals(map.get("sourcestatus"))){
			if(flag==0){
				whereString+=" where b.status = ?";
				flag=1;
			}else{
				whereString+=" and b.status = ?";
			}
			condition.add(Integer.valueOf((String) map.get("sourcestatus")));
		}
		
		
		/**
		 * 4.sourceid 
		 */
		if(CollectionsUtil.exist(map, "sourceid")&&map.get("sourceid")!=null&&!"".equals(map.get("sourceid"))){
			if(flag==0){
				whereString+=" where a.source.id = ?";
				flag=1;
			}else{
				whereString+=" and a.source.id = ?";
			}
			condition.add(map.get("sourceid"));
		}
		
		
		/**
		 * 8.operStatus
		 */
		if(CollectionsUtil.exist(map, "operStatus")&&map.get("operStatus")!=null&&!"".equals(map.get("operStatus"))&&!"0".equals(map.get("operStatus").toString())){
			if(flag==0){
				whereString+=" where a.operStatus = ?";
				flag=1;
			}else{
				whereString+=" and a.operStatus = ?";
			}
			condition.add(map.get("operStatus"));
		}
		
		table.put("where",whereString);
		table.put("condition", condition);
		return table;
	}
	
	public List<TProduct> getPubNameList(Map<String,Object> condition,String sort)throws Exception{
		List<TProduct> list=null;
		String hql=" from TProduct a ";
		Map<String,Object> t=this.getWhere(condition);
		String property="pubName";
		String field="distinct a.pubName";
		try{
			list=super.hibernateDao.getListByHql(property,field, hql+t.get("where").toString(), ((List<Object>)t.get("condition")).toArray(),sort, TProduct.class.getName());
		}catch(Exception e){
			throw e;
		}
		return list;
	}
	
	public List<TProduct> getDincList(Map<String,Object> condition,String sort)throws Exception{
		List<TProduct> list=null;
		String hql=" from TProduct a left join a.source b ";
		Map<String,Object> t=this.getWhere(condition);
		String property="subTypeCode";
		String field="distinct a.subTypeCode";
		try{
			list=super.hibernateDao.getListByHql(property,field, hql+t.get("where").toString(), ((List<Object>)t.get("condition")).toArray(),sort, TProduct.class.getName());
		}catch(Exception e){
			throw e;
		}
		return list;
	}

	public List<TProduct> getDincLists(Map<String,Object> condition,String sort)throws Exception{
		List<TProduct> list=null;
		String hql=" from TProduct a left join a.source b ";
		Map<String,Object> t=this.getWhere(condition);
		String property="subCode";
		String field="distinct a.subCode";
		try{
			list=super.hibernateDao.getListByHql(property,field, hql+t.get("where").toString(), ((List<Object>)t.get("condition")).toArray(),sort, TProduct.class.getName());
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
	public Integer getCount(Map<String,Object> condition)throws Exception{
		List<TProduct> list=null;
		Map<String,Object> t=this.getWhere(condition);
		String hql=" from TProduct a left join a.source b ";
		try{
			list=this.hibernateDao.getListByHql("id","cast(count(*) as string)", hql+t.get("where").toString(), ((List<Object>)t.get("condition")).toArray(),"", TProduct.class.getName());
		}catch(Exception e){
			throw e;
		}
		return list==null?0:Integer.valueOf(list.get(0).getId());
	}

	public List<TProduct> getPagingListToGenerate(Map<String, Object> condition, String sort, int countPage, int curPage)throws Exception {
		List<TProduct> list=null;
		String hql=" from TProduct a left join a.source b ";
		Map<String,Object> t=this.getWhere(condition);
		String property="id";
		String field="a.id";
		hql += t.get("where").toString();
		hql += " and exists(select s.elseCode from cn.digitalpublishing.po.BSubRelation s where s.elseCode=replace(replace(a.subCode,'[',''),']',''))";
		hql += " and exists(select p.coopCode from cn.digitalpublishing.po.BPubRelation p where p.coopCode=a.pubCode)";
		try{
			list=super.hibernateDao.getListByHql(property,field,hql , ((List<Object>)t.get("condition")).toArray(),sort, TProduct.class.getName(),countPage,curPage*countPage);
		}catch(Exception e){
//			e.printStackTrace();
			throw e;
		}
		return list;
	}
	
	/************************************TPIData*****************************************/
	/**
	 * 获取列表
	 * @param condition
	 * @param sort
	 * @return
	 * @throws Exception
	 */
	public List<TProduct> getTPIList(Map<String,Object> condition,String sort)throws Exception{
		List<TProduct> list=null;
		String hql=" from TProduct a left join a.source b ";
		Map<String,Object> t=this.getWhere(condition);
		String property="id,code,path,title,pubName,author,pubCode,subTypeName,subTypeCode,subName,subCode,updateDate,operStatus,status,isbn,bookName,publisher";
		String field="a.id,a.code,a.path,a.title,a.pubName,a.author,a.pubCode,a.subTypeName,a.subTypeCode,a.subName,a.subCode,a.updateDate,a.operStatus,a.status,a.isbn,a.bookName,a.publisher";
		try{
			list=super.hibernateDao.getListByHql(property,field, hql+t.get("where").toString(), ((List<Object>)t.get("condition")).toArray(),sort, TProduct.class.getName());
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
	public List<TProduct> getTPIPagingList(Map<String,Object> condition,String sort,Integer pageCount,Integer page)throws Exception{
		List<TProduct> list=null;
		String hql=" from TProduct a ";
		Map<String,Object> t=this.getWhere(condition);
		String property="id,code,path,title,pubName,author,pubCode,subTypeName,subTypeCode,subName,subCode,updateDate,operStatus,status";
		String field="a.id,a.code,a.path,a.title,a.pubName,a.author,a.pubCode,a.subTypeName,a.subTypeCode,a.subName,a.subCode,a.updateDate,a.operStatus,a.status";
		try{
			list=super.hibernateDao.getListByHql(property,field, hql+t.get("where").toString(), ((List<Object>)t.get("condition")).toArray(),sort, TProduct.class.getName(),pageCount,page*pageCount);
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
	public Integer getTPICount(Map<String,Object> condition)throws Exception{
		List<TProduct> list=null;
		Map<String,Object> t=this.getWhere(condition);
		String hql=" from TProduct a ";
		try{
			list=this.hibernateDao.getListByHql("id","cast(count(*) as string)", hql+t.get("where").toString(), ((List<Object>)t.get("condition")).toArray(),"", TProduct.class.getName());
		}catch(Exception e){
			throw e;
		}
		return list==null?0:Integer.valueOf(list.get(0).getId());
	}
	
	public TProduct getTPIById(Map<String,Object> condition)throws Exception{
		TProduct tp = new TProduct();
		List<TProduct> list=new ArrayList<TProduct>();
		String hql=" from TProduct a left join a.source b ";
		Map<String,Object> t=this.getWhere(condition);
		String property="id,code,path,title,pubName,author,pubCode,subTypeName,subTypeCode,subName,subCode,updateDate,operStatus,status,source.id";
		String field="a.id,a.code,a.path,a.title,a.pubName,a.author,a.pubCode,a.subTypeName,a.subTypeCode,a.subName,a.subCode,a.updateDate,a.operStatus,a.status,b.id";
		try{
			list=super.hibernateDao.getListByHql(property,field, hql+t.get("where").toString(), ((List<Object>)t.get("condition")).toArray(),"", TProduct.class.getName());
			if(list.size() != 0){
				return list.get(0);
			}
		}catch(Exception e){
			throw e;
		}
		return tp;
	}

	public List<TProduct> getTPIPagingListToGenerate(Map<String, Object> condition, String sort, int countPage, int curPage)throws Exception {
		List<TProduct> list=null;
		String hql=" from TProduct a left join a.source b ";
		Map<String,Object> t=this.getWhere(condition);
		String property="id";
		String field="a.id";
		hql += t.get("where").toString();
		hql += " and exists(select s.elseCode from cn.digitalpublishing.po.BSubRelation s where s.elseCode=replace(replace(a.subCode,'[',''),']',''))";
		hql += " and exists(select p.coopCode from cn.digitalpublishing.po.BPubRelation p where p.coopCode=a.pubCode)";
		try{
			list=super.hibernateDao.getListByHql(property,field,hql , ((List<Object>)t.get("condition")).toArray(),sort, TProduct.class.getName(),countPage,curPage*countPage);
		}catch(Exception e){
//			e.printStackTrace();
			throw e;
		}
		return list;
	}
	
	
	public List<TProduct> getErrorList (Map<String, Object> condition, String sort) throws Exception {
		List<TProduct> list = null;
		Map<String,Object> t = this.getWhere(condition);
		List<Object> param = new ArrayList<Object>();
		String property = "operStatus,status";
		String field = "a.operStatus, cast(count(a.operStatus) as int)";
		String hql = "from TProduct a left join a.source b ";
		
		String where = "";
		for (Object o : (List<Object>) t.get("condition")) {
			param.add(o);
		}
		
		if(CollectionsUtil.exist(condition, "operSta") && !"".equals(condition.get("operSta")))
		{
			Integer a[] = (Integer[]) condition.get("operSta");
			for(int i : a)
			{
				where += " and a.operStatus != ?";
				param.add(i);
			}
		} 
		
		try {
			
			list = this.hibernateDao.getListByHql(property, field, hql + t.get("where").toString()+where,param.toArray(),sort,TProduct.class.getName());
		
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return list;
	}
	/**
	 * ErrorTproduct 错误信息
	 * @param condition
	 * @param sort
	 * @return
	 * @throws Exception
	 */
	public List<TProduct> getErrorTproductList (Map<String,Object> condition,String sort,Integer pageCount,Integer page) throws Exception {
			List<TProduct> list = null;
			Map<String,Object> t = this.getWhere(condition);
			String property = "code,operStatus,path,updateDate,source.fileName,source.source.code,source.source.name,tempTransformError";
			String field = "a.code,a.operStatus,a.path,a.updateDate,b.fileName,c.code,c.name,a.tempTransformError";
			String hql = "from TProduct a left join a.source b  left join b.source c";
			try {
				list=super.hibernateDao.getListByHql(property,field, hql+t.get("where").toString(),((List<Object>)t.get("condition")).toArray(),sort, TProduct.class.getName(),pageCount,page*pageCount);
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
			return list;
	}


	/**
	 * 查询缺少PDF或Cover的信息
	 * @param condition
	 * @param sort
	 * @return
	 * @throws Exception
	 */
	public List<TProduct> getErrorProductList(Map<String, Object> condition,String sort)throws Exception {
		List<TProduct> list = null;
		Map<String,Object> t = this.getWhere(condition);
		String property = "id,code,path";
		String field = "a.id,a.code,a.path";
		String hql = "from TProduct a left join a.source b ";
		try {
			list = this.hibernateDao.getListByHql(property, field, hql + t.get("where").toString(),((List<Object>)t.get("condition")).toArray(),sort,TProduct.class.getName());
		
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return list;
	}


	public List<TProduct> getTproductList(Map<String,Object> condition,String sort)throws Exception {
		// TODO Auto-generated method stub
		List<TProduct> list=null;
		String hql=" from TProduct a left join a.source b";
		
		Map<String,Object> t=this.getWhere(condition);
		String property="code,source";
		String field="a.code,a.source";
		try{
			list=super.hibernateDao.getListByHql(property,field, hql+t.get("where").toString(), ((List<Object>)t.get("condition")).toArray(),sort, TProduct.class.getName());
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return list;
	}
	
}
