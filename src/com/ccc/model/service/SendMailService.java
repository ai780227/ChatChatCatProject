package com.ccc.model.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.ccc.model.bean.MemberBean;

public class SendMailService {
	private static final String smtp_host = "smtp.gmail.com";// 發送郵件伺服器
	private static final String from_userName = "chatchatcat.service@gmail.com";// 寄件人帳號
	private static final String from_passWord = "chatchatcat";// 寄件人密碼
	private static final String sendSubject = "ChatChatCat社群網站會員通知";// 主题

	public static void sendMail(MemberBean mem,String Newpwd) {
		try {
			Properties props = System.getProperties(); // 現在的大部分smpt都需要驗證 
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", smtp_host);
			props.put("mail.smtp.user", from_userName);
			props.put("mail.smtp.password", from_passWord);
			props.setProperty("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
			props.setProperty("mail.smtp.socketFactory.fallback", "false");
			props.setProperty("mail.smtp.port", "465"); // 發送端prot
			props.setProperty("mail.smtp.socketFactory.port", "465"); // 發送端port
			props.put("mail.smtp.auth", "true");
			String[] to ={mem.getM_email(), "chatchatcat.service@gmail.com"}; // 收件人
			Session session = Session.getDefaultInstance(props, null);
			session.setDebug(true);
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from_userName));
			InternetAddress[] toAddress = new InternetAddress[to.length]; // 獲取地址的array
			for (int i = 0; i < to.length; i++) {
				toAddress[i] = new InternetAddress(to[i]);
			}
//			System.out.println(Message.RecipientType.TO);
			for (int i = 0; i < toAddress.length; i++) {
				message.addRecipient(Message.RecipientType.TO, toAddress[i]);
			}
			message.setSubject(sendSubject);
			//內容
			String sendTextAll="親愛的用戶 " + mem.getM_name() + " 您好：<br><br>";
			sendTextAll += "<h3>我們已將您的密碼修改為：  <b>"+Newpwd+"</b></h3><br>";
			sendTextAll += "請使用此密碼登入<br><br>";
			sendTextAll += "<a href='http://localhost:8080/ChatChatCatProject/index.jsp'>ChatChatCat社群網站</a><br><br>";
			sendTextAll += "再將您的密碼修改<br><br>";
			sendTextAll += "謝謝您<br><br>";
			sendTextAll += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ChatChatCat社群網站服務人員<br><hr>";
//			String img="http://localhost:8080/VideoProject/images/logo.png";
//			sendTextAll+="<img src=\"http://localhost:8080/VideoProject/images/logo.png\" alt='logo'width='100%' height='100px'>";
			message.setContent(sendTextAll, "text/html;charset=utf-8");
			Transport transport = session.getTransport("smtp");
			transport.connect(smtp_host, from_userName, from_passWord);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
//			System.out.println("郵件發送成功!");
		} catch (Exception e) {
//			System.out.println("郵件發送失敗! ");
		}
	}
	
	public static void main(String[] args) {
//		sendMail("jack2431@hotmail.com","TEST");
	}
}
