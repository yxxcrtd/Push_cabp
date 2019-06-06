package cn.digitalpublishing.service.factory;

import cn.digitalpublishing.service.FtpDirService;
import cn.digitalpublishing.service.PushStaService;
import cn.digitalpublishing.service.ThreadService;
import cn.digitalpublishing.service.UserTemplateNodeService;


public interface ServiceFactory {
	
	public UserTemplateNodeService getTemplateNodeService();
	
	/**
	 * 获取ftpdirService对象
	 */
	public FtpDirService getFtpDirService();
	
	/**
	 * 获取线程处理通用ThreadService对象
	 */
	public ThreadService getThreadService();
	
	/**
	 * 获取线程处理通用ThreadService对象
	 */
	public PushStaService getPushStaService();
}
