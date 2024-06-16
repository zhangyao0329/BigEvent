package org.pixxiu.j2ee.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.Setter;
import org.pixxiu.j2ee.pojo.Email;
import org.pixxiu.j2ee.utils.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@Tag(name = "邮件发送接口")
@RestController
@RequestMapping("/sendMail")
public class EmailSendController {

    @Autowired
    private EmailUtil emailUtil;
    @Autowired
    private JavaMailSender sender;

    private String Subject = "cc";
    private String Content = "cc";
    private String To = "3145438318@qq.com";


    @Operation(summary = "纯文本邮件发送")
    @PostMapping("/sendTextEmail")
    public String sendTextEmail(@RequestBody Email request) {
        emailUtil.sendMessage(request.getTo(), request.getSubject(), request.getContent());
        return "Text email sent successfully!";
    }

    @Operation(summary = "循环发送邮件")
    @PostMapping("/sendEmails")
    public String sendMails() {

        for (int i = 0; i < 100; i++) {
            try {
                System.out.println("开始发送邮件" + "发送了" + i + "次");
                Thread.sleep(1);
                emailUtil.sendMessage(To, Subject, Content);
                System.out.println("邮件发送成功！");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "Emails sent successfully!";
    }


}
