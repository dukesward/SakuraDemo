package com.duke.sakura.demo.service;

import com.duke.sakura.demo.auth.model.SakuraUserSession;

public interface SakuraSessionManager {
	
	public SakuraUserSession getUserSession();
}
