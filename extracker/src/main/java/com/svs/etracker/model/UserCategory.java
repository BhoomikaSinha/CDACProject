package com.svs.etracker.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class UserCategory {

	private int categoryId;
	private String category;
	private User user;

	public UserCategory() {
	}

	public UserCategory(int id, String category) {
		this.categoryId = id;
		this.category = category;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int id) {
		this.categoryId = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	@ManyToOne(targetEntity=User.class)
	@JoinColumn(name="user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}