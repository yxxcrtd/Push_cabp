package cn.digitalpublishing.service.factory.impl;

import cn.com.daxtech.framework.web.service.SpringBeanService;
import cn.digitalpublishing.service.FtpDirService;
import cn.digitalpublishing.service.PushStaService;
import cn.digitalpublishing.service.ThreadService;
import cn.digitalpublishing.service.UserTemplateNodeService;
import cn.digitalpublishing.service.factory.ServiceFactory;

public class ServiceFactoryImpl implements ServiceFactory {

	@Override
	public UserTemplateNodeService getTemplateNodeService() {
		// TODO Auto-generated method stub
		return (UserTemplateNodeService)SpringBeanService.getService("userTemplateNodeService");
	}

	@Override
	public FtpDirService getFtpDirService() {
		// TODO Auto-generated method stub
		return (FtpDirService)SpringBeanService.getService("ftpdirService");
	}
	
	@Override
	public ThreadService getThreadService() {
		// TODO Auto-generated method stub
		return (ThreadService)SpringBeanService.getService("threadService");
	}

	@Override
	public PushStaService getPushStaService() {
		// TODO Auto-generated method stub
		return (PushStaService)SpringBeanService.getService("pushStaService");
	}

}
