package cn.digitalpublishing.util;


import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.ElementHandler;
import org.dom4j.ElementPath;
import org.dom4j.io.XMLWriter;

public class XmlSplitHander implements ElementHandler {
	
	private String targetDir;
	
	private String codeEl;
	
	private String root;
	
	private List<Element> pubEl;
	
	private String rootEl;
	
	public XmlSplitHander(){};
	
	Element e = null;
	
	Element code = null;
	
	Element pub = null;
	
	private Map<String,String> results;
	
	boolean getPubEl = false;
	
	public XmlSplitHander(boolean getPubEl){
		this.getPubEl  = getPubEl;
	}
	
	public XmlSplitHander(String root,String targetDir,String codeEl,List<Element> pubEl,String rootEl){
		this.targetDir = targetDir;
		this.codeEl = codeEl;
		this.pubEl = pubEl;
		this.rootEl = rootEl;
		this.root = root;
	}

	public void onEnd(ElementPath ep) {
		e = ep.getCurrent(); // 获得当前节点
	    // 对节点进行操作。。。
		if(!getPubEl){
			code = getCodeElement(e,codeEl);
			if(code!=null&&!"".equals(code.getText().trim())){
				createXml(root,targetDir,e,code.getText().trim());
			}
		}else{
			pub = e;
		}
	    e.detach(); // 处理完当前节点后，将其从dom树中剪除
	}
	
	public Element getPub(){
		return pub!=null?((Element)pub.clone()):pub;
	}
	
	private Element getCodeElement(Element e,String codeEl){
		Element result = null;
		if(codeEl.indexOf("/")==-1){
			if(e!=null)
				result = e.element(codeEl);
		}else{
			if(e!=null)
				result = getCodeElement(e.element(codeEl.substring(0,codeEl.indexOf("/"))),codeEl.substring(codeEl.indexOf("/")+1));
		}
		return result;
	}

	public void onStart(ElementPath ep) {
		// TODO Auto-generated method stub

	}

	public String getTargetDir() {
		return targetDir;
	}

	public void setTargetDir(String targetDir) {
		this.targetDir = targetDir;
	}
	
	private void createXml(String root,String targetDir,Element el,String isbn){
		try{
			if(el!=null){
				String fileName = isbn.toString()+".xml";
				String filePath = targetDir+"/"+fileName;
				if(results!=null){
					results.put(isbn.toString(),filePath);
				}
				Document doc = null;
				doc = DocumentHelper.createDocument();
				doc.setXMLEncoding("UTF-8");
				Element codes = doc.addElement(rootEl);
				if(pubEl!=null&&!pubEl.isEmpty()){
					for(Element pub:pubEl){
						if(pub!=null){
							codes.add((Element)pub.clone());
						}
					}
				}
				codes.add((Element)el.clone());
				XMLWriter writer = new XMLWriter(new FileOutputStream(root+filePath));
				writer.write(doc);  
				writer.close();
				doc = null;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public Map<String, String> getResults() {
		return results;
	}

	public void setResults(Map<String, String> results) {
		this.results = results;
	}


}
