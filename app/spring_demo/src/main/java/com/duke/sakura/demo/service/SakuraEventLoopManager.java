package com.duke.sakura.demo.service;

import com.duke.sakura.demo.bean.SakuraDataSourceEvent;

public interface SakuraEventLoopManager {
	
	public void pushEvent(SakuraDataSourceEvent event);
}
