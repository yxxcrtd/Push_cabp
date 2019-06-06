package cn.digitalpublishing.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.daxtech.framework.util.CollectionsUtil;
import cn.digitalpublishing.po.OOrder;

public class OrderDao extends CommonDao<OOrder, String> {

	private Map<String,Object> getWhere(Map<String,Object> map){
		Map<String,Object> table=new HashMap<String,Object>();
		String whereString="";
		List<Object> condition=new ArrayList<Object>();
		int flag=0;
		if(CollectionsUtil.exist(map, "name")&&!"".equals(map.get("name"))){
			if(flag==0){
				whereString+=" where lower(oo.name) like ?";
				flag=1;
			}else{
				whereString+=" and lower(oo.name) like ?";
			}
			condition.add("%"+map.get("name").toString().trim().toLowerCase()+"%");
		}
		
		if(CollectionsUtil.exist(map, "code")&&!"".equals(map.get("code"))){
			if(flag==0){
				whereString+=" where lower(oo.code) like ?";
				flag=1;
			}else{
				whereString+=" and lower(oo.code) like ?";
			}
			condition.add("%"+map.get("code").toString().trim().toLowerCase()+"%");
		}
		
		if(CollectionsUtil.exist(map, "ostatus")&&!"".equals(map.get("ostatus"))){
			if(flag==0){
				whereString+=" where oo.status = ?";
				flag=1;
			}else{
				whereString+=" and oo.status = ?";
			}
			condition.add(map.get("ostatus"));
		}
		
		if(CollectionsUtil.exist(map, "createdby")&&!"".equals(map.get("createdby"))){
			if(flag==0){
				whereString+=" where lower(oo.createdby) like ?";
				flag=1;
			}else{
				whereString+=" and lower(oo.createdby) like ?";
			}
			condition.add("%"+map.get("createdby").toString().trim().toLowerCase()+"%");
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
		List<OOrder> list=null;
		Map<String,Object> t=this.getWhere(condition);
		String hql=" from OOrder oo ";
		try{
			list=this.hibernateDao.getListByHql("id","cast(count(*) as string)", hql+t.get("where").toString(), ((List<Object>)t.get("condition")).toArray(),"", OOrder.class.getName());
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
	public List<OOrder> getPagingList(Map<String,Object> condition,String sort,Integer pageCount,Integer page)throws Exception{
		List<OOrder> list=null;
		String hql=" from OOrder oo ";
		Map<String,Object> t=this.getWhere(condition);
		String property="id,code,name,ip,salePrice,settledPrice,status,quantity,createdby,createdon,updatedby,updatedon,payType,purchaser,purchaserPhone,phone,address,paymentMethod,contacts,email";
		String field="oo.id,oo.code,oo.name,oo.ip,oo.salePrice,oo.settledPrice,oo.status,oo.quantity,oo.createdby,oo.createdon,oo.updatedby,oo.updatedon,oo.payType,oo.purchaser,oo.purchaserPhone,oo.phone,oo.address,oo.paymentMethod,oo.contacts,oo.email";
		try{
			list=super.hibernateDao.getListByHql(property,field, hql+t.get("where").toString(), ((List<Object>)t.get("condition")).toArray(),sort, OOrder.class.getName(),pageCount,page*pageCount);
			
		}catch(Exception e){
			throw e;
		}
		return list;
	}
}
