package com.example.demo.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.persistence.model.Branch;

public interface BranchRepository extends JpaRepository<Branch, Long> {
	Branch findByMerchantIdAndName(Long merchant_id, String name);
	Branch findById(Long id);
	Branch findByNameEn(String name_en);
}
