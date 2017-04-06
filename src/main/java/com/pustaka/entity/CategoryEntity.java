package com.pustaka.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Pustaka_Category")
public class CategoryEntity {
	@Id
	@GeneratedValue
	private Long categoryid;
	private String name;

	public Long getCategoryId() {
		return categoryid;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryid = categoryId;
	}

	public String getCategoryName() {
		return name;
	}

	public void setCategoryName(String categoryName) {
		this.name = categoryName;
	}

}
