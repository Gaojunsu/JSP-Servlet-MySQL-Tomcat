package web.tools;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class MailUtils {

	//发件人地址
	public static String senderAddress = "xxxx@***.com";
	//收件人地址
	public static String recipientAddress = "xxxx@****com";
	//发件人账户名
	public static String senderAccount = "xxxx@****.com";
	//发件人账户密码
	public static String senderPassword = "*******";

	public static void main(String[] args) throws Exception {
		//1、连接邮件服务器的参数配置
		Properties props = new Properties();
		//设置用户的认证方式
		props.setProperty("mail.smtp.auth", "true");
		//设置传输协议
		props.setProperty("mail.transport.protocol", "smtp");
		//设置发件人的SMTP服务器地址
		props.setProperty("mail.smtp.host", "smtp.xxxx.com");
		//2、创建定义整个应用程序所需的环境信息的 Session 对象
		Session session = Session.getInstance(props);
		//设置调试信息在控制台打印出来
		session.setDebug(true);
		//3、创建邮件的实例对象
		Message msg = getMimeMessage(session);
		//4、根据session对象获取邮件传输对象Transport
		Transport transport = session.getTransport();
		//设置发件人的账户名和密码
		transport.connect(senderAccount, senderPassword);
		//发送邮件，并发送到所有收件人地址，message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
		transport.sendMessage(msg,msg.getAllRecipients());

		//如果只想发送给指定的人，可以如下写法
		//transport.sendMessage(msg, new Address[]{new InternetAddress("xxx@qq.com")});

		//5、关闭邮件连接
		transport.close();
	}

	/**
	 * 获得创建一封邮件的实例对象
	 * @param session
	 * @return
	 * @throws MessagingException
	 * @throws AddressException
	 */
	public static MimeMessage getMimeMessage(Session session) throws Exception{
		//创建一封邮件的实例对象
		MimeMessage msg = new MimeMessage(session);
		//设置发件人地址
		msg.setFrom(new InternetAddress(senderAddress));
		/**
		 * 设置收件人地址（可以增加多个收件人、抄送、密送），即下面这一行代码书写多行
		 * MimeMessage.RecipientType.TO:发送
		 * MimeMessage.RecipientType.CC：抄送
		 * MimeMessage.RecipientType.BCC：密送
		 */
		msg.setRecipient(MimeMessage.RecipientType.TO,new InternetAddress(recipientAddress));
		//设置邮件主题
		msg.setSubject("邮件主题","UTF-8");
		//设置邮件正文
		msg.setContent("简单的纯文本邮件！", "text/html;charset=UTF-8");
		//设置邮件的发送时间,默认立即发送
		msg.setSentDate(new Date());

		return msg;
	}




	/*public static void sendMail(String email, String emailMsg)
			throws AddressException, MessagingException {
		// 1.创建一个程序与邮件服务器会话对象 Session

		Properties props = new Properties();


		//设置发送的协议
		props.setProperty("mail.transport.protocol", "smtp");

		//设置发送邮件的服务器
		props.setProperty("mail.host", "smtp.office365.com");
		props.setProperty("mail.smtp.auth", "true");// 指定验证为true

		// 创建验证器
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				//设置发送人的帐号和密码
				return new PasswordAuthentication("jean_su@hanbo.com", "13790@hel");
			}
		};

		Session session = Session.getInstance(props, auth);

		// 2.创建一个Message，它相当于是邮件内容
		Message message = new MimeMessage(session);

		//设置发送者
		message.setFrom(new InternetAddress("jean_su@hanbo.com"));

		//设置发送方式与接收者
		message.setRecipient(RecipientType.TO, new InternetAddress(email)); 

		//设置邮件主题
		message.setSubject("用户激活");
		// message.setText("这是一封激活邮件，请<a href='#'>点击</a>");

		String url="http://localhost:8080/store_v4/UserServlet?method=active&code="+emailMsg;
		String content="<h1>来自购物天堂的激活邮件!激活请点击以下链接!</h1><h3><a href='"+url+"'>"+url+"</a></h3>";
		//设置邮件内容
		message.setContent(content, "text/html;charset=utf-8");

		// 3.创建 Transport用于将邮件发送
		Transport.send(message,message.getAllRecipients());


	}
	public static void main(String[] args) throws AddressException, MessagingException {

		MailUtils.sendMail("jim_zhang@hanbo.com", "1234567890");

		
	}*/
}
