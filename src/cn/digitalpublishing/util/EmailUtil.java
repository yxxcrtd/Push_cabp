package cn.digitalpublishing.util;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import cn.digitalpublishing.config.ProcessQueue;
/**
 * 发送邮件工具类
 * @author zhouwenqian
 *
 */

public class EmailUtil {
	/**
	 * 发送带附件及图片的邮件
	 * @param subject	邮件主题
	 * @param content	邮件正文
	 * @param imgPath	图片路径
	 * @param attchPath	附件路径
	 * @throws IOException
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public static void sendMail(String subject, String content,String imgPath,
			String attchPath,String[] addresseeArray) throws IOException, AddressException,
			MessagingException {

		Message message = getMessage(addresseeArray);
		// 设置邮件的主题
		message.setSubject(subject);
		// 创建邮件的正文
		MimeBodyPart text = new MimeBodyPart();
		// setContent(“邮件的正文内容”,”设置邮件内容的编码方式”)
		text.setContent("<img src='cid:a'></br>" + content,
				"text/html;charset=gb2312");
		// 创建图片
		MimeBodyPart img = new MimeBodyPart();
		/*
		 * JavaMail API不限制信息只为文本,任何形式的信息都可能作茧自缚MimeMessage的一部分.
		 * 除了文本信息,作为文件附件包含在电子邮件信息的一部分是很普遍的. JavaMail
		 * API通过使用DataHandler对象,提供一个允许我们包含非文本BodyPart对象的简便方法.
		 */
		DataHandler dh = new DataHandler(new FileDataSource(imgPath));
		img.setDataHandler(dh);
		// 创建图片的一个表示用于显示在邮件中显示
		img.setContentID("a");

		// 创建附件
		MimeBodyPart attch = new MimeBodyPart();
		DataHandler dh1 = new DataHandler(new FileDataSource(attchPath));
		attch.setDataHandler(dh1);
		String filename1 = dh1.getName();
		// MimeUtility 是一个工具类，encodeText（）用于处理附件字，防止中文乱码问题
		attch.setFileName(MimeUtility.encodeText(filename1));
		// 关系 正文和图片的
		MimeMultipart mm = new MimeMultipart();
		mm.addBodyPart(text);
		mm.addBodyPart(img);
		mm.setSubType("related");// 设置正文与图片之间的关系
		// 图班与正文的 body
		MimeBodyPart all = new MimeBodyPart();
		all.setContent(mm);
		// 附件与正文（text 和 img）的关系
		MimeMultipart mm2 = new MimeMultipart();
		mm2.addBodyPart(all);
		mm2.addBodyPart(attch);
		mm2.setSubType("mixed");// 设置正文与附件之间的关系

