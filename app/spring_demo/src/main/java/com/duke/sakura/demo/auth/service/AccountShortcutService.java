package com.duke.sakura.demo.auth.service;

import com.duke.sakura.demo.auth.model.AccountShortcut;

public interface AccountShortcutService {
	
	int saveAccount(AccountShortcut account);
	
	AccountShortcut findByUsername(String username);
}
