package org.pixxiu.j2ee.utils;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class EmailUtil {
    @Autowired
    private JavaMailSender mailSender;

    @Setter
    @Getter
    @Value("${spring.mail.properties.mail.from}")
    private String from;

    /*纯文本邮件发送*/
    public void sendMessage(String to, String subject, String content) {
        // 创建一个邮件对象
        SimpleMailMessage msg = new SimpleMailMessage();
        // 设置发送发
        msg.setFrom(from);
        // 设置接收方
        msg.setTo(to);
        // 设置邮件主题
        msg.setSubject(subject);
        // 设置邮件内容
        msg.setText(content);
        // 发送邮件
        mailSender.send(msg);
    }

    /*带附件发送*/

    /**
     * 发送带附件的邮件信息
     *
     * @param to      接收方
     * @param subject 邮件主题
     * @param content 邮件内容（发送内容）
     * @param files   文件数组 // 可发送多个附件
     */
    public void sendMessageCarryFiles(String to, String subject, String content, File[] files) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            // 设置发送发
            helper.setFrom(from);
            // 设置接收方
            helper.setTo(to);
            // 设置邮件主题
            helper.setSubject(subject);
            // 设置邮件内容
            helper.setText(content);
            // 添加附件（多个）
            if (files != null && files.length > 0) {
                for (File file : files) {
                    helper.addAttachment(file.getName(), file);
                }
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        // 发送邮件
        mailSender.send(mimeMessage);
    }

    /**
     * 发送带附件的邮件信息
     *
     * @param to      接收方
     * @param subject 邮件主题
     * @param content 邮件内容（发送内容）
     * @param file    单个文件
     */
    public void sendMessageCarryFile(String to, String subject, String content, File file) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            // 设置发送发
            helper.setFrom(from);
            // 设置接收方
            helper.setTo(to);
            // 设置邮件主题
            helper.setSubject(subject);
            // 设置邮件内容
            helper.setText(content);
            // 单个附件
            helper.addAttachment(file.getName(), file);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        // 发送邮件
        mailSender.send(mimeMessage);
    }

    /**
     * 发送html的邮件信息
     *
     * @param to      接收方
     * @param subject 邮件主题
     * @param content 邮件内容（发送内容）
     */
    public void sendMessageHTML(String to, String subject, String content) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            // 设置发送发
            helper.setFrom(from);
            // 设置接收方
            helper.setTo(to);
            // 设置邮件主题
            helper.setSubject(subject);
            // 设置邮件内容
            helper.setText(content, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        // 发送邮件
        mailSender.send(mimeMessage);
    }

}


