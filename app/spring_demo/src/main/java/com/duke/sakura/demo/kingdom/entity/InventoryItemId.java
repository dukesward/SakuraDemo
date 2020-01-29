package com.duke.sakura.demo.kingdom.entity;

import java.io.Serializable;

public class InventoryItemId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3932420704484356362L;
	
	private String inventoryId;
	
	private String itemId;

	public String getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(String inventoryId) {
		this.inventoryId = inventoryId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
}
