package cn.digitalpublishing.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

@SuppressWarnings({ "rawtypes", "unused", "resource", "unchecked" })
public class XpathXML {
	Map<String, Object> data =null;
	final String pathAhead="D:\\";
	private static ArrayList fileList= new ArrayList();
	public Map<String, Object> xpathParse(String path) throws Exception {
		data = new HashMap<String, Object>();
		try {
			SAXReader reader = new SAXReader();
			FileInputStream fis = new FileInputStream(path);
			reader.setValidation(false);
			reader.setFeature(
					"http://apache.org/xml/features/nonvalidating/load-external-dtd",
					false);
			Document document = reader.read(fis);// 得到Document对象
			Element root = document.getRootElement();// 根元素
			List<Map<String, String>> chapters = new ArrayList<Map<String, String>>();
			
			Node journalId = root.selectSingleNode("//journal-id");
			Node issn = root.selectSingleNode("//issn");
			Node publisherName = root.selectSingleNode("//publisher-name");
			List<Node> subNodeList = root.selectNodes("//article-id");
			List<String> ids = new ArrayList<String>();
			for (int i = 0; i < subNodeList.size(); i++) {
				System.out.println(subNodeList.get(i).asXML());
				ids.add(subNodeList.get(i).asXML());
			}
			Node articlecategories = root.selectSingleNode("//article-categories");
			Node titlegroup = root.selectSingleNode("//title-group");
			Node permissions = root.selectSingleNode("//permissions");
			Node pubdate  = root.selectSingleNode("//pub-date");
//			List<Node> list = root.selectNodes("//contrib-group/contrib[@contrib-type='author']");
//			for (Node author : list) {
//				Node surName = author.selectSingleNode("child::name/surname");
//				Node givenName = author
//						.selectSingleNode("child::name/given-names");
//				data.put("surName", surName.getText());
//				data.put("givenName", givenName.getText());
//			}
			Node author=root.selectSingleNode("//contrib-group");
			Node volume = root.selectSingleNode("//volume");
			Node issue = root.selectSingleNode("//issue");
			Node fpage = root.selectSingleNode("//fpage");
			Node lpage = root.selectSingleNode("//lpage");
			
			data.put("filePath", path);
			data.put("articleid", ids);
			data.put("titlegroup", titlegroup.asXML());
			data.put("articlecategories", articlecategories.asXML());
			data.put("pubdate", pubdate.asXML());
			data.put("permissions", permissions.asXML());
			data.put("journalId", journalId.asXML());
			data.put("issn", issn.asXML());
			data.put("publisherName", publisherName.getText());
			data.put("volume", volume.getText());
			data.put("issue", issue.getText());
			data.put("fpage", fpage.getText());
			data.put("lpage", lpage.getText());
		/*	data.put("copyrightstatement",
					copyrightstatement.asXML()
							.replace("<copyright-statement>", "")
							.replace("</copyright-statement>", ""));*/
			data.put("author",author.asXML());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return data;
	}

	public  void createXML() throws Exception{
		
		String path1=data.get("filePath").toString();
		path1=path1.replace(pathAhead,"");
		path1=path1.substring(0,path1.indexOf("\\"));
		 String savePath="d:\\handled\\" + path1 + "\\" + data.get("volume") + "\\" + data.get("issue") + "\\" ;
		if(!FileUtil.isExist(savePath)){
			FileUtil.newFolder(savePath);
		}
		savePath+= data.get("fpage") + "-" + data.get("lpage") + ".xml";
		
		BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\lifh\\Desktop\\DCC格式1.xml"));  
	
		
		String obj=br.readLine();
		String template =obj==null?"":obj;//一次读入一行，直到读入null为文件结束  
		while( obj!=null){  
			obj = br.readLine(); //接着读下一行  
			if(obj!=null){
				template+=obj;
			}
		}	
		
		template=template.replace("{JOURNALID}",data.get("journalId").toString());
		template=template.replace("{ISSN}",data.get("issn").toString());
		template=template.replace("{PUBLISHERNAME}",data.get("publisherName").toString());
		template=template.replace("{PUBLISHERLOC}"," ");
		List<String> ids = (List<String>) data.get("articleid");
		StringBuffer sb = new StringBuffer();
		for (String id : ids) {
			sb.append(id);
		}
		template=template.replace("{ARTICLEID}",sb.toString());
		template=template.replace("{ARTICLECATEGORIES}",data.get("articlecategories").toString());
		template=template.replace("{TITLEGROUP}",data.get("titlegroup").toString());
		template=template.replace("{AUTHORS}",data.get("author").toString());
		template=template.replace("{PERMISSIONS}",data.get("permissions").toString());
		template=template.replace("{PUBLISHER}",data.get("publisherName").toString());
		template=template.replace("{PUBDATE}",data.get("pubdate").toString());
		template=template.replace("{VOLUME}",data.get("volume").toString());
		template=template.replace("{ISSUE}",data.get("issue").toString());
		template=template.replace("{FPAGE}",data.get("fpage").toString());
		template=template.replace("{LPAGE}",data.get("lpage").toString());
		template=template.replace("{ABSTRACT}"," ");
		saveAsFileOutputStream(template,savePath);
	}
	public void refreshFileList(String path ) throws Exception{
		String str=null;
		String fileName=null;
		File dir=new File(path);
		File [] files= dir.listFiles();
		if(files==null){
			return;
		}
		for(int i=0;i<files.length;i++){
			if(files[i].isDirectory()){
				refreshFileList(files[i].getAbsolutePath());
			}else{
				String stringFileName=files[i].getAbsolutePath().toLowerCase();
				String daxie=stringFileName.toUpperCase();
				System.out.println("-----"+stringFileName);
				if(stringFileName.endsWith(".xml")){
					str=stringFileName;
					File str1= new File(str.trim());
					fileName=str1.getName();
					System.out.println(fileName);
					fileList.add(files[i].getAbsoluteFile());
					this.xpathParse(files[i].getAbsolutePath());
					this.createXML();
				}
				
				
			}
		}
	}
	private void saveAsFileOutputStream(String content,String path) throws Exception {
		  
		  if(FileUtil.isExist(path)){
			  FileUtil.delFile(path);
		  }
		  FileOutputStream foutput = null;
		  try {
		   foutput = new FileOutputStream(path);
		   foutput.write(content.getBytes("UTF-8"));
		  } catch(IOException ex) {
		   ex.printStackTrace();
		  } finally {
		   try {
		    foutput.flush();
		    foutput.close();
		   } catch (IOException ex) {
		    ex.printStackTrace();
		   }
		  }
		  System.out.println("文件保存成功。" + path);
		 }
	
	public static void main(String[] args) throws Exception {
		/*String path = "D:\\15596109_v52n4_s12.xml";*/
		String path="D:\\15596109";
		XpathXML xp = new XpathXML();
		xp.refreshFileList(path);
		/*xp.xpathParse(path);
		xp.createXML();*/

	}
}
