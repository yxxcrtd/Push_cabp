package cn.digitalpublishing.thread;

import cn.digitalpublishing.config.ProcessQueue;

public class PushListener {

	private Counter counter = null;
	
	private int i = 0;
	
	private String msg="";
	
	public PushListener(){
		counter = new Counter();
	}
	
	/**
	 * 执行数据扫描
	 */
	public void executeScan(){
		if(ProcessQueue.PUSHLOAD==1){
			if(counter.getCount()==0){
				System.out.println("Open Push thread "+i+"！");
				System.out.println("开启推送线程"+i+"！");
				msg="";
				i++;
				counter.countAdd();
				Thread t = new PushProcess(counter);   
				t.start();
			}else{
				if(msg.trim().length()==0){
					msg="有推送线程执行，等待......";
					System.out.println(msg);
				}
			}
		}
	}
	
}
