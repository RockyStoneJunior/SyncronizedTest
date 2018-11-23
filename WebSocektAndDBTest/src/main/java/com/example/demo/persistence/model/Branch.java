package com.example.demo.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "branch")
public class Branch {
	@Id
	@Column(unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "merchant_id")
	private Long merchantId;
	
	private String name;
	
	@Column(name = "name_en")
	private String nameEn;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMerchant_id() {
		return merchantId;
	}

	public void setMerchant_id(Long merchant_id) {
		this.merchantId = merchant_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName_en() {
		return nameEn;
	}

	public void setName_en(String name_en) {
		this.nameEn = name_en;
	}
	
}
