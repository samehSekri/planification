package com.wevioo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wevioo.dto.JwtAuthenticationResponseDto;
import com.wevioo.dto.UserDto;
import com.wevioo.exception.ApiException;
import com.wevioo.model.User;
import com.wevioo.security.config.jwt.JwtAuthenticationRequest;
import com.wevioo.security.utility.JwtTokenUtil;
import com.wevioo.service.UserService;
import com.wevioo.utility.MessageUtil;
import com.wevioo.utility.mail.EmailStatus;

@RestController
public class AuthenticationRestController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private MessageUtil messageUtil;
    
    @RequestMapping(value = "${jwt.route.authentication.path}", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest, Device device) throws AuthenticationException {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails, device);

        return ResponseEntity.ok(new JwtAuthenticationResponseDto(token, (UserDto) userDetails));
    }

    @RequestMapping(value = "${jwt.route.authentication.refresh}", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        UserDto user = (UserDto) userDetailsService.loadUserByUsername(username);

        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            return ResponseEntity.ok(new JwtAuthenticationResponseDto(refreshedToken, user));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
	 * PUT /users : reset password of an existing User.
	 *
	 */
	@RequestMapping(value= "${jwt.route.password.reset}", method = RequestMethod.PUT)
	public @ResponseBody Object resetUserPassword(@RequestBody JwtAuthenticationRequest authenticationRequest, Device device, HttpServletRequest request) {
		User user = userService.findUserByEmailAndUsername(authenticationRequest.getPassword(), authenticationRequest.getUsername());
		
		if (user != null && !user.getId().equals(null)) {
			String emailResetSubject = messageUtil.getMessage("email.reset.title");
			EmailStatus emailStatus = userService.resetPassword(user, emailResetSubject, request.getLocalAddr());
			if(emailStatus.isSuccess()){
				return user;
			}else{
				ApiException error = new ApiException(HttpStatus.CONFLICT, "error.reset.password.send.email.failed",
						messageUtil.getMessage("error.reset.password.send.email.failed"));
				return new ResponseEntity<Object>(error, new HttpHeaders(), error.getStatus());
			}
		}
		
		ApiException error = new ApiException(HttpStatus.CONFLICT, "error.reset.password.credential.error", messageUtil.getMessage("error.reset.password.credential.error"));
		return new ResponseEntity<Object>(error, new HttpHeaders(), error.getStatus());
	}
}
