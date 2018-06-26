package com.wevioo.utility.mail;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
public class EmailHtmlSender {
 
    @Autowired
    private EmailSender emailSender;
 
    @Autowired
    private TemplateEngine templateEngine;

    private final static String creationTemplate = "emails/creationEmail";
    private final static String passwordResetTemplate = "emails/passwordResetEmail";
    
    
    public EmailStatus send(Optional<String> from, String to, String subject, String templateName, Context context) {
        String body = templateEngine.process(templateName, context);
        return emailSender.sendHtml(from, to, subject, body);
    }
    
    
    /**
     * Send creation HTML email
     * @param from: name to set on the mail from attribute
     * @param to: email destination
     * @param subject: email subject
     * @param context: the context with variable
     * @return EmailStatus
     */
    public EmailStatus sendCreationEmail(Optional<String> from, String to, String subject, Context context) {
    	String body = templateEngine.process(creationTemplate, context);
        return emailSender.sendHtml(from, to, subject, body);
    }

    /**
     * Send reset password HTML email
     * @param from: name to set on the mail from attribute
     * @param to: email destination
     * @param subject: email subject
     * @param context: the context with variable
     * @return EmailStatus
     */
	public EmailStatus sendPasswordResetEmail(Optional<String> from, String to, String subject, Context context) {
		String body = templateEngine.process(passwordResetTemplate, context);
        return emailSender.sendHtml(from, to, subject, body);
	}
}