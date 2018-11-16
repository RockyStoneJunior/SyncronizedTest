package com.example.demo.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.persistence.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
	Account findByUsername(String username);
	Account findByUsernameAndPassword(String username, String password);
}
