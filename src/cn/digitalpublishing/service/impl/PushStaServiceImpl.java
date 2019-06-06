package cn.digitalpublishing.service.impl;

import java.util.List;
import java.util.Map;

import cn.com.daxtech.framework.exception.CcsException;
import cn.digitalpublishing.po.PushSta;
import cn.digitalpublishing.po.Task;
import cn.digitalpublishing.service.PushStaService;

public class PushStaServiceImpl extends BaseServiceImpl implements PushStaService {

	@Override
	public void save(PushSta pushSta) throws Exception {
		try {
			this.daoFacade.getPushStaDao().insert(pushSta);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "Pages.books.chu.cuo", e);
		}
		
	}
	@Override
	public PushSta getPushSta(String taskId) throws Exception {
		// TODO Auto-generated method stub
		PushSta obj = null;
		try{
			obj = (PushSta)this.daoFacade.getPushStaDao().get(PushSta.class.getName(), taskId);
		}catch(Exception e){
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt()	: "Pages.books.chu.cuo", e);
		}
		return obj;
	}
	
	@Override
	public PushSta getList(Map<String, Object> condition) throws Exception {
		// TODO Auto-generated method stub
		PushSta obj = null;
		try{
//			obj = (PushSta)this.daoFacade.getPushStaDao().get(PushSta.class.getName(), taskId);
			List<PushSta> list = this.daoFacade.getPushStaDao().getList(condition, "");
			if(list!=null&&list.size()>0){
				obj = list.get(0);
			}
			
		}catch(Exception e){
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt()	: "Pages.books.chu.cuo", e);
		}
		return obj;
	}
	

	@Override
	public void updatePushSta(PushSta obj, String id, String[] properties) throws Exception {
		try {
			daoFacade.getPushStaDao().update(obj, PushSta.class.getName(), id, properties);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "Pages.books.chu.cuo", e);
		}
	}
	
	@Override
	public List<PushSta> getPagingList(Map<String,Object> condition,String sort,Integer pageCount,Integer page)throws Exception{
			List<PushSta> list = null;
			try{
				list = this.daoFacade.getPushStaDao().getPagingList(condition,sort,pageCount,page);
			}catch(Exception e){
				throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt()	: "Pages.books.chu.cuo", e);
			}
			return list;
	}

	@Override
	public int getpushStaCount(Map<String, Object> condition) throws CcsException {
		// TODO Auto-generated method stub
		Integer num = 0;
		try {
			num = daoFacade.getPushStaDao().getCount(condition);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "Pages.books.chu.cuo", e);
		}
		return num;
	}

	
	
}
