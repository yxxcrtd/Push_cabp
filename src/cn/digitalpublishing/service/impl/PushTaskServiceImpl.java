package cn.digitalpublishing.service.impl;

import java.util.List;
import java.util.Map;

import cn.com.daxtech.framework.exception.CcsException;
import cn.digitalpublishing.po.Task;
import cn.digitalpublishing.service.PushTaskService;

/**
 * Push Service Implement
 * 
 * @author yul
 */
public class PushTaskServiceImpl extends BaseServiceImpl implements PushTaskService {
	// TODO Auto-generated method stub
	@Override
	public List<Task> getPagingList(Map<String,Object> condition,String sort,Integer pageCount,Integer page)throws Exception{
			List<Task> list = null;
			try{
				list = this.daoFacade.getPushTaskDao().getPagingList(condition,sort,pageCount,page);
			}catch(Exception e){
				throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt()	: "Pages.books.chu.cuo", e);
			}
			return list;
	}

	@Override
	public int getpushTaskCount(Map<String, Object> condition) throws CcsException {
		// TODO Auto-generated method stub
		Integer num = 0;
		try {
			num = daoFacade.getPushTaskDao().getCount(condition);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "Pages.books.chu.cuo", e);
		}
		return num;
	}

	@Override
	public void save(Task task) throws Exception {
		try {
			this.daoFacade.getPushTaskDao().insert(task);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "Pages.books.chu.cuo", e);
		}
		
	}
}
