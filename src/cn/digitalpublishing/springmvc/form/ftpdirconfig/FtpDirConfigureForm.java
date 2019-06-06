package cn.digitalpublishing.springmvc.form.ftpdirconfig;

import java.util.HashMap;
import java.util.Map;

import cn.digitalpublishing.po.FtpDirConfigure;
import cn.digitalpublishing.springmvc.form.BaseForm;

public class FtpDirConfigureForm extends BaseForm {
	private FtpDirConfigure obj = new FtpDirConfigure();

	public static Map<String, String> ftpDirMap = new HashMap<String, String>();
	
	private String ftpid;

	private String code;

	private String dirName;

	private Integer flag;

	public String getFtpid() {
		return ftpid;
	}

	public void setFtpid(String ftpid) {
		this.ftpid = ftpid;
		this.getCondition().put("ftpid", ftpid);
	}

	public String getDirName() {
		return dirName;
	}

	public void setDirName(String dirName) {
		this.dirName = dirName;
		this.getCondition().put("dirName", dirName);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		this.getCondition().put("name", name);
	}

	private String name;

	public FtpDirConfigure getObj() {
		return obj;
	}

	public void setObj(FtpDirConfigure obj) {
		this.obj = obj;
	}

	public static Map<String, String> getftpDirMap() {
		return ftpDirMap;
	}

	public static void setftpDirMap(Map<String, String> ftpdirmap) {
		ftpDirMap = ftpdirmap;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
		this.getCondition().put("flag", flag.toString());
	}
}
