package cn.digitalpublishing.util;

import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XmlSplitHelper {
	
	private Map<String,String> results;
	
	/**
	 * 按产品拆分元数据文件
	 * @param filePath 元数据文件存放路径
	 * @param targetDir 产品数据文件存放路径
	 * @param feature 取消验证需要输入的DTD验证URL  例：http://apache.org/xml/features/nonvalidating/load-external-dtd
	 * @param productEl 拆分产品文件的元素名 /documents/document
	 * @param codeEl 获取产品的唯一编号元素名  eisbn/eisbn13
	 * @param pubEl 公共元素，每个产品中都必须存在
	 * @param rootEl 产品的根元素名  document
	 * @throws Exception 
	 */
	public void split(String filePath,String root,String targetDir,String feature,String productEl,String codeEl,List<Element> pubEl,String rootEl) throws Exception{
		try{
			SAXReader reader = new SAXReader();
			FileInputStream fis = new FileInputStream(filePath);
			XmlSplitHander addHandler = new XmlSplitHander(root,targetDir,codeEl,pubEl,rootEl);
			if(results!=null){
				addHandler.setResults(results);
			}
			reader.addHandler(productEl, addHandler);// 节点
			reader.setValidation(false);  
			reader.setFeature(feature, false);
			reader.getDocumentFactory().setXPathNamespaceURIs(null);
			reader.read(fis);
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}
	
	public Element getPubElement(String filePath,String pubEl,String feature){
		Element pub = null;
		try{
			filePath = filePath.replace("//", "/");
			SAXReader reader = new SAXReader();
			FileInputStream fis = new FileInputStream(filePath);
			XmlSplitHander addHandler = new XmlSplitHander(true);
			reader.addHandler(pubEl, addHandler);// 节点
			reader.setValidation(false);   
			reader.setFeature(feature, false);
			reader.getDocumentFactory().setXPathNamespaceURIs(null);
			reader.read(fis);
			pub = addHandler.getPub();
		}catch(Exception e){
			e.printStackTrace();
		}
		return pub;
	}
	
	public static void main(String[] args) {
		
//		XmlSplitHelper helper = new XmlSplitHelper();
//		helper.setResults(new HashMap<String,String>());
//		List<Element> list = new ArrayList<Element>();
//		Element pub = helper.getPubElement("D:/dawson/source/onix2_1-1.xml", "/ONIXMessage/Header", "http://apache.org/xml/features/nonvalidating/load-external-dtd");
//		list.add(pub);
//		helper.split("D:/dawson/source/onix2_1-1.xml","D:/dawson","/source/temp","http://apache.org/xml/features/nonvalidating/load-external-dtd","/ONIXMessage/Product","RecordReference",list,"ONIXMessage");
//		System.out.println("xxxx");
		//K:/tomcat6/webapps/MetadataConverter/dawson/source/xml/ERA_DATAFULLV1-00_20120628.xml
		//K:/Dawson/unhandled/ERA_DATAFULLV1-00_20120628.xml
	}

	public Map<String, String> getResults() {
		return results;
	}

	public void setResults(Map<String, String> results) {
		this.results = results;
	}

}
