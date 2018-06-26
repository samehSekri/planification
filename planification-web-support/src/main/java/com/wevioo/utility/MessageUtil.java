package com.wevioo.utility;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageUtil {

	@Autowired
	private MessageSource messageSource;

	public String getMessage(String id) {
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage(id, null, locale);
	}

	public String getMessage(String id, Object[] args) {
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage(id, args, locale);
	}

}