package com.duke.sakura.demo.kingdom.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "CHARACTER_POOL")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CharacterEntity implements SakuraEntityBasic {
	@Id
	@Column(name="uuid")
	private String uuid;
	
	@Column(name="name")
	private String name;
	
	@Column(name="name_en")
	private String nameEn;

	@Column(name="status")
	private String status;

	@Column(name="race")
	private String race;
	
	@Column(name="gender")
	private char gender;
	
	@Column(name="level")
	private int level;

	@OneToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="class_profile")
	private CharacterClassProfile classProfile;
	
	@Column(name="create_date")
	private Date createDate;
	
	@OneToOne(fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="talent_id")
	private CharacterTalent talent;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public CharacterClassProfile getClassProfile() {
		return classProfile;
	}

	public void setClassProfile(CharacterClassProfile classProfile) {
		this.classProfile = classProfile;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public CharacterTalent getTalent() {
		return talent;
	}

	public void setTalent(CharacterTalent talent) {
		this.talent = talent;
	}

}
