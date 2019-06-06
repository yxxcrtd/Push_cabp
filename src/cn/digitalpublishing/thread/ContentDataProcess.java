//package cn.digitalpublishing.thread;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import cn.com.daxtech.framework.model.Param;
//import cn.com.daxtech.framework.util.Log;
//import cn.digitalpublishing.po.TProduct;
//import cn.digitalpublishing.po.TPubTempRelation;
//import cn.digitalpublishing.service.factory.ServiceFactory;
//import cn.digitalpublishing.service.factory.impl.ServiceFactoryImpl;
//import cn.digitalpublishing.util.web.IpUtil;
//
//public class ContentDataProcess extends Thread {
//
//		ServiceFactory serviceFactory = null;
//			
//			private Counter counter;
//			
//			public ContentDataProcess (Counter counter){
//				serviceFactory=(ServiceFactory)new ServiceFactoryImpl();
//				this.counter=counter;
//			}
//			
//			@Override
//			public void run(){
//				this.scan();
//				counter.countDown();
//			}
//			
//			private void scan(){
//				TProduct tProduct = null;
//				try {
//					String webRoot = Param.getParam("pdf.directory.config").get("dir");
//					webRoot = webRoot.replace("-",":");
//					//逐条遍历TProduct中未处理记录
//					//查询未解析记录中的一条
//					Map<String,Object> condition = new HashMap<String,Object>();
//					condition.put("operStatus", 1);
//					condition.put("standard","true");
//					condition.put("excuteIp", IpUtil.getLocalIp());
//					List<TProduct> tpList = this.serviceFactory.getTPIService().getMetadataPagingList(condition, " order by a.contentType,a.updateDate ", 1, 0);
//					if(tpList!=null&&tpList.size()>0){
//						tProduct = tpList.get(0);
//						if(tProduct.getSource().getPubType()==null || tProduct.getSource().getPubType()==1){
//							this.serviceFactory.getTPIService().generateONIXMetadata(tProduct,webRoot);
//							//移动封面和PDF
//							Map<String,Object> con = new HashMap<String,Object>();
//							con.put("sourceId", tProduct.getSource().getSource().getId());
//							con.put("pubType", 1);//适用于图书的模版
//							List<TPubTempRelation> tptList = this.serviceFactory.getConfigureService().getPubTempRelationList(con, "");
//							if(tptList!=null&&!tptList.isEmpty()&&tptList.size()==1){
//								TPubTempRelation pubTemp = tptList.get(0);
//								//封面下载
//								String remoteDir = "";
//								if(pubTemp.getReadCoverFile()!=null&&!"".equals(pubTemp.getReadCoverFile())){
//									remoteDir = pubTemp.getReadCoverFile();
//								}else{
//									remoteDir = (pubTemp.getReadFile()==null||"".equals(pubTemp.getReadFile())?Param.getParam("TPIService.info.directory.config").get("readFile"):pubTemp.getReadFile());//目标路径
//								}
//								String fileName = "";
//								if(serviceFactory.getTPIService().isExis(pubTemp.getFtpConfigure(), remoteDir,tProduct.getCode()+".jpg")){
//									fileName = tProduct.getCode()+".jpg";
//								}else if(serviceFactory.getTPIService().isExis(pubTemp.getFtpConfigure(), remoteDir,tProduct.getCode()+".bmp")){
//									fileName = tProduct.getCode()+".bmp";
//								}else if(serviceFactory.getTPIService().isExis(pubTemp.getFtpConfigure(), remoteDir,tProduct.getCode()+".gif")){
//									fileName = tProduct.getCode()+".gif";
//								}else if(serviceFactory.getTPIService().isExis(pubTemp.getFtpConfigure(), remoteDir,tProduct.getCode()+".png")){
//									fileName = tProduct.getCode()+".png";
//								}else if(serviceFactory.getTPIService().isExis(pubTemp.getFtpConfigure(), remoteDir,tProduct.getCode()+".jpeg")){
//									fileName = tProduct.getCode()+".jpg";
//								}
//								if(fileName!=null&&!"".equals(fileName)){
//									//1.移动封面
//									if(pubTemp.getRemoveCoverFile()!=null&&!"".equals(pubTemp.getRemoveCoverFile())){
//										String targetPath = remoteDir;
//										serviceFactory.getTPIService().shiftRemoteOneFile(pubTemp.getFtpConfigure(),targetPath,pubTemp.getRemoveCoverFile() ,fileName);
//									}
//								}
//								//2.移动PDF
//								if(pubTemp.getIsPDF()==2 && (tProduct.getContentType()==null || tProduct.getContentType()==1 || tProduct.getContentType()==0 )){
//									if(pubTemp.getReadPDFFile()!=null&&!"".equals(pubTemp.getReadPDFFile())){
//										remoteDir = pubTemp.getReadPDFFile();
//									}else{
//										remoteDir = (pubTemp.getReadFile()==null||"".equals(pubTemp.getReadFile())?Param.getParam("TPIService.info.directory.config").get("readFile"):pubTemp.getReadFile());//目标路径
//									}
//									fileName = "";
//									fileName = serviceFactory.getTPIService().isExistAndReturn(pubTemp.getFtpConfigure(), remoteDir,tProduct.getCode()+".pdf");
//									if(fileName!=null && !"".equals(fileName)){
//										if(pubTemp.getRemovePDFFile()!=null&&!"".equals(pubTemp.getRemovePDFFile())){
//											String targetPath = remoteDir;
//											serviceFactory.getTPIService().shiftRemoteOneFile(pubTemp.getFtpConfigure(),targetPath,pubTemp.getRemovePDFFile() ,fileName);											
//										}
//									}
//								}
//							}
//						}else if(tProduct.getSource().getPubType()==2){
//							this.serviceFactory.getTPIService().generateNLMMetadata(tProduct,webRoot);
//						}
//					}
//				}catch(Exception e){
//					Log.printError(e.getMessage());
//					try {
//						if(tProduct!=null&&tProduct.getId()!=null){
//							tProduct.setOperStatus(Integer.valueOf(e.getMessage()));
//							this.serviceFactory.getTPIService().updateTProduct(tProduct);
//						}
//					} catch (Exception e1) {
//						Log.printError(e1.getMessage());
//					}
//				}
//			}
//}
