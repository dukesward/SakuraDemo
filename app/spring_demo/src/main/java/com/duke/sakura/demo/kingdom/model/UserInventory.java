package com.duke.sakura.demo.kingdom.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER_INVENTORY")
public class UserInventory implements SakuraEntityBasic {
	@Id
	@Column(name="user_id")
	private String userId;
	
	@Column(name="inventory_id")
	private String inventoryId;
	
	@Column(name="stack_limit")
	private int stackLimit;
	
	@Column(name="stack_volume")
	private int stackVolume;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(String inventoryId) {
		this.inventoryId = inventoryId;
	}

	public int getStackLimit() {
		return stackLimit;
	}

	public void setStackLimit(int stackLimit) {
		this.stackLimit = stackLimit;
	}

	public int getStackVolume() {
		return stackVolume;
	}

	public void setStackVolume(int stackVolume) {
		this.stackVolume = stackVolume;
	}
}
