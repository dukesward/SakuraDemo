package com.duke.sakura.demo.kingdom.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.duke.sakura.demo.kingdom.entity.InventoryItemId;

@Entity
@Table(name = "INVENTORY_ITEM")
@IdClass(InventoryItemId.class)
public class InventoryItem implements SakuraEntityBasic {
	@Id
	@Column(name="inventory_id")
	private String inventoryId;

	@Id
	@Column(name="item_id")
	private String itemId;
	
	@Column(name="create_date")
	private Date createDate;
	
	@Column(name="stack_amount")
	private int stackAmount;

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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getStackAmount() {
		return stackAmount;
	}

	public void setStackAmount(int stackAmount) {
		this.stackAmount = stackAmount;
	}
	
	public String toString() {
		return "inventoryId: " + this.inventoryId + ", itemId: " + this.itemId;
	}
}
