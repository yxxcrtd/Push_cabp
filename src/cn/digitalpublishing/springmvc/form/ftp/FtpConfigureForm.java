package cn.digitalpublishing.springmvc.form.ftp;

import java.util.HashMap;
import java.util.Map;

import cn.digitalpublishing.po.FtpConfigure;
import cn.digitalpublishing.springmvc.form.BaseForm;

public class FtpConfigureForm extends BaseForm {
	
	private FtpConfigure obj = new FtpConfigure();
	
	private String name;
	
	private String code;
	
	private String ip;
	
	private Integer flag;
	

	public static Map<String,String> FTPMap = new HashMap<String,String>();

	public Integer getFlag() {
		return flag;
	}
	
	public void setFlag(Integer flag) {
		this.flag = flag;
		this.getCondition().put("flag",flag.toString());
	}
	public FtpConfigure getObj() {
		return obj;
	}

	public void setObj(FtpConfigure obj) {
		this.obj = obj;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.getCondition().put("name",name);
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.getCondition().put("code",code);
		this.code = code;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.getCondition().put("ip",ip);
		this.ip = ip;
	}


	public static Map<String, String> getFTPMap() {
		return FTPMap;
	}

	public static void setFTPMap(Map<String, String> fTPMap) {
		FTPMap = fTPMap;
	}
	
}
