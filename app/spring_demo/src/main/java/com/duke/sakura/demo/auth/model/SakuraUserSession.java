package com.duke.sakura.demo.auth.model;

import com.duke.sakura.demo.kingdom.model.SakuraEntityBasic;

public interface SakuraUserSession extends SakuraEntityBasic {
	
	public String getSessionId();
}
