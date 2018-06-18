/**
 * 
 */
package cn.molasoftware.core.util;

import java.io.File;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

/**
 * @author caizx
 * 
 */
public class EmailUtil {

	public static void sendEmail(String hostName, int port, String fromeEmail, String[] toEmails, String subject, String massege) throws Exception{
		Email email = createEmail("SimpleEmail", hostName, port, fromeEmail, toEmails);
		email.setSubject(subject);
		email.setMsg(massege);
		email.send();
	}
	
	public static void sendEmail(String hostName, int port, String userName, String password, String fromeEmail, String[] toEmails, String subject, String massege) throws Exception {
		Email email = createEmail("SimpleEmail", hostName, port, userName, password, fromeEmail, toEmails);
		email.setSubject(subject);
		email.setMsg(massege);
		email.send();
	}


	public static void sendFileEmail(String hostName, int port, String fromeEmail, String[] toEmails, String subject, String massege, File[] files) throws Exception{
		if(files==null||files.length==0){
			sendEmail(hostName, port, fromeEmail, toEmails, subject, massege);
		}else{
			MultiPartEmail email = (MultiPartEmail)createEmail("MultiPartEmail", hostName, port, fromeEmail, toEmails);
			email.setSubject(subject);
			email.setMsg(massege);
			for(File file : files){
				email.attach(file);
			}
			email.send();
		}
	}
	
	public static void sendFileEmail(String hostName, int port, String userName, String password, String fromeEmail, String[] toEmails, String subject, String massege, File[] files) throws Exception{
		if(files==null||files.length==0){
			sendEmail(hostName, port, userName, password, fromeEmail, toEmails, subject, massege);
		}else{
			MultiPartEmail email = (MultiPartEmail)createEmail("MultiPartEmail", hostName, port, userName, password, fromeEmail, toEmails);
			email.setSubject(subject);
			email.setMsg(massege);
			for(File file : files){
				email.attach(file);
			}
			email.send();
		}
	}
	
	public static void sendHTMLEmail(String hostName, int port, String fromeEmail, String[] toEmails, String subject, String massege) throws Exception{
		MultiPartEmail email = (MultiPartEmail)createEmail("HtmlEmail", hostName, port, fromeEmail, toEmails);
		email.setSubject(subject);
		email.setMsg(massege);
		email.send();
	}
	
	public static void sendHTMLEmail(String hostName, int port, String fromeEmail, String[] toEmails, String subject, String massege, File[] files) throws Exception{
		if(files==null||files.length==0){
			sendHTMLEmail(hostName, port, fromeEmail, toEmails, subject, massege);
		}else{
			MultiPartEmail email = (MultiPartEmail)createEmail("HtmlEmail", hostName, port, fromeEmail, toEmails);
			email.setSubject(subject);
			email.setMsg(massege);
			for(File file : files){
				email.attach(file);
			}
			email.send();
		}
	}
	
	public static void sendHTMLEmail(String hostName, int port, String userName, String password, String fromeEmail, String[] toEmails, String subject, String massege) throws Exception{
		MultiPartEmail email = (MultiPartEmail)createEmail("HtmlEmail", hostName, port, userName, password, fromeEmail, toEmails);
		email.setSubject(subject);
		email.setMsg(massege);
		email.send();
	}
	
	public static void sendHTMLEmail(String hostName, int port, String userName, String password, String fromeEmail, String[] toEmails, String subject, String massege, File[] files) throws Exception{
		if(files==null||files.length==0){
			sendHTMLEmail(hostName, port, userName, password, fromeEmail, toEmails, subject, massege);
		}else{
			MultiPartEmail email = (MultiPartEmail)createEmail("HtmlEmail", hostName, port, userName, password, fromeEmail, toEmails);
			email.setSubject(subject);
			email.setMsg(massege);
			for(File file : files){
				email.attach(file);
			}
			email.send();
		}
	}
	
	private static Email createEmail(String mailType, String hostName, int port, String fromeEmail, String[] toEmails) throws Exception{
		Email email = null;
		if(mailType.equals("SimpleEmail")){
			email = new SimpleEmail();
		}else if(mailType.equals("MultiPartEmail")){
			email = new MultiPartEmail ();
		}else if(mailType.equals("HtmlEmail")){
			email = new HtmlEmail ();			
		}
		email.setCharset("UTF-8");// 必须放在前面，否则乱码
		email.setHostName(hostName);
		email.setSmtpPort(port);
		email.setFrom(fromeEmail);
		email.addTo(toEmails);
		return email;
	}
	
	private static Email createEmail(String mailType, String hostName, int port, String userName, String password, String fromeEmail, String[] toEmails) throws Exception{
		Email email = createEmail(mailType, hostName, port, fromeEmail, toEmails);
		email.setAuthentication(userName, password);
		email.setSSLOnConnect(true);
		return email;
	}
}
