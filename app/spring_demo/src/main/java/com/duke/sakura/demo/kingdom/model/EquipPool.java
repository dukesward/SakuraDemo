package com.duke.sakura.demo.kingdom.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EQUIP_POOL")
public class EquipPool implements SakuraEntityBasic {
	@Id
	@Column(name="equip_id")
	private String equipId;
	
	@Column(name="subtype")
	private String subtype;
	
	@Column(name="type")
	private String type;
	
	@Column(name="price")
	private int price;
	
	@Column(name="quality")
	private String quality;
	
	@Column(name="quality_level")
	private int qualityLevel;
	
	@Column(name="attr_type")
	private String attrType;
	
	@Column(name="attrs")
	private String attributes;

	public String getEquipId() {
		return equipId;
	}

	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}

	public String getSubtype() {
		return subtype;
	}

	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	public int getQualityLevel() {
		return qualityLevel;
	}

	public void setQualityLevel(int qualityLevel) {
		this.qualityLevel = qualityLevel;
	}

	public String getAttrType() {
		return attrType;
	}

	public void setAttrType(String attrType) {
		this.attrType = attrType;
	}

	public String getAttributes() {
		return attributes;
	}

	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}
}
