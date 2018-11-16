package com.learnandresearch.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learnandresearch.persistence.model.User;

public interface UserRepository extends JpaRepository<User,Long>{
	User findByEmail(String email);
}
