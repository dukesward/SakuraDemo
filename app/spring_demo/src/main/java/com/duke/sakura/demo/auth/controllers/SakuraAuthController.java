package com.duke.sakura.demo.auth.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.duke.sakura.demo.auth.model.AccountShortcut;
import com.duke.sakura.demo.auth.service.AccountShortcutService;
import com.duke.sakura.demo.utils.entity.SakuraExceptionHandler;
import com.duke.sakura.demo.utils.entity.SakuraExceptionThrower;

@RestController
@RequestMapping("/sakura/auth")
public class SakuraAuthController implements SakuraExceptionThrower {
	@Autowired
	private SakuraExceptionHandler exceptionHandler;

	@Autowired
	private AccountShortcutService accountShortcutService;
	
	@PostConstruct
	public void init() {
	   this.defineExceptions(this.exceptionHandler);
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> processLogin() {
		Map<String, Object> map = new HashMap<>();
		map.put("message", "test auth portal login");
		return map;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> processRegister(AccountShortcut account, HttpServletRequest request) {
		System.out.println("created new account " + account.getUsername());
		String message = this.exceptionHandler.success();
		account.setCreated(new Date());
		int saveAccountConfirm = this.accountShortcutService.saveAccount(account);
		if(!this.exceptionHandler.isSuccess(saveAccountConfirm)) {
			message = this.exceptionHandler.matchException(saveAccountConfirm);
		}
		Map<String, Object> map = new HashMap<>();
		map.put("message", message);
		return map;
	}

	@Override
	public void defineExceptions(SakuraExceptionHandler handler) {
		// exceptions defined post construction
		handler.addException(1, "unknown error");
	}
}
