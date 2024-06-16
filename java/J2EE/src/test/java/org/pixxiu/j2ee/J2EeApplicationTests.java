package org.pixxiu.j2ee;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootTest
class J2EeApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private JavaMailSender sender;
    private String Subject = "邮件标题";
    private String Content = "邮件正文";
    private String To = "3145438318@qq.com";
    private String From = "1870476411@qq.com";

    @Test
    void mailSendTest() {

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setSubject(Subject);
        mail.setText(Content);
        mail.setTo(To);
        mail.setFrom(From);
        sender.send(mail);
        System.out.println("邮件发送成功！");
    }
}
