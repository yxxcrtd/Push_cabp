package cn.digitalpublishing.po;

import java.io.Serializable;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnore;

@SuppressWarnings("serial")
public class FtpConfigure implements Serializable {
	/**
	 * 主键
	 */
	private String id;
	/**
	 * ftp标记 0代表源数据ftp，1代表目标ftp
	 */
	private Integer flag;

	/**
	 * 编号
	 */
	
	private String code;
	
	/**
	 * 名称
	 */
	private String name;
	/**
	 * IP
	 */
	private String ip;
	/**
	 * 端口
	 */
	private String port;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 用户名
	 */
	private String username;
	
	/**
	 *ftp文件夹配置
	 */
	@JsonIgnore
	private Set<FtpDirConfigure> directories;

	/**
	 * 用户模板集合
	 */
	@JsonIgnore
	private Set<UserTemplate> userTemplates; 
	

	public Set<UserTemplate> getUserTemplates() {
		return userTemplates;
	}

	public void setUserTemplates(Set<UserTemplate> userTemplates) {
		this.userTemplates = userTemplates;
	}

	public Set<FtpDirConfigure> getDirectories() {
		return directories;
	}

	public void setDirectories(Set<FtpDirConfigure> directories) {
		this.directories = directories;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

}
