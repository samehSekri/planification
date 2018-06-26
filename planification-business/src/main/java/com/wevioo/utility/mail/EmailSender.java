package com.wevioo.utility.mail;

import java.text.MessageFormat;
import java.util.Optional;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.wevioo.utility.LoggerUtility;

@Component
public class EmailSender {
 
    private static final LoggerUtility LOGGER = new LoggerUtility(EmailSender.class);
 
    @Autowired
    private JavaMailSender javaMailSender;
    
    public EmailStatus sendPlainText(Optional<String>from, String to, String subject, String text) {
        return sendMail(from, to, subject, text, false);
    }
    
    public EmailStatus sendHtml(Optional<String>from, String to, String subject, String htmlBody) {
        return sendMail(from, to, subject, htmlBody, true);
    }
 
    @Async
    private EmailStatus sendMail(Optional<String>from, String to, String subject, String text, Boolean isHtml) {
        try {
            MimeMessage mail = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail, true, "UTF-8");
            if(from.isPresent()){
            	helper.setFrom(from.get());
            }
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, isHtml);
            javaMailSender.send(mail);
            LOGGER.info(MessageFormat.format("Send email {0} to: {1}", subject, to));
            return new EmailStatus(to, subject, text).success();
        } catch (Exception e) {
            LOGGER.error(MessageFormat.format("Problem with sending email to: {0}, error message: {1}", to, e.getMessage()));
            return new EmailStatus(to, subject, text).error(e.getMessage());
        }
    }
}

