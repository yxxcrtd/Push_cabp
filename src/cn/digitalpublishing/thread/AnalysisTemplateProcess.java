package cn.digitalpublishing.thread;

import cn.digitalpublishing.po.UserTemplate;
import cn.digitalpublishing.service.factory.ServiceFactory;
import cn.digitalpublishing.service.factory.impl.ServiceFactoryImpl;

public class AnalysisTemplateProcess extends Thread {

		ServiceFactory serviceFactory = null;
		
		private Counter counter;
		
		private UserTemplate userTemplate;
			
		
		public AnalysisTemplateProcess (Counter counter,UserTemplate userTemplate){
			serviceFactory=(ServiceFactory)new ServiceFactoryImpl();
			this.userTemplate=userTemplate;
			this.counter=counter;
		}
			
		@Override
		public void run(){
			this.scan();
			counter.countDown();
		}
			
		private void scan(){
			try {
				serviceFactory.getTemplateNodeService().analysisTemplateAddNode(userTemplate);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
}
