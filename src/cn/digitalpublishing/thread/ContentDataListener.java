//package cn.digitalpublishing.thread;
//
//import cn.com.daxtech.framework.util.Log;
//import cn.digitalpublishing.config.ProcessQueue;
//
//public class ContentDataListener {
//	private Counter counter = null;
//	private int i = 0;
//	
//	private String msg="";
//	
//	public ContentDataListener(){
//		counter = new Counter();
//	}
//	
//	/**
//	 * 执行数据扫描
//	 */
//	public void executeScan(){
//		if(ProcessQueue.contentDataLoad==1){
//			if(counter.getCount()==0){
//				Log.printInfo(" Begin to analyze the third product data..., number of thread is "+i);
//				msg="";
//				i++;
//				counter.countAdd();
//				Thread t = new ContentDataProcess(counter);   
//				t.start();
//			}else{
//				if(msg.trim().length()==0){
//					msg="Analyzing the third product data，please wait...";
//					Log.printInfo(msg);
//				}
//			}
//		}
//	}
//}
