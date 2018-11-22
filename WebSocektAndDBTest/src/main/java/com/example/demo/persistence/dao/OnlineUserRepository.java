package com.example.demo.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.persistence.model.OnlineUser;

public interface OnlineUserRepository extends JpaRepository<OnlineUser, Long> {
	OnlineUser findByBranchId(Long branch_id);
	//void delete(OnlineUserRepository user);
}
