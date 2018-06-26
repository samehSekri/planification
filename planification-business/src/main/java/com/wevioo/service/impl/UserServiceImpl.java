package com.wevioo.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;
import com.wevioo.dao.RoleRepository;
import com.wevioo.dao.UserRepository;
import com.wevioo.dto.UserDto;
import com.wevioo.model.User;
import com.wevioo.model.enumeration.RoleNameEnum;
import com.wevioo.service.UserService;
import com.wevioo.utility.LoggerUtility;
import com.wevioo.utility.RandomUtil;
import com.wevioo.utility.mail.EmailHtmlSender;
import com.wevioo.utility.mail.EmailStatus;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ModelMapper userMapper;

	@Autowired
	private EmailHtmlSender emailHtmlSender;

	@Value("${server.port}")
	private String serverPort;

	@Value("${wevioo.mail.from:no_replay@wevioo.com}")
	private String emailFrom;

	private LoggerUtility LOGGER = new LoggerUtility(UserServiceImpl.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UserDto> findAllUsers() {
		List<User> users = userRepository.findAll();
		if (users != null && users.size() > 0) {
			List<UserDto> list = new ArrayList<UserDto>();

			for (User user : users) {
				UserDto dto = new UserDto(user.getId(), user.getUsername(), user.getFirstname(), user.getLastname(),
						user.getEmail(), user.getPassword(), user.getAuthorities(), user.isEnabled(),
						user.getLastPasswordResetDate());

				list.add(dto);
			}
			return list;
		}

		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserDto findUserByUsername(String username) {
		if (username != null && !username.isEmpty()) {
			User user = userRepository.findByUsername(username);
			if (user != null) {
				return userMapper.map(user, UserDto.class);
			}
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserDto findUserById(Long id) {
		if (id != null) {
			User user = userRepository.findOne(id);
			if (user != null) {
				return userMapper.map(user, UserDto.class);
			}
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public User saveAndGetUser(User user) {
		return userRepository.saveAndFlush(user);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public UserDto changeUserStatus(Long userId) {
		if (userId != null) {
			User user = userRepository.findOne(userId);
			if (user != null) {
				user.setEnabled(!user.isEnabled());
				user = saveAndGetUser(user);
				return userMapper.map(user, UserDto.class);
			}
		}
		return null;
	}

	@Override
	public UserDto findUserByEmail(String email) {
		if (email != null && !email.isEmpty()) {
			User user = userRepository.findByEmail(email);
			if (user != null) {
				return userMapper.map(user, UserDto.class);
			}
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User convertUserDtoToUser(UserDto user) {
		if (user != null) {
			return userMapper.map(user, User.class);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserDto convertUserToUserDto(User user) {
		if (user != null) {
			return userMapper.map(user, UserDto.class);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserDto saveAndGetUser(UserDto userDto) {
		if (userDto != null) {
			User user = userMapper.map(userDto, User.class);
			user = saveAndGetUser(user);
			return userMapper.map(user, UserDto.class);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional
	@Override
	public UserDto updateUser(UserDto userDto) {
		if (userDto != null) {
			User user = userRepository.findOne(userDto.getId());

			user.setUsername(userDto.getUsername());
			user.setFirstname(userDto.getFirstname());
			user.setLastname(userDto.getLastname());
			user.setEmail(userDto.getEmail());
			user.setRole(roleRepository.findByName(userDto.getRoleName()));

			if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()
					&& !user.getPassword().equals(userDto.getPassword())) {
				String password = passwordEncoder.encode(userDto.getPassword());
				user.setPassword(password);
				// user.setLastPasswordResetDate(Calendar.getInstance().getTime());
			}
			user = userRepository.saveAndFlush(user);
			return userMapper.map(user, UserDto.class);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional
	@Override
	public UserDto createUser(UserDto userDto, String emailCreationSubject, String httpIpAddress) {
		boolean withEmail = false;
		if (userDto != null) {
			User user = userMapper.map(userDto, User.class);
			user.setEnabled(true);
			user.setRole(roleRepository.findByName(userDto.getRoleName()));
			user.setLastPasswordResetDate(Calendar.getInstance().getTime());
			String generatedPassword = "";

			if (userDto.getRoleName().name().equals(RoleNameEnum.ROLE_DOCK.name())) {
				user.setFirstname(userDto.getUsername());
				user.setLastname(userDto.getUsername());
				// Generate email for dock user
				StringBuffer email = new StringBuffer(userDto.getUsername()).append("@application.com");
				user.setEmail(email.toString());

				// set the password = the login when the user role is dock
				// otherwise generate a password and send email to the user
				generatedPassword = userDto.getUsername();
				user.setPassword(passwordEncoder.encode(generatedPassword));
			} else {
				withEmail = true;
				generatedPassword = RandomUtil.generatePassword();
				// Generate a random password
				user.setPassword(passwordEncoder.encode(generatedPassword));
			}

			user = userRepository.saveAndFlush(user);
			if (withEmail) {
				EmailStatus emailStatus = sendEmail(user, generatedPassword, emailCreationSubject, false,
						httpIpAddress);
				if (emailStatus.isError()) {
					LOGGER.error(emailStatus.getErrorMessage());
				}
			}
			return convertUserToUserDto(user);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User findUserByEmailAndUsername(String email, String username) {
		if (!email.equals(null) && !username.equals(null) && !email.isEmpty() && !username.isEmpty()) {
			User user = userRepository.findByEmailAndUsername(email, username);
			return user;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public EmailStatus resetPassword(User user, String emailResetSubject, String httpIpAddress) {
		if (user != null && user.getId() != null) {
			String newPassword = RandomUtil.generatePassword();
			user.setPassword(passwordEncoder.encode(newPassword));
			user.setLastPasswordResetDate(Calendar.getInstance().getTime());
			user = userRepository.saveAndFlush(user);
			return sendEmail(user, newPassword, emailResetSubject, true, httpIpAddress);
		}
		return null;
	}

	/**
	 * Send email to a user that contains credential
	 * 
	 * @param user:
	 *            user to send email
	 * @param generatedPassword:
	 *            the generated password
	 * @param emailSubject:
	 *            the email subject
	 * @param resetPassword:
	 *            boolean that indicate if it's an email for creation account or for
	 *            reset password
	 * @return EmailStatus
	 */
	@Async
	private EmailStatus sendEmail(User user, String generatedPassword, String emailSubject, boolean resetPassword,
			String httpIpAddress) {
		httpIpAddress = "http://" + httpIpAddress + ":" + serverPort;

		// Send email
		Locale locale = LocaleContextHolder.getLocale();
		Context context = new Context(locale);
		context.setVariable("user", user);
		context.setVariable("baseUrl", httpIpAddress);
		context.setVariable("password", generatedPassword);

		if (!resetPassword) {
			return emailHtmlSender.sendCreationEmail(Optional.of(emailFrom), user.getEmail(), emailSubject, context);
		}
		return emailHtmlSender.sendPasswordResetEmail(Optional.of(emailFrom), user.getEmail(), emailSubject, context);
	}

	
}
