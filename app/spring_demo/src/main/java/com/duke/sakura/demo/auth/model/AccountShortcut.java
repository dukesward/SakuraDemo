package com.duke.sakura.demo.auth.model;

import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name = "account_shortcut")
public class AccountShortcut {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long uid;
	
	private String username;
	
	private String password;
	
	private Date created;
	
	public Long getId() {
        return uid;
    }

	public void setId(Long uid) {
        this.uid = uid;
    }
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
}
