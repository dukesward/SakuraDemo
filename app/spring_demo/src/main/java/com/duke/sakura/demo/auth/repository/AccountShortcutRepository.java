package com.duke.sakura.demo.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.duke.sakura.demo.auth.model.AccountShortcut;

public interface AccountShortcutRepository extends JpaRepository<AccountShortcut, Long> {
	
	AccountShortcut findByUsername(@Param("username") String username);
}
