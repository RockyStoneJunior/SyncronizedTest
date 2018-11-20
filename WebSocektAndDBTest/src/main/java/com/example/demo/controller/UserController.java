package com.example.demo.controller;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.persistence.dao.AccountRepository;
import com.example.demo.persistence.dao.BranchRepository;
import com.example.demo.persistence.dao.MerchantRepository;
import com.example.demo.persistence.model.Account;
import com.example.demo.persistence.model.Branch;
import com.example.demo.persistence.model.Merchant;
import com.example.demo.utils.MD5;


@RestController
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	private MerchantRepository merchantRepository;
	
	@Autowired
	private BranchRepository branchRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@RequestMapping(value="/register", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String userRegister(HttpServletRequest request) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		
		String merchant_name = request.getParameter("merchant_name");
		
		if(merchant_name != null)
		{			
			Merchant merchant = merchantRepository.findByName(merchant_name);
			
			if(merchant != null)
			{
				String branch_name = request.getParameter("branch");
				Branch branch = branchRepository.findByMerchantIdAndName(merchant.getId(), branch_name);
				
				if(branch != null)
				{				
					String username = request.getParameter("username");
					String password = request.getParameter("password");
					
					Account account = accountRepository.findByUsername(username);
					
					if(account == null)
					{
						Account account_new = new Account();
						account_new.setBranch_id(branch.getId());
						account_new.setUsername(username);
						
						MessageDigest md = MessageDigest.getInstance("MD5");
				        md.update(password.getBytes("UTF-8"));
				        String password_encypted = MD5.byteArrayToHexString(md.digest());
						account_new.setPassword(password_encypted);
						
						accountRepository.save(account_new);
						
						return "注册成功，请登录！";
					}else {
						return "用户名已存在";
					}
				}else {
					return "分店不存在";
				}
			}else {
				return "商户不存在";
			}
		}else {
			return "商户名不能为空";
		}
	}

	@RequestMapping(value="/login", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String,String> userLogin(HttpServletRequest request, HttpSession session) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes("UTF-8"));
        String password_encypted = MD5.byteArrayToHexString(md.digest());
        
        System.out.println(password);
        System.out.println(password_encypted);
		
		Account account = accountRepository.findByUsernameAndPassword(username, password_encypted);
		
		if(account != null)
		{
			Branch branch = branchRepository.findById(account.getBranch_id());
			
			System.out.println(session.getId());
			
			final ServletContext context = session.getServletContext();
			context.setAttribute(session.getId(), session);
			
			Map<String, String> resultMap = new HashMap<String, String>();
			resultMap.put("result", "success");
			resultMap.put("branch", branch.getName());
			resultMap.put("token",session.getId());
			
			return resultMap;
		}else {
			return Collections.singletonMap("result", "failed");
		}
	}
}
