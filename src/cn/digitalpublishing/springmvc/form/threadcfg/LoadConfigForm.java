package cn.digitalpublishing.springmvc.form.threadcfg;

import java.util.HashMap;
import java.util.Map;

import cn.digitalpublishing.springmvc.form.BaseForm;

/**
 * @author yul
 *
 */
public class LoadConfigForm extends BaseForm {
	
	
	//下载源数据
	private int sourceDataLoad;
	

	//解析源数据
	private int analysisDataLoad;

	//下载图书文件
	private int download;
	
	//模板转换
	private int convertDataLoad;
	
	//图书推送
	private int pushLoad;
	

	private Map<String,String> loadMap =new HashMap<String,String>();
	
	

	public int getAnalysisDataLoad() {
		return analysisDataLoad;
	}

	public void setAnalysisDataLoad(int analysisDataLoad) {
		this.analysisDataLoad = analysisDataLoad;
	}

	public int getDownload() {
		return download;
	}

	public void setDownload(int download) {
		this.download = download;
	}

	public int getConvertDataLoad() {
		return convertDataLoad;
	}

	public void setConvertDataLoad(int convertDataLoad) {
		this.convertDataLoad = convertDataLoad;
	}

	public Map<String, String> getLoadMap() {
		return loadMap;
	}

	public void setLoadMap(Map<String, String> loadMap) {
		this.loadMap = loadMap;
	}
	public int getSourceDataLoad() {
		return sourceDataLoad;
	}

	public void setSourceDataLoad(int sourceDataLoad) {
		this.sourceDataLoad = sourceDataLoad;
	}
	
	public int getPushLoad() {
		return pushLoad;
	}

	public void setPushLoad(int pushLoad) {
		this.pushLoad = pushLoad;
	}
}

