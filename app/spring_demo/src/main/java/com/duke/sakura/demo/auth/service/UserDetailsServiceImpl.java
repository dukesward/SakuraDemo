package com.duke.sakura.demo.auth.service;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.duke.sakura.demo.auth.model.AccountShortcut;
import com.duke.sakura.demo.utils.entity.EntityServiceExceptionHandler;

public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private AccountShortcutService accounts;
	
	@Autowired
	private EntityServiceExceptionHandler entityExceptionHandler;
	
	@Override
    @Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) {
		System.out.println("debug user details service with username: " + username);
		AccountShortcut account = accounts.findByUsername(username);
		if (account == null) {
			System.out.println("username is not found");
			throw new UsernameNotFoundException(username);
		}

		return new User(account.getUsername(), account.getPassword(), new HashSet<>());
	}
}
