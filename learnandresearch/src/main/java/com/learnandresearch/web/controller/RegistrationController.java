package com.learnandresearch.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.learnandresearch.persistence.dao.UserRepository;
import com.learnandresearch.persistence.model.User;

@RestController
public class RegistrationController {
	
	@Autowired
	private JavaMailSender  mailSender;
	
	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value="/user/registration", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> registerUserAccount(@Valid final User account,final HttpServletRequest request) {
		
		/*final SimpleMailMessage email = new SimpleMailMessage();
		email.setTo("739909951@qq.com");
		email.setSubject("Stone SpringBoot Mail Test");
		email.setText("Hello LiDonghai, this is my first springboot mail test. Don't panic!");
		email.setFrom("511697856@qq.com");

		for(int i=0;i<10;i++)
		{
			mailSender.send(email);
		}*/
		
		account.setEnabled(true);
		account.setUsing2FA(true);
		
		userRepository.save(account);
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("message", "success");	
		return map;
	}
}
