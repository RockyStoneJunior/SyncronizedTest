package com.example.demo.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.persistence.model.Merchant;

public interface MerchantRepository extends JpaRepository<Merchant, Long> {
	
	Merchant findByName(String name);
	Merchant findById(Long id);
}
