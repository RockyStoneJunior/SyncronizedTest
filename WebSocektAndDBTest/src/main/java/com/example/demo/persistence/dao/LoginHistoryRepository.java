package com.example.demo.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.persistence.model.LoginHistory;

public interface LoginHistoryRepository extends JpaRepository<LoginHistory, Long> {

}
