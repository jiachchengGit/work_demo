package com.jccdemo.email;

import org.apache.commons.lang.CharEncoding;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.UrlResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2017/7/12 上午11:25
 **/
public class MailSendUtils {

    private static String SM_EAMIL_HOST = "mail.sm.vvip-u.com";

    private static volatile MailSendUtils mailSendUtils = new MailSendUtils();

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private JavaMailSenderImpl javaMailSender;

    private SimpleMailMessage simpleMailMessage;

    private MailSendUtils() {

        javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setDefaultEncoding(CharEncoding.UTF_8);
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.timeout", 25000);
        properties.put("mail.smtp.host", SM_EAMIL_HOST);
//        properties.put("mail.transport.protocol","imap");
//        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.store.protocol", "imap");
        properties.put("mail.debug",true);
        javaMailSender.setJavaMailProperties(properties);
    }

    public SimpleMailMessage getSimpleMailMessage() {
        return simpleMailMessage;
    }

    public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
        this.simpleMailMessage = simpleMailMessage;
    }

    public static MailSendUtils getInstance() {
        return mailSendUtils;
    }

    /**
     * 发送邮件
     * @param mail
     */
    public void sendEmail(MailModel mail) {

        logger.info("------------开始发送邮件----------");
//        if (StringUtils.isEmpty(mail.getEmailHost())) {
//            throw new IllegalArgumentException("发送服务器不能我空");
//        }
        if (StringUtils.isEmpty(mail.getEmailUserName())) {
            throw new IllegalArgumentException("发送人用户名不能为空");
        }
        if (StringUtils.isEmpty(mail.getEmailPassword())) {
            throw new IllegalArgumentException("发送服密码不能我空");
        }
        if(mail.getEmailHost() != null){
            javaMailSender.setHost(mail.getEmailHost());
        }
        javaMailSender.setUsername(mail.getEmailUserName());
        javaMailSender.setPassword(mail.getEmailPassword());

        // 建立邮件消息
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper;
        try {
            messageHelper = new MimeMessageHelper(message, true, "UTF-8");
            // 设置发件人邮箱
            if (mail.getEmailFrom() != null) {
                messageHelper.setFrom(mail.getEmailFrom());
            } else {
                messageHelper.setFrom(simpleMailMessage.getFrom());
            }

            // 设置收件人邮箱
            if (mail.getToEmails() != null) {
                String[] toEmailArray = mail.getToEmails().split(";");
                List<String> toEmailList = new ArrayList<String>();
                if (null == toEmailArray || toEmailArray.length <= 0) {
                    throw new RuntimeException("收件人邮箱不得为空！");
                } else {
                    for (String s : toEmailArray) {
                        if (s != null && !s.equals("")) {
                            toEmailList.add(s);
                        }
                    }
                    if (null == toEmailList || toEmailList.size() <= 0) {
                        throw new RuntimeException("收件人邮箱不得为空！");
                    } else {
                        toEmailArray = new String[toEmailList.size()];
                        for (int i = 0; i < toEmailList.size(); i++) {
                            toEmailArray[i] = toEmailList.get(i);
                        }
                    }
                }
                messageHelper.setTo(toEmailArray);
            } else {
                messageHelper.setTo(simpleMailMessage.getTo());
            }

            // 邮件主题
            if (mail.getSubject() != null) {
                messageHelper.setSubject(mail.getSubject());
            } else {
                messageHelper.setSubject(simpleMailMessage.getSubject());
            }

            // true 表示启动HTML格式的邮件
            messageHelper.setText(mail.getContent(), true);

            // 添加图片
            if (null != mail.getPictures() && mail.getPictures().size() > 0) {
                for (Iterator<Map.Entry<String, String>> it = mail.getPictures().entrySet().iterator(); it.hasNext(); ) {
                    Map.Entry<String, String> entry = it.next();
                    String cid = entry.getKey();
                    String filePath = entry.getValue();
                    if (null == cid || null == filePath) {
                        throw new RuntimeException("请确认每张图片的ID和图片地址是否齐全！");
                    }

                    if (StringUtils.isEmpty(filePath)) {
                        throw new RuntimeException("图片" + filePath + "不能为空！");
                    }
                    if(filePath.startsWith("http")){
                        UrlResource img = new UrlResource(filePath);
                        messageHelper.addInline(cid, img);
                    }else {
                        FileSystemResource fsr  = new FileSystemResource(filePath);
                        messageHelper.addInline(cid,fsr);
                    }
                }
            }

            // 添加附件
            if (null != mail.getAttachments() && mail.getAttachments().size() > 0) {
                for (Iterator<Map.Entry<String, String>> it = mail.getAttachments().entrySet().iterator(); it.hasNext(); ) {
                    Map.Entry<String, String> entry = it.next();
                    String cid = entry.getKey();
                    String filePath = entry.getValue();
                    if (null == cid || null == filePath) {
                        throw new RuntimeException("请确认每个附件的ID和地址是否齐全！");
                    }
                    if (StringUtils.isEmpty(filePath)) {
                        throw new RuntimeException("附件" + filePath + "不能为空！");
                    }
//                    UrlResource fileResource = new UrlResource(filePath);
//                    messageHelper.addAttachment(cid, fileResource);
                    if(filePath.startsWith("http")){
                        UrlResource img = new UrlResource(filePath);
                        messageHelper.addAttachment(cid, img);
                    }else {
                        FileSystemResource fsr  = new FileSystemResource(filePath);
                        messageHelper.addAttachment(cid,fsr);
                    }
                }
            }
            messageHelper.setSentDate(new Date());
            // 发送邮件
            javaMailSender.send(message);
            Session session = javaMailSender.getSession();
            save2SendBox(mail, message, session);
            logger.info("------------发送邮件完成----------");
        } catch (MessagingException e) {
            logger.error("发送邮件错误信息：", e);
            throw new RuntimeException("邮件发送失败");
        } catch (Exception ex) {
            logger.error("错误信息：{}" , ex);
            throw new RuntimeException("邮件发送失败");
        }
    }

    private void save2SendBox(MailModel mail, MimeMessage message, Session session)
                        throws MessagingException {
        Store store = session.getStore("imap");
        store.connect(SM_EAMIL_HOST,mail.getEmailFrom(),mail.getEmailPassword());
        Folder folder = store.getFolder("Sent");
        if (!folder.exists()) {
            folder.create(Folder.HOLDS_MESSAGES);
        }
        folder.open(Folder.READ_WRITE);
        folder.appendMessages(new Message[]{message});
        folder.expunge();
        folder.close(true);
        store.close();
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
//        mm.addPic("20170704152434.jpg","F:/私人文件/20170704152434.jpg");
//        mm.addAtta("公积金接收函范本.doc","F:/公积金接收函范本.doc");
//        sendEmail(mm);
        MailSendUtils.getInstance().sendEmail(mm);
    }
}
