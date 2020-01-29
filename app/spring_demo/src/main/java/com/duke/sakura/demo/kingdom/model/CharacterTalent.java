package com.duke.sakura.demo.kingdom.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
@Table(name = "CHARACTER_TALENT")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CharacterTalent implements SakuraEntityBasic {
	@Id
	@Column(name="talent_id")
	private String talentId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="effect_group_id")
	private String effectGroupId;
}
