package cn.digitalpublishing.config;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class StartInit implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		try {
			getProps();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void getProps() throws IOException {
		Properties props = new Properties();
		try {
			props.load(new InputStreamReader(StartInit.class.getClassLoader().getResourceAsStream("mail.properties"), "UTF-8"));         
			ProcessQueue.EMAILUSERNAME = props.getProperty("addresserEmailUsername") ;
			ProcessQueue.EMAILPASSWORD = props.getProperty("addresserEmailPassword");
			ProcessQueue.EMAILSUBJECT = props.getProperty("emailSubject");
			ProcessQueue.AUTH = props.getProperty("mail.smtp.auth");
			ProcessQueue.PROTOCOL = props.getProperty("mail.transport.protocol");
			ProcessQueue.HOST = props.getProperty("mail.host");
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

}
