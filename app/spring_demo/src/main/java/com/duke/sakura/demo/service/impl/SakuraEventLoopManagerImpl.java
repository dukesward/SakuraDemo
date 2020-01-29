package com.duke.sakura.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.duke.sakura.demo.bean.SakuraDataSourceEvent;
import com.duke.sakura.demo.bean.SakuraEventAbstract;
import com.duke.sakura.demo.service.SakuraEventLoopManager;

@Service
public class SakuraEventLoopManagerImpl implements SakuraEventLoopManager {
	
	protected List<SakuraEventAbstract> eventLoop;

	@Override
	public void pushEvent(SakuraDataSourceEvent event) {
		this.eventLoop.add(event);
	}
	
	public void processEvent() {
		
	}
}