		message.setContent(mm2);
		message.saveChanges(); // 保存修改
		Transport.send(message);//发送邮件

	}
	
	
	
	/**
	 * 带附件的邮件
	 * @param subject	邮件主题
	 * @param content	邮件正文
	 * @param attchPath	附件路径
	 * @throws IOException
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public static void sendAttchMail(String subject, String content,
			String attchPath,String[] addresseeArray) throws IOException, AddressException,
			MessagingException {
		Message message = getMessage(addresseeArray);
		// 设置邮件的主题
		message.setSubject(subject);
		// 创建邮件的正文
		MimeBodyPart text = new MimeBodyPart();
		// setContent(“邮件的正文内容”,”设置邮件内容的编码方式”)
		text.setContent(content, "text/html;charset=gb2312");
		// 创建附件
		MimeBodyPart attch = new MimeBodyPart();
		DataHandler dh = new DataHandler(new FileDataSource(attchPath));
		attch.setDataHandler(dh);
		String filename1 = dh.getName();
		// MimeUtility 是一个工具类，encodeText（）用于处理附件字，防止中文乱码问题
		attch.setFileName(MimeUtility.encodeText(filename1));
		MimeMultipart mm = new MimeMultipart();
		mm.addBodyPart(text);
		MimeBodyPart all = new MimeBodyPart();
		all.setContent(mm);
		MimeMultipart mm2 = new MimeMultipart();
		mm2.addBodyPart(all);
		mm2.addBodyPart(attch);
		mm2.setSubType("mixed");// 设置正文与附件之间的关系
		message.setContent(mm2);
		message.saveChanges(); // 保存修改
		Transport.send(message);// 发送邮件

	}
	
	/**
	 * 带有图片的邮件
	 * @param subject	邮件主题
	 * @param content	邮件正文
	 * @param imgPath	图片路径
	 * @throws IOException
	 * @throws MessagingException 
	 * @throws AddressException 
	 */
	public static void sendImgMail(String subject, String content, String imgPath,String[] addresseeArray)
			throws IOException, AddressException, MessagingException {
		Message message = getMessage(addresseeArray);
		// 设置邮件的主题
		message.setSubject(subject);
		// 创建邮件的正文
		MimeBodyPart text = new MimeBodyPart();
		// setContent(“邮件的正文内容”,”设置邮件内容的编码方式”)
		text.setContent("<img src='cid:a'></br>"+content,
				"text/html;charset=gb2312");
		// 创建图片
		MimeBodyPart img = new MimeBodyPart();
		/*
		 * JavaMail API不限制信息只为文本,任何形式的信息都可能作茧自缚MimeMessage的一部分.
		 * 除了文本信息,作为文件附件包含在电子邮件信息的一部分是很普遍的. JavaMail
		 * API通过使用DataHandler对象,提供一个允许我们包含非文本BodyPart对象的简便方法.
		 */
		DataHandler dh = new DataHandler(new FileDataSource(imgPath));
		img.setDataHandler(dh);
		// 创建图片的一个表示用于显示在邮件中显示
		img.setContentID("a");
		MimeMultipart mm = new MimeMultipart();
		mm.addBodyPart(text);
		mm.addBodyPart(img);
		mm.setSubType("related");// 设置正文与图片之间的关系
		//图片与正文的 body
		MimeBodyPart all = new MimeBodyPart();
		all.setContent(mm);
		message.setContent(mm);
		message.saveChanges(); // 保存修改
		Transport.send(message);// 发送邮件

	}
	/**
	 * 普通邮件
	 * @param subject	邮件主题
	 * @param content	邮件内容
	 * @param	addresseeArray	收件人
	 * @throws IOException
	 * @throws MessagingException
	 */
	public static void sendmail(String subject, String content,String[] addresseeArray)
			throws IOException, MessagingException {
		Message message = getMessage(addresseeArray);
		// 邮件发送人
		// 邮件主题
		message.setSubject(subject);
		// 邮件内容
		message.setText(content);
		
		Transport.send(message);


	}
	/**
	 * 获取Message
	 * @return
	 * @throws IOException
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public static  Message getMessage(String[] addresseeArray) throws IOException, AddressException, MessagingException{
		
		Properties props = new Properties();
		System.out.println(ProcessQueue.AUTH);
		// 设置服务器验证
		props.setProperty("mail.smtp.auth",ProcessQueue.AUTH);
		// 设置传输协议
		props.setProperty("mail.transport.protocol", ProcessQueue.PROTOCOL);
		// 选择服务类型
		props.setProperty("mail.host", ProcessQueue.HOST);

		// 创建一个session实例
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(ProcessQueue.EMAILUSERNAME,
						ProcessQueue.EMAILPASSWORD);
			}
		});
		// 显示邮件发送过程中的交互信息
		session.setDebug(true);

		Message message = new MimeMessage(session);
		// 设置邮件的属性
		// 设置邮件的发件人
		message.setFrom(new InternetAddress(ProcessQueue.EMAILUSERNAME));

		int len = addresseeArray.length;
		InternetAddress address[] = new InternetAddress[len];
		for (int i = 0; i < addresseeArray.length; i++) {
			address[i] = new InternetAddress(addresseeArray[i]);
		}
		// 设置邮件的直接收件人 cc表示抄送 bcc 表示暗送
		message.addRecipients(Message.RecipientType.TO, address);
		
		return message;
	}
	
}
