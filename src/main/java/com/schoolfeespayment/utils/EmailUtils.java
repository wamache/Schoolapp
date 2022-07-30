package com.schoolfeespayment.utils;


import com.schoolfeespayment.POJO.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

@Component
@Service
public class EmailUtils {

    @Autowired
    private JavaMailSender emailSender;



    public void sendSimpleMessage(String to, String subject, String text, List<String> list) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText(text);
        message.setTo(to);
        message.setSubject(subject);
        message.setFrom("benardwamache@gmail.com");
        if (list != null && list.size() > 0)
            message.setCc(getCcArray(list));
        emailSender.send(message);


    }

    private  String[] getCcArray(List<String> ccList) {
        String[] cc =new String[ccList.size()];
        for(int i = 0;i<ccList.size(); i++){
            cc[i] = ccList.get(i);
        }
        return cc;
    }

    public void forgotMail(String to, String subject, String password) throws MessagingException{
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            //helper.setText(text);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom("hello@gmail.com");
            String htmlMsg = "<p><b> Your Login details</b><br><b>Email:</b>"+ to+ "<br><b>Password:</b>" +password+ "<br><a href=\"http://localhost:4200/\"> Click here to login</a></p>";
            message.setContent(htmlMsg, "text/html");
            emailSender.send(message);
        } catch (MessagingException e) {
            LOGGER.error("failed to send email", e);
            throw new IllegalStateException("failed to send email");
        }
    }

}