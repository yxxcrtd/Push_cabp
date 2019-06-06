package cn.digitalpublishing.thread;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import cn.com.daxtech.framework.exception.CcsException;
import cn.digitalpublishing.po.FtpDirConfigure;
import cn.digitalpublishing.po.PushSta;
import cn.digitalpublishing.po.TSource;
import cn.digitalpublishing.po.Task;
import cn.digitalpublishing.po.UserTemplate;
import cn.digitalpublishing.po.UserTemplateNode;
import cn.digitalpublishing.service.factory.ServiceFactory;
import cn.digitalpublishing.service.factory.impl.ServiceFactoryImpl;

@SuppressWarnings("unused")
public class PushProcess extends Thread {

		ServiceFactory serviceFactory = null;
			
		private Counter counter;
			
		public PushProcess (Counter counter){
			serviceFactory=(ServiceFactory)new ServiceFactoryImpl();
			this.counter=counter;
		}
			
		@Override
		public void run(){
			this.scan();
			counter.countDown();
		}
			
		private void scan(){
			System.out.println("----PushProcess线程开启-----");
			//扫描 task任务表，将stauts=1 的记录进行推送
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("status", 1);
			Task task = null;
			
			try {	
				List<Task> tasklist = this.serviceFactory.getThreadService().getTaskPagingList(condition,"order by a.createTime asc",1, 0);
				//取得一条记录
				if(tasklist!=null&&tasklist.size()>0){
					task=tasklist.get(0);
					
					Map<String,Object> conditionother = new HashMap<String,Object>();
					task.setTuisDate(new Date());
					//发送数据到目标ftp
					this.serviceFactory.getThreadService().sendFile(task);
					//修改该执行状态
					task.setStatus(2);
					task.setTuieDate(new Date());
					this.serviceFactory.getThreadService().update(task, task.getId(),null);
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				//修改执行状态为 推送失败
				task.setStatus(3);
				try {
					this.serviceFactory.getThreadService().update(task, task.getId(),null);
				} catch (CcsException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}		
}
