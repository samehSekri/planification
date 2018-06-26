package com.wevioo.security.config.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.integration.support.json.Jackson2JsonObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.wevioo.exception.ApiException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;

// Hedhi hiya l entry point of our auth , the commence will handle the http request , 
// Kél 3ada :p apiError will catch the exception and map it to send it as json object 

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

	private static final long serialVersionUID = -8970718410437077606L;

	@Autowired
	private Jackson2JsonObjectMapper jackson2JsonObjectMapper;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
			throws IOException {

		ApiException error = new ApiException(HttpStatus.FORBIDDEN, e.getLocalizedMessage(), e.getMessage());

		try {
			String json = jackson2JsonObjectMapper.toJson(error);
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
			response.getWriter().write(json);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}
}