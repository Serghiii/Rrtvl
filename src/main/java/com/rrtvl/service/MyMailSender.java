package com.rrtvl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
1
@Service
public class MyMailSender {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String Sender;

    public boolean send(String name, String email, String topic, String topictxt) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom(Sender);
            helper.setTo(Sender);
            helper.setText("<html><body><table style=\"border-collapse: collapse;\">\n" +
                    "<tbody>\n" +
                    "<tr style=\"background-color: #f8f8f8\">\n" +
                    "<td style=\"padding: 10px; border: #e9e9e9 1px solid; vertical-align: top;\"><b>Ім'я:</b></td>\n" +
                    "<td style=\"padding: 10px; border: #e9e9e9 1px solid;\">"+name+"</td>\n" +
                    "</tr>\n" +
                    "<tr>\n" +
                    "<td style=\"padding: 10px; border: #e9e9e9 1px solid; vertical-align: top;\"><b>E-mail:</b></td>\n" +
                    "<td style=\"padding: 10px; border: #e9e9e9 1px solid;\">"+email+"</td>\n" +
                    "</tr>\n" +
                    "<tr style=\"background-color: #f8f8f8\">\n" +
                    "<td style=\"padding: 10px; border: #e9e9e9 1px solid; vertical-align: top;\"><b>Тема&nbsp;повідомлення:</b></td>\n" +
                    "<td style=\"padding: 10px; border: #e9e9e9 1px solid;\">"+topic+"</td>\n" +
                    "</tr>\n" +
                    "<tr>\n" +
                    "<td style=\"padding: 10px; border: #e9e9e9 1px solid; vertical-align: top;\"><b>Текст&nbsp;повідомлення:</b></td>\n" +
                    "<td style=\"padding: 10px; border: #e9e9e9 1px solid;\">"+topictxt+"</td>\n" +
                    "</tr>\n" +
                    "</tbody>\n" +
                    "</table></body></html>",true);
            helper.setSubject("Зворотній зв'язок.");
            mailSender.send(message);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
