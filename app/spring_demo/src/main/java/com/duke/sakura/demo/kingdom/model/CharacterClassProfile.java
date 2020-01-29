package com.duke.sakura.demo.kingdom.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "CHARACTER_CLASS")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CharacterClassProfile implements SakuraEntityBasic {
	@Id
	@Column(name="class_id")
	private String classId;
	
	@Column(name="class_name")
	private String className;
	
	@Column(name="class_name_en")
	private String classNameEn;
	
	@Column(name="class_level")
	private String classLevel;
	
	@Column(name="dominant_attr")
	private String dominantAttribute;
	
	@Column(name="nature_id")
	private String natureId;

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassNameEn() {
		return classNameEn;
	}

	public void setClassNameEn(String classNameEn) {
		this.classNameEn = classNameEn;
	}

	public String getClassLevel() {
		return classLevel;
	}

	public void setClassLevel(String classLevel) {
		this.classLevel = classLevel;
	}

	public String getDominantAttribute() {
		return dominantAttribute;
	}

	public void setDominantAttribute(String dominantAttribute) {
		this.dominantAttribute = dominantAttribute;
	}

	public String getNatureId() {
		return natureId;
	}

	public void setNatureId(String natureId) {
		this.natureId = natureId;
	}
}
