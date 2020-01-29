package com.duke.sakura.demo.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duke.sakura.demo.auth.model.AccountShortcut;
import com.duke.sakura.demo.auth.repository.AccountShortcutRepository;

@Service
public class AccountShortcutServiceImpl implements AccountShortcutService {
	@Autowired
	private AccountShortcutRepository accountRepository;

	@Override
	public int saveAccount(AccountShortcut account) {
		// TODO Auto-generated method stub
		try {
			this.accountRepository.save(account);
		}catch (Exception e) {
			System.out.println("err save account " + account.getUsername());
			return 1;
		}
		return 0;
	}

	@Override
	public AccountShortcut findByUsername(String username) {
		// TODO Auto-generated method stub
		return accountRepository.findByUsername(username);
	}

}
