package com.example.demo.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repo.UserRepository;
import com.example.demo.service.IUserService;

@Service
public class UserServiceImpl implements IUserService, UserDetailsService {

	@Autowired
	private BCryptPasswordEncoder pwdEncoder;
	
	@Autowired
	private UserRepository repo;

	@Override
	public Integer saveUser(User user) {
		String pwd = user.getPwd();
		String encpwd = pwdEncoder.encode(pwd);
		user.setPwd(encpwd);
		
		user = repo.save(user);
		System.out.println("11111111111");
		return user.getUid();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// when we login then user id is store as username and it go in databases and
		// fetch all data
		Optional<User> opt = repo.findByEmail(username);
		org.springframework.security.core.userdetails.User usr = null;
		if (opt.isEmpty()) {
			// if user is not found then this exception is show
			throw new UsernameNotFoundException("User not found " + username);
		} else {
			// if user is found then then it is in form of user object now we convert
			// spring security object
			User user = opt.get();

			List<String> roles = user.getRoles();
			Set<GrantedAuthority> gas = new HashSet<>();

			for (String role : roles) {
				gas.add(new SimpleGrantedAuthority(role));
			}

			usr = new org.springframework.security.core.userdetails.User(username, user.getPwd(), gas);

		}

		return usr;
	}

}
