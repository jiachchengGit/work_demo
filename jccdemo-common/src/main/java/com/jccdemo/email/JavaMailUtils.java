package com.jccdemo.email;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Created by chenjiacheng on 2017-07-14.
 */
public class JavaMailUtils {
    private static Properties props = new Properties();
    private final static  String EMAIL_SERVER_HOST = "mail.sm.vvip-u.com";
    static {
        // 发送邮件的服务器的IP和端口
        props.put("mail.smtp.host", EMAIL_SERVER_HOST);
        props.put("mail.smtp.port", 25);
        props.put("mail.smtp.auth", "true");
        props.put("mail.transport.protocol","imap");
//        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    }

    public static void sendEmail(final MailModel model) throws Exception {

        Session session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                // 登陆邮件发送服务器的用户名和密码
                return new PasswordAuthentication(model.getEmailUserName(), model.getEmailPassword());
            }
        });
        MimeMessage message = new MimeMessage(session);     // 创建邮件对象
        message.setFrom(new InternetAddress(model.getEmailFrom()));
        String[] toEmails = model.getToEmails().split(";");
        for(String to:toEmails){
            message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
        }
        message.setSubject(model.getSubject(), "UTF-8");
        message.setContent(model.getContent(), "text/html;charset=UTF-8");
        message.setSentDate(new Date());

        MimeMultipart contentPart = new MimeMultipart("mixed");
        message.setContent(contentPart);
        if(model.getAttachments() != null){
            Set<Map.Entry<String, String>> attas = model.getAttachments().entrySet();
            for(Map.Entry<String, String> at:attas){
                String atKey = at.getKey();
                String atValue = at.getValue();
                MimeBodyPart attaPart = new MimeBodyPart();
                attaPart.setDisposition(MimeBodyPart.ATTACHMENT);
                attaPart.setFileName(MimeUtility.encodeText(atKey));
                attaPart.setDataHandler(new DataHandler(new FileDataSource(new File(atValue))));
                contentPart.addBodyPart(attaPart);
            }
        }

        //正文内容
        MimeBodyPart content = new MimeBodyPart();
        contentPart.addBodyPart(content);

        MimeMultipart bodyMultipart  = new MimeMultipart("related");
        content.setContent(bodyMultipart);


        if(model.getPictures() != null){
            Set<Map.Entry<String, String>> entries = model.getPictures().entrySet();
            for(Map.Entry<String, String> pic:entries){
                String picKey = pic.getKey();
                String picValue = pic.getValue();
                MimeBodyPart picBodyPart = new MimeBodyPart();
                picBodyPart.setDisposition(MimeBodyPart.INLINE);
                picBodyPart.setHeader("Content-Location", "http://" + picKey + "");
                picBodyPart.setFileName(picKey);
                picBodyPart.setDataHandler(new DataHandler(new FileDataSource(new File(picValue))));
                bodyMultipart.addBodyPart(picBodyPart);
            }
        }
        MimeBodyPart htmlPart = new MimeBodyPart();
        htmlPart.setContent(model.getContent(),"text/html;charset=UTF-8");
        bodyMultipart.addBodyPart(htmlPart);
        message.saveChanges();

        //save to send box
        saveToSendBox(model, session, message);
        System.out.println("----email send yes-------");
    }

    private static void saveToSendBox(MailModel model, Session session, MimeMessage message) throws MessagingException {
        Store store = session.getStore("imap");
        store.connect(EMAIL_SERVER_HOST,model.getEmailUserName(),model.getEmailPassword());
        Folder folder = store.getFolder("Sent");
        if (!folder.exists()) {
            folder.create(Folder.HOLDS_MESSAGES);
        }
        folder.open(Folder.READ_WRITE);
        folder.appendMessages(new Message[]{message});
        folder.expunge();
        folder.close(true);
        store.close();
        Transport transport = session.getTransport("smtp");
        transport.connect();
        transport.send(message);
    }

    public static void main(String[] args) throws Exception {
        MailModel mm = new MailModel();
        String userName = "finance@sm.vvip-u.com";
        mm.setEmailUserName(userName);
        mm.setEmailPassword("finance");
        mm.setSubject("Chen jiacheng Test");
        mm.setContent("邮件正文测试");
        mm.setEmailFrom(userName);
        mm.setToEmails("chenjiacheng@sm.vvip-u.com");
        mm.addPic("20170704152434.jpg","F:/私人文件/20170704152434.jpg");
        mm.addAtta("公积金接收函范本.doc","F:/公积金接收函范本.doc");
        sendEmail(mm);
    }
}
