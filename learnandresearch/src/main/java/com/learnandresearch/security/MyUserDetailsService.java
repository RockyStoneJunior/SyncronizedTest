package com.learnandresearch.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learnandresearch.persistence.dao.UserRepository;
import com.learnandresearch.persistence.model.User;

//@Service("userDetailsService")
@Transactional
public class MyUserDetailsService {//implements UserDetailsService{
	
//	@Autowired
//	private UserRepository userRepository;
//	
//	public MyUserDetailsService() {
//		super();
//	}
//	
//	@Override
//	public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException{
//		
//		final User user = userRepository.findByEmail(email);
//		
//		if(user == null) {
//			throw new UsernameNotFoundException("No user found with username: " + email);
//		}
//		
//		System.out.println(email + ":User Login!");
//		System.out.println("password: " + user.getPassword());
//		
//		final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//        authorities.add(new SimpleGrantedAuthority("READ_PRIVILEGE"));
//		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), true, true, true, true, authorities);
//	}
}
