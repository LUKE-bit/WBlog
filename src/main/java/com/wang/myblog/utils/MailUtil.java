package com.wang.myblog.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Component
public class MailUtil {

    private static final String FROM = "1051443771@qq.com";
    @Autowired
    private JavaMailSender mailSender;

    public  void sendMail(String to, String message) throws MessagingException {
        String address = " http://47.94.156.241:8080/statusCode?code="+message;
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setSubject("激活账号");
        mimeMessageHelper.setFrom(FROM);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setText("点击完成激活账号，激活码的有效时间为一分钟，只有账号激活才能使用其他功能，<a href = "+address+">激活账号</a>",true);
        mailSender.send(mimeMessage);
    }
}
