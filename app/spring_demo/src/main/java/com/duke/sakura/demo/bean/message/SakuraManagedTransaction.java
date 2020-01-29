package com.duke.sakura.demo.bean.message;

import java.util.Date;

import lombok.Data;

@Data
public class SakuraManagedTransaction {
	
	private String message;
	
	private String status;
	
	private Date timeCreated;
	
	private Date timeUpdated;
}
